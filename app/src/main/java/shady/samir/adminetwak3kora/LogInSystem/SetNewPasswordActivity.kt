package shady.samir.adminetwak3kora.LogInSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Response
import shady.samir.adminetwak3kora.LogInSystem.model.*
import shady.samir.adminetwak3kora.MainActivities.MainActivity
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.ViewHelper.HelperView

class SetNewPasswordActivity : AppCompatActivity() {
    lateinit var edtNew: TextInputEditText
    lateinit var edtConfirmPass: TextInputEditText
    lateinit var btnsave: Button
    var helperView: HelperView = HelperView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_new_password)
        edtConfirmPass = findViewById(R.id.edtConfirmPass)
        edtNew = findViewById(R.id.edtNew)
        btnsave = findViewById(R.id.btnsave)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        btnsave.setOnClickListener {
            validate()
        }
    }

    var resetModel: ResetRequestModel? = null
    fun validate() {
        val password = edtNew.text.toString().trim()
        val confirmPass = edtConfirmPass.text.toString().trim()
        resetModel = ResetRequestModel(confirmPass, password)
        if (!password.equals("")) {
            if (password.length >= 8) {
                if (confirmPass == password) {
                    reset().observe(this, Observer {
                        if (it.code() == 200) {
                            helperView.toastAShow(
                                this,
                                this,
                                "Password Reset successfully",
                                R.drawable.ic_lock
                            )
                            val intent = Intent(this,MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)

                        } else {
                            helperView.toastAShow(
                                this,
                                this,
                                it.errorBody().toString(),
                                R.drawable.ic_lock
                            )
                        }
                    })
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
            helperView.toastAShow(this, this, "Please Enter an Pasword", R.drawable.ic_lock)
        }
    }

    lateinit var retService: SportService

    private fun reset(): LiveData<Response<ConfirmEmailResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ConfirmEmailResponse>> = liveData {
            val response = retService.reset(intent.getStringExtra("URL") ?: "", resetModel)
            emit(response)
        }

        return responseLiveData

    }
}