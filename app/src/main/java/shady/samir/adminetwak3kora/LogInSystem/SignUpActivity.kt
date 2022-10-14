package shady.samir.adminetwak3kora.LogInSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import shady.samir.adminetwak3kora.R

class SignUpActivity : AppCompatActivity() {

    lateinit var edtmail: TextInputEditText
    lateinit var edtpass: TextInputEditText
    lateinit var signup: TextView
    lateinit var login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        edtmail = findViewById(R.id.edtmail)
        edtpass = findViewById(R.id.edtpass)
        signup = findViewById(R.id.signup)
        login = findViewById(R.id.Login)

        signup.setOnClickListener {
            signupFun()
        }

        login.setOnClickListener {
            startActivity(Intent(this,
                LoginActivity::class.java))
        }

    }

    private fun signupFun() {
        val email = edtmail.text.toString().trim()
        val pass = edtpass.text.toString().trim()

        if (!email.equals("")){
            if (!pass.equals("")){
                signupFunPAI(email,pass)
            }else{

            }

        }else{

        }

    }

    private fun signupFunPAI(email: String, pass: String) {

    }


    private fun goCom(){
        startActivity(Intent(this,
            ConformMessageActivity::class.java).putExtra("From","SignUp"))
    }
}
