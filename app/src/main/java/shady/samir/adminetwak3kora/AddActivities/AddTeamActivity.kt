package shady.samir.adminetwak3kora.AddActivities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import shady.samir.adminetwak3kora.FileUtils
import shady.samir.adminetwak3kora.Models.DataModel.Team
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.DeleteTeamResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamRequestModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamResponseModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.io.File

class AddTeamActivity : AppCompatActivity() {

    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var edtteamName: EditText
    lateinit var edtteamCountry: EditText
    lateinit var edtteamNameEn: EditText
    lateinit var edtteamCountryEn: EditText
    lateinit var imgteamLogo: ImageView

    var teamImage:File?=null

    lateinit var uri: Uri

    lateinit var team: Team
    var edit = false

    var getTeam: TeamResponseModel.Team? = null

    lateinit var retService: SportService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_team)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        btnsave = findViewById(R.id.btnsave)
        btndelete = findViewById(R.id.btndelete)
        imgteamLogo = findViewById(R.id.imgteamLogo)
        edtteamName = findViewById(R.id.edtteamNameAr)
        edtteamNameEn = findViewById(R.id.edtteamNameEn)
        edtteamCountry = findViewById(R.id.edtteamCountryAr)
        edtteamCountryEn = findViewById(R.id.edtteamCountryEn)

        imgteamLogo.setOnClickListener {
            selectImage()
        }
        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            supportActionBar?.title = "Update Team"
            btndelete.visibility = View.VISIBLE
            edit = true
            btndelete.setOnClickListener {
                deleteTeam()
            }

            getTeam(id.toString()).observe(this, androidx.lifecycle.Observer {
                if (it.code() == 200) {
                    it.body()?.team?.run {
                        getTeam = this
                        edtteamName.setText(nameAR ?: "" )
                        edtteamNameEn.setText(nameEn ?: "")
                        Glide.with(this@AddTeamActivity).load(image).into(imgteamLogo)
                        edtteamCountry.setText(countryAr)
                        edtteamCountryEn.setText(countryEn)
                    }
                }
            })

        } else {
            supportActionBar?.title = "Add Team"
            btndelete.visibility = View.GONE
            edit = false
        }

        btnsave.setOnClickListener {
            saveTeam()
        }

    }

    private fun saveTeam() {

       // Toast.makeText(this, "zdgs0", Toast.LENGTH_LONG).show()

        val teamRequestModel = TeamRequestModel().apply {
            countryAr = edtteamCountry.text.toString()
            countryEn = edtteamCountryEn.text.toString()
            nameAR = edtteamName.text.toString()
            nameEn = edtteamNameEn.text.toString()
            image = teamImage
        }

        if (edit) {
            teamRequestModel.run {
                id = getTeam?.id

                if (nameAR == null || nameEn == null || countryEn == null || countryAr == null) {
                    Toast.makeText(
                        this@AddTeamActivity,
                        "please fill all field",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return
                } else {
                    updateTeam(teamRequestModel).observe(
                        this@AddTeamActivity,
                        Observer {

                            if (it.code() == 200) {
                                Toast.makeText(
                                    this@AddTeamActivity,
                                    "League Added Successfully",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this@AddTeamActivity,
                                    it.message()+" "+it.code()+it.raw(),
                                    Toast.LENGTH_LONG).show()
                            }
                        })
                }
            }
        } else {
            teamRequestModel.run {
                if (nameAR == null || nameEn == null || image == null || countryEn == null || countryAr == null) {
                    Toast.makeText(
                        this@AddTeamActivity,
                        "please fill all field",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return
                } else {
                    addTeams(teamRequestModel).observe(
                        this@AddTeamActivity,
                        Observer {
                            if (it.code() == 200) {
                                Toast.makeText(
                                    this@AddTeamActivity,
                                    "League Added Successfully",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                finish()
                            } else {
                                Toast.makeText(
                                    this@AddTeamActivity,
                                    it.message()+" "+it.code()+it.raw(),
                                    Toast.LENGTH_LONG).show()
                            }
                        })
                }
            }
        }
        
    }

    private fun addTeams(teamRequestModel: TeamRequestModel): LiveData<Response<TeamResponseModel>> {
        val requestFile: RequestBody =
            teamRequestModel.image!!.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        val body: MultipartBody.Part =
            MultipartBody.Part.createFormData("ImageFile", teamRequestModel.image?.name, requestFile)

        val nameEn: RequestBody =
            teamRequestModel.nameEn?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val nameAr: RequestBody =
            teamRequestModel.nameAR?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val countryAr: RequestBody =
            teamRequestModel.countryAr?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val countryEn: RequestBody =
            teamRequestModel.countryEn?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamResponseModel>> = liveData {
            val response = retService.PostTeam(
                nameEn,
                nameAr,
                body,
                countryEn,
                countryAr
            )
            emit(response)
        }

        return responseLiveData
    }

    private fun updateTeam(teamRequestModel: TeamRequestModel): LiveData<Response<TeamResponseModel>> {

        var body:MultipartBody.Part? = null
        if (teamImage==null){
            body = null
        }else{
            val requestFile: RequestBody =
                teamRequestModel.image!!.asRequestBody("multipart/form-data".toMediaTypeOrNull())

            body =
                MultipartBody.Part.createFormData("ImageFile", teamRequestModel.image?.name, requestFile)
        }

        val teamId: RequestBody =
            teamRequestModel.id?.toString()?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val nameEn: RequestBody =
            teamRequestModel.nameEn?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val nameAr: RequestBody =
            teamRequestModel.nameAR?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val countryAr: RequestBody =
            teamRequestModel.countryAr?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val countryEn: RequestBody =
            teamRequestModel.countryEn?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<TeamResponseModel>> = liveData {
            val response = retService.PutTeam(
                getTeam?.id.toString(),
                teamId,
                nameEn,
                nameAr,
                body,
                countryEn,
                countryAr
            )
            emit(response)
        }

        return responseLiveData
    }

    private fun getTeam(stringExtra: String): LiveData<Response<TeamResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val enResponseLiveData: LiveData<Response<TeamResponseModel>> = liveData {
            val response = retService.getTeam(stringExtra)
            emit(response)
        }

        return enResponseLiveData
    }

    private fun deleteTeam() {
        deleteTeamById().observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "League deleted successfully", Toast.LENGTH_LONG)
                    .show()
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }


    fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.apply {
            type = "image/*"
        }
        startActivityForResult(intent, 1)
    }


    fun deleteTeamById(): LiveData<Response<DeleteTeamResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<DeleteTeamResponseModel>> = liveData {
            val response = retService.DeleteTeam(getTeam?.id!!)
            emit(response)
        }

        return responseLiveData
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
        if (requestCode == 1) {
            if (true) {
                if (data != null) {

                    uri = data.data!!

                    val preferences =
                        PreferenceManager.getDefaultSharedPreferences(applicationContext)
                    val editor = preferences.edit()
                    editor.putString("image", uri.toString())
                    editor.commit()

                    imgteamLogo.setImageURI(uri)
                    //imgleaguelogo.invalidate()

                    teamImage = File(FileUtils.getSmartFilePath(this, data.data!!) ?: "")

                }
            }
        }
    }
}
