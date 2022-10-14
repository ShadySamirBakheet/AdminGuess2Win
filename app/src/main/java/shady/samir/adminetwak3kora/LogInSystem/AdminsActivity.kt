package shady.samir.adminetwak3kora.LogInSystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Response
import shady.samir.adminetwak3kora.Adapters.AdminsItemClickListener
import shady.samir.adminetwak3kora.Adapters.DataAdapters.AdminsAdapter
import shady.samir.adminetwak3kora.LogInSystem.model.AdminsResponseModel
import shady.samir.adminetwak3kora.LogInSystem.model.ConfirmEmailResponse
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData

class AdminsActivity : AppCompatActivity() {
    lateinit var adminsList: RecyclerView
    lateinit var btnAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admins)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        adminsList = findViewById(R.id.adminsList)
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddAdminActivity::class.java
                )
            )
        }

        getAdmins()
    }

    private fun getAdmins() {
        getAdminsApi().observe(this, Observer {
            if (it.code() == 200) {
                val adapter = AdminsAdapter(this, it.body()?.data!!)
                adapter.adminsItemClickListener = object : AdminsItemClickListener {
                    override fun deleteAdmin(adminId: String) {
                        this@AdminsActivity.deleteAdmin(adminId)
                    }
                }
                adminsList.adapter = adapter
                adminsList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }


    lateinit var retService: SportService
    val sharedPref = SharedPreferencesData(this)
    private fun getAdminsApi(): LiveData<Response<AdminsResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AdminsResponseModel>> = liveData {
            val response = retService.getAdmins(sharedPref.getId())
            emit(response)
        }

        return responseLiveData
    }

    fun deleteAdmin(adminId: String) {
        deleteAdminApi(adminId).observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "admin deleted successfully", Toast.LENGTH_LONG).show()
                getAdmins()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun deleteAdminApi(adminId: String): LiveData<Response<ConfirmEmailResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<ConfirmEmailResponse>> = liveData {
            val response = retService.deleteAdmin(sharedPref.getId()?:"",adminId)
            emit(response)
        }

        return responseLiveData
    }
}