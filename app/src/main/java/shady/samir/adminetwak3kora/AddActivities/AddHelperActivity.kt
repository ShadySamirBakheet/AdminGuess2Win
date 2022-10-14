package shady.samir.adminetwak3kora.AddActivities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Helper
import shady.samir.adminetwak3kora.Models.ResponseModel.Help.AddHelpResponseModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class AddHelperActivity : AppCompatActivity() {

    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var titleAr: EditText
    lateinit var titleEn: EditText
    lateinit var helperAr: EditText
    lateinit var helperEn: EditText

    lateinit var retService: SportService

    lateinit var helper: Helper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_helper)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        inti()

        if (intent.getIntExtra("id", 0) > 0) {
            supportActionBar?.title = "Update Helper"
            btndelete.visibility = View.VISIBLE
            getHelper(intent.getIntExtra("id", 0))
            btnsave.setOnClickListener {
                updateHelp()
            }
        } else {
            supportActionBar?.title = "Add Helper"
            btndelete.visibility = View.GONE

            btnsave.setOnClickListener {
                saveHelp()
            }
        }

        inti()

    }

    var getHelp: AddHelpResponseModel.Data? = null
    private fun getHelper(id: Int) {
        requestHelp(id.toString()).observe(this, Observer {
            if (it.code() == 200) {
                it.body()?.data?.run {
                    getHelp = this
                    this@AddHelperActivity.titleAr.setText(titleAr)
                    this@AddHelperActivity.titleEn.setText(titleEn)
                    this@AddHelperActivity.helperAr.setText(descriptionAr)
                    helperEn.setText(descriptionEn)
                }
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateHelp() {
        val titlear = titleAr.text.toString().trim()
        val titlen = titleEn.text.toString().trim()
        val helperar = helperAr.text.toString().trim()
        val helperen = helperEn.text.toString().trim()

        if (titlear != "") {
            if (titlen != "") {
                if (helperar != "") {
                    if (helperen != "") {
                        helper =
                            Helper(
                                titlear,
                                titlen,
                                helperar,
                                helperen
                            )
                        helper.id = getHelp?.id
                        requestUpdateHelp().observe(this, Observer {
                            if (it.code() == 200) {
                                Toast.makeText(this, "help updated successfully", Toast.LENGTH_LONG)
                                    .show()
                            } else {
                                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                            }
                        })
                    }
                }
            }
        }

    }


    private fun inti() {
        btndelete = findViewById(R.id.btndelete)
        btnsave = findViewById(R.id.btnsave)
        titleAr = findViewById(R.id.titleAr)
        titleEn = findViewById(R.id.titleEn)
        helperAr = findViewById(R.id.helperAr)
        helperEn = findViewById(R.id.helperEn)



        btndelete.setOnClickListener {
            deleteHelp()
        }
    }

    private fun deleteHelp() {
        requestDeleteHelp(getHelp?.id.toString()).observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "help deleted successfully", Toast.LENGTH_LONG)
                    .show()
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveHelp() {
        val titlear = titleAr.text.toString().trim { it <= ' ' }
        val titlen = titleEn.text.toString().trim { it <= ' ' }
        val helperar = helperAr.text.toString().trim { it <= ' ' }
        val helperen = helperEn.text.toString().trim { it <= ' ' }

        if (!titlear.equals("")) {
            if (!titlen.equals("")) {
                if (!helperar.equals("")) {
                    if (!helperen.equals("")) {
                        helper =
                            Helper(
                                titlear,
                                titlen,
                                helperar,
                                helperen
                            )
                        addHelp().observe(this, Observer {
                            if (it.code() == 200) {
                                Toast.makeText(this, "help added successfully", Toast.LENGTH_LONG)
                                    .show()
                                titleAr.setText("")
                                titleEn.setText("")
                                helperAr.setText("")
                                helperEn.setText("")
                            } else
                                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                        })
                    }
                }
            }
        }
    }

    fun addHelp(): LiveData<Response<AddHelpResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddHelpResponseModel>> = liveData {
            val response = retService.addHelp(helper)
            emit(response)
        }

        return responseLiveData
    }

    fun requestUpdateHelp(): LiveData<Response<AddHelpResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddHelpResponseModel>> = liveData {
            val response = retService.updateHelp(helper.id.toString(), helper)
            emit(response)
        }

        return responseLiveData
    }

    fun requestHelp(id: String): LiveData<Response<AddHelpResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddHelpResponseModel>> = liveData {
            val response = retService.getHelp(id)
            emit(response)
        }

        return responseLiveData
    }

    fun requestDeleteHelp(id: String): LiveData<Response<AddHelpResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddHelpResponseModel>> = liveData {
            val response = retService.deleteHelp(id)
            emit(response)
        }

        return responseLiveData
    }
}
