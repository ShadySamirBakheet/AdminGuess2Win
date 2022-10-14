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
import shady.samir.adminetwak3kora.LogInSystem.model.AddAdminResponseModel
import shady.samir.adminetwak3kora.LogInSystem.model.LoginRequestModel
import shady.samir.adminetwak3kora.LogInSystem.model.LoginResponseModel
import shady.samir.adminetwak3kora.MainActivities.HomeFiles.HomeActivity
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData

class LoginActivity : AppCompatActivity() {

    lateinit var edtmail: TextInputEditText
    lateinit var edtpass: TextInputEditText
    lateinit var signup: TextView
    lateinit var login: Button
    var sharedPreferences: SharedPreferencesData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edtmail = findViewById(R.id.edtmail)
        edtpass = findViewById(R.id.edtpass)
        signup = findViewById(R.id.signup)
        login = findViewById(R.id.Login)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        sharedPreferences = SharedPreferencesData(this)
        signup.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ResetPasswordActivity::class.java
                )
            )
        }

        login.setOnClickListener {
            loginFun()
        }

    }

    private fun loginFun() {

        val userMail: String = edtmail.text.toString().trim()
        val userPass: String = edtpass.text.toString().trim()

        if (!userMail.equals("")) {
            if (!userPass.equals("")) {
                val loginRequestModel = LoginRequestModel(userMail, userPass)
                loginFunApi(loginRequestModel).observe(this, Observer {
                    if (it.code() == 200) {
                        sharedPreferences?.setSignIn()
                        sharedPreferences?.setId(it.body()?.user?.id ?: "")
                        sharedPreferences?.setIsSuperAdmin(
                            it.body()?.user?.roles?.contains("SuperAdmin") ?: false
                        )
                        startActivity(
                            Intent(
                                this,
                                HomeActivity::class.java
                            )
                        )
                        finish()
                    } else {
                        Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

    }

    lateinit var retService: SportService

    private fun loginFunApi(loginRequestModel: LoginRequestModel): LiveData<Response<LoginResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<LoginResponseModel>> = liveData {
            val response = retService.login(loginRequestModel)
            emit(response)
        }

        return responseLiveData

    }
}
