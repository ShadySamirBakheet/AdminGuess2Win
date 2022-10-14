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
import shady.samir.adminetwak3kora.LogInSystem.model.ResetPassordResponseModel
import shady.samir.adminetwak3kora.LogInSystem.model.ResetPasswordModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class ResetPasswordActivity : AppCompatActivity() {

    lateinit var btnsave: Button
    lateinit var edtEmail: TextInputEditText

    lateinit var code: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        btnsave = findViewById(R.id.btnsave)
        edtEmail = findViewById(R.id.edtEmail)


        btnsave.setOnClickListener {
            if (!edtEmail.text.toString().trim().equals("")) {
                sendEmail().observe(this, Observer {
                    if (it.code() == 200){
                        val intent = Intent(this, ConformMessageActivity::class.java)
                        intent.putExtra(
                            "resetPassword",
                            ResetPasswordModel(it.body()?.user?.id, it.body()?.randomKey)
                        )
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
                    }
                })
            } else {
                Toast.makeText(this,"enter your email",Toast.LENGTH_LONG).show()
            }
        }

    }


    lateinit var retService: SportService

    private fun sendEmail(): LiveData<Response<ResetPassordResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ResetPassordResponseModel>> = liveData {
            val response = retService.resetPassword(edtEmail.text.toString().trim())
            emit(response)
        }

        return responseLiveData

    }

}
