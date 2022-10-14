package shady.samir.adminetwak3kora.LogInSystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Response
import shady.samir.adminetwak3kora.LogInSystem.model.AddAdminModel
import shady.samir.adminetwak3kora.LogInSystem.model.AddAdminRequestModel
import shady.samir.adminetwak3kora.LogInSystem.model.AddAdminResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Rewards.AddRewardResponseModel
import shady.samir.adminetwak3kora.ViewHelper.HelperView
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData

class AddAdminActivity : AppCompatActivity() {

    lateinit var edtmail: TextInputEditText
    lateinit var edtpass: TextInputEditText
    lateinit var btnadd: Button
    lateinit var userName: TextInputEditText
    lateinit var confirmPass: TextInputEditText
    var helperView: HelperView = HelperView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_admin)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        edtmail = findViewById(R.id.edtmail)
        edtpass = findViewById(R.id.edtpass)
        btnadd = findViewById(R.id.btnadd)
        userName = findViewById(R.id.edtusername)
        confirmPass = findViewById(R.id.edtconfirmpass)

        btnadd.setOnClickListener {

            saveData()

        }

        selectAdmin()

    }

    private fun selectAdmin() {


    }

    val addAdminRequestModel: AddAdminRequestModel = AddAdminRequestModel()
    private fun saveData() {

        val Email = edtmail.text.toString().trim()
        val password = edtpass.text.toString().trim()
        val userName = userName.text?.toString()?.trim()
        val confirmPassword = confirmPass.text?.toString()?.trim()
        addAdminRequestModel.run {
            email = Email
            this.userName = userName.toString()
            this.passwerd = password
            this.confirmPassword = confirmPassword.toString()
        }
        if (!userName.isNullOrEmpty()) {
            if (!Email.equals("")) {
                if (!password.equals("")) {
                    if (password.length >= 8) {
                        if (confirmPassword == password) {
                            addAdmin().observe(this, Observer {
                                if (it.code() == 200) {
                                    helperView.toastAShow(
                                        this,
                                        this,
                                        "Admin Added successfully",
                                        R.drawable.ic_lock
                                    )
                                    val intent = Intent(this, ConformMessageActivity::class.java)
                                    intent.putExtra(
                                        "addAdmin",
                                        AddAdminModel(it.body()?.randomKey, it.body()?.callbackUrl)
                                    )
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
            } else {
                helperView.toastAShow(this, this, "Please Enter an E-Mail", R.drawable.ic_mail)
            }
        } else {
            helperView.toastAShow(this, this, "Please Enter user name", R.drawable.ic_mail)
        }

    }

    lateinit var retService: SportService
    val sharedPref = SharedPreferencesData(this)
    private fun addAdmin(): LiveData<Response<AddAdminResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddAdminResponseModel>> = liveData {
            val response = retService.addAdmin(sharedPref.getId() ?: "", addAdminRequestModel)
            emit(response)
        }

        return responseLiveData

    }
}
