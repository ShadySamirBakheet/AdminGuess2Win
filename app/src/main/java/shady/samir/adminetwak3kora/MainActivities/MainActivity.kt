package shady.samir.adminetwak3kora.MainActivities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import shady.samir.adminetwak3kora.LogInSystem.LoginActivity
import shady.samir.adminetwak3kora.MainActivities.HomeFiles.HomeActivity
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.SharedPreferencesData.SharedPreferencesData
import shady.samir.adminetwak3kora.ViewHelper.HelperView

class MainActivity : AppCompatActivity() {

    lateinit var helper:HelperView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // startActivity( Intent(this,HomeActivity::class.java))

        getPermission()

        helper = HelperView()

        if (helper.isNetworkAvailable(this)){
            val handler = Handler();
            handler.postDelayed(Runnable {
                next()
            },2000)
        }else{
            helper.toastAShow(this,this,"Plesa Open The Network!!",R.drawable.ic_warning)
            finish()
        }

    }

    private fun getPermission() {
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                101
            )
        }

        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                101
            )
        }
    }

    fun next(){
        val sharedPreferencesData = SharedPreferencesData(this)
       if (sharedPreferencesData.isSignIn()){
           startActivity( Intent(this,
               HomeActivity::class.java))
           finish()
       }else{
           startActivity( Intent(this,
               LoginActivity::class.java))
           finish()
       }
    }
}
