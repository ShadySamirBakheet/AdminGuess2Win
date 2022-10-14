package shady.samir.adminetwak3kora.LogInSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Response
import shady.samir.adminetwak3kora.LogInSystem.model.AddAdminModel
import shady.samir.adminetwak3kora.LogInSystem.model.AddAdminResponseModel
import shady.samir.adminetwak3kora.LogInSystem.model.ConfirmEmailResponse
import shady.samir.adminetwak3kora.LogInSystem.model.ResetPasswordModel
import shady.samir.adminetwak3kora.MainActivities.HomeFiles.HomeActivity
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class ConformMessageActivity : AppCompatActivity() {

    lateinit var comfrim: Button
    lateinit var edtcom: TextInputEditText
    var addAdminModel: AddAdminModel? = null
    var resetPasswordModel: ResetPasswordModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conform_message)
        addAdminModel = intent.getParcelableExtra("addAdmin")
        resetPasswordModel = intent.getParcelableExtra("resetPassword")
        comfrim = findViewById(R.id.comfrim)
        edtcom = findViewById(R.id.edtcom)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        comfrim.setOnClickListener {
            if (!edtcom.text.toString().trim().equals("")) {
                cheakCode(edtcom.text.toString().trim())
            } else {
                Toast.makeText(this, "enter code", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun cheakCode(code: String) {
        addAdminModel?.run {
            if (randomKey == edtcom.text.toString()) {
                addAdmin().observe(this@ConformMessageActivity, Observer {
                    if (it.code() == 200) {
                        Toast.makeText(
                            this@ConformMessageActivity,
                            "Confirmation success",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(this@ConformMessageActivity, it.message(), Toast.LENGTH_LONG)
                            .show()
                    }
                })
            }
        } ?: kotlin.run {
            sendCodeForResetPassword()
        }
    }

    private fun sendCodeForResetPassword() {
        sendCode().observe(this, Observer {
            if (it.code() == 200) {
                val intent = Intent(this,SetNewPasswordActivity::class.java)
                intent.putExtra("URL",it.body()?.callbackUrl)
                startActivity(intent)
            } else {
                Toast.makeText(this@ConformMessageActivity, it.message(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun sendCode(): LiveData<Response<AddAdminResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddAdminResponseModel>> = liveData {
            val response = retService.sendCodeForResetPassword(
                resetPasswordModel?.userId,
                resetPasswordModel?.randomKey
            )
            emit(response)
        }

        return responseLiveData

    }

    private fun nextPage(code: String) {
        if (intent.getStringExtra("From").equals("SignUp")) {
            startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                )
            )
        } else if (intent.getStringExtra("From").equals("Change")) {
            startActivity(
                Intent(
                    this,
                    ResetPasswordActivity::class.java
                ).putExtra("code", code)
            )
        }
    }

    lateinit var retService: SportService

    private fun addAdmin(): LiveData<Response<ConfirmEmailResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ConfirmEmailResponse>> = liveData {
            val response =
                retService.confirmCode("${addAdminModel?.callbackUrl}${addAdminModel?.randomKey}")
            emit(response)
        }

        return responseLiveData

    }
}
