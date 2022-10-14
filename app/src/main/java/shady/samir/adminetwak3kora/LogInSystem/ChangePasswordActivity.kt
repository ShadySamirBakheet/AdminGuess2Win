package shady.samir.adminetwak3kora.LogInSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Response
import shady.samir.adminetwak3kora.LogInSystem.model.ChangePasswordRequestModel
import shady.samir.adminetwak3kora.LogInSystem.model.ConfirmEmailResponse
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData
import shady.samir.adminetwak3kora.ViewHelper.HelperView

class ChangePasswordActivity : AppCompatActivity() {

    lateinit var fpass: TextView
    lateinit var btnsave: Button
    lateinit var edtConfirmpass: TextInputEditText
    lateinit var edtold: TextInputEditText
    lateinit var edtNewpass: TextInputEditText
    var helperView: HelperView = HelperView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        fpass = findViewById(R.id.fpass)
        btnsave = findViewById(R.id.btnsave)
        edtConfirmpass = findViewById(R.id.edtConfirmpass)
        edtNewpass = findViewById(R.id.edtnpass)
        edtold = findViewById(R.id.edtold)

        fpass.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ConformMessageActivity::class.java
                ).putExtra("From", "Change")
            )
        }


        btnsave.setOnClickListener {
            saveChange()
        }


    }

    private fun saveChange() {
        val old = edtold.text.toString().trim()
        val new = edtNewpass.text.toString().trim()
        val rnew = edtConfirmpass.text.toString().trim()

        if (!old.equals("")) {
            if (!new.equals("")) {
                if (new.length > 8) {
                    if (new == rnew) {
                        saveNew(old, new, rnew)
                    } else {
                        helperView.toastAShow(
                            this,
                            this,
                            "password and confirm password are not the same",
                            R.drawable.ic_lock
                        )
                    }
                } else {
                    helperView.toastAShow(
                        this,
                        this,
                        "Please Enter a Password More Than 8 Char",
                        R.drawable.ic_lock
                    )
                }
            } else {
                helperView.toastAShow(
                    this,
                    this,
                    "Please Enter new Password",
                    R.drawable.ic_lock
                )
            }
        } else {
            helperView.toastAShow(this, this, "Please Enter old Password", R.drawable.ic_lock)
        }

    }

    private fun saveNew(old: String, new: String, rnew: String) {
        val userId = SharedPreferencesData(this).getId()
        val changePasswordRequestModel = ChangePasswordRequestModel().apply {
            oldPasswerd = old
            passwerd = new
            confirmPassword = rnew
        }
        addAdmin(userId ?: "", changePasswordRequestModel).observe(this, Observer {
            if (it.code() == 200) {
                helperView.toastAShow(
                    this,
                    this,
                    "password changed successfully",
                    R.drawable.ic_lock
                )
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    lateinit var retService: SportService

    private fun addAdmin(
        userId: String,
        changePasswordRequestModel: ChangePasswordRequestModel
    ): LiveData<Response<ConfirmEmailResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ConfirmEmailResponse>> = liveData {
            val response = retService.changePassword(userId, changePasswordRequestModel)
            emit(response)
        }

        return responseLiveData

    }
}
