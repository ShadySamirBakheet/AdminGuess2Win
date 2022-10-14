package shady.samir.adminetwak3kora.AddActivities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.*
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
import shady.samir.adminetwak3kora.Models.DataModel.League
import shady.samir.adminetwak3kora.Models.LeagueModelResponse
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.AddLeagueRequest
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.AddLeagueResponse
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.DeleteLeagueResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeagueResponseModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class AddLeagueActivity : AppCompatActivity() {

    lateinit var imgleaguelogo: ImageView
    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var btnaddmatch: Button
    lateinit var edtleauguenameAr: EditText
    lateinit var edtleauguenameEn: EditText
    lateinit var edtstaertdate: TextView
    lateinit var edtenddate: TextView

    var leagueImage: File? = null

    lateinit var uri: Uri

    var league: League? = null
    var edit = false
    lateinit var cDate: Calendar

    var startDate: String? = null
    var endDate: String? = null

    lateinit var retService: SportService

    var getLeague: LeagueModelResponse.Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_league)
        //uri = Uri.EMPTY

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        btnsave = findViewById(R.id.btnsave)
        btndelete = findViewById(R.id.btndelete)
        btnaddmatch = findViewById(R.id.btnaddmatch)
        edtleauguenameAr = findViewById(R.id.edtleauguenameAr)
        edtleauguenameEn = findViewById(R.id.edtleauguenameEn)
        edtstaertdate = findViewById(R.id.edtstaertdate)
        edtenddate = findViewById(R.id.edtenddate)
        imgleaguelogo = findViewById(R.id.imgleaguelogo)
        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            supportActionBar?.title = "Update League"
            btndelete.visibility = View.VISIBLE
            btnaddmatch.visibility = View.VISIBLE
            edit = true

            btndelete.setOnClickListener {
                deleteLeague()
            }

            getLeague(id.toString()).observe(this, androidx.lifecycle.Observer {
                if (it.code() == 200) {
                    it.body()?.data?.run {
                        getLeague = this
                        edtleauguenameAr.setText(nameAR ?: "")
                        edtleauguenameEn.setText(nameEn ?: "")

                        Glide.with(this@AddLeagueActivity).load(image).into(imgleaguelogo)

//                        Toast.makeText(
//                            this@AddLeagueActivity,
//                            leagueImage?.path,
//                            Toast.LENGTH_LONG
//                        )
//                            .show()


                        val format = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
                        val startDateD = format.parse(startDate ?: "")
                        edtstaertdate.text = format.format(startDateD!!)
                        this@AddLeagueActivity.startDate =  format.format(startDateD!!)

                        val endDateD = format.parse(endDate ?: "")
                        edtenddate.text = format.format(endDateD!!)

                        this@AddLeagueActivity.endDate = format.format(endDateD!!)
                    }
                }
            })
        } else {
            supportActionBar?.title = "Add League"
            btnaddmatch.visibility = View.GONE
            btndelete.visibility = View.GONE
        }

        btnsave.setOnClickListener {
            saveDateLeague()
        }

        imgleaguelogo.setOnClickListener {
            selectImage()
        }

        edtstaertdate.setOnClickListener {
            if (league != null) {
                selectDate(edtstaertdate, null, true)
            } else {
                selectDate(edtstaertdate, null, true)
            }
        }

        edtenddate.setOnClickListener(View.OnClickListener {
            if (league != null) {
                selectDate(edtenddate, null, false)
            } else {
                selectDate(edtenddate, null, false)
            }
        })
    }

    private fun deleteLeague() {
        deleteLeagueById().observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "League deleted successfully", Toast.LENGTH_LONG)
                    .show()
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun saveDateLeague() {

           val addLeagueRequest = AddLeagueRequest().apply {
               NameEn = edtleauguenameEn.text.toString()
               NameAr = edtleauguenameAr.text.toString()
               ImageFile = leagueImage
               StartDate = startDate
               EndDate = endDate
           }

           league.let {
               if (edit) {
                   addLeagueRequest.run {
                       LeagueId = getLeague?.id
                       if (NameAr == null || NameEn == null || StartDate == null || EndDate == null) {
                           Toast.makeText(
                               this@AddLeagueActivity,
                               "please fill all field",
                               Toast.LENGTH_LONG
                           )
                               .show()
                           return
                       } else {
                           updateLeague(addLeagueRequest).observe(
                               this@AddLeagueActivity,
                               Observer { response ->
                                   if (response.code() == 200) {
                                       Toast.makeText(
                                           this@AddLeagueActivity,
                                           "League Added Successfully",
                                           Toast.LENGTH_LONG
                                       )
                                           .show()
                                       finish()
                                   } else {
                                       Toast.makeText(
                                           this@AddLeagueActivity,
                                           response.message()+" "+response.code()+response.raw(),
                                           Toast.LENGTH_LONG).show()
                                   }
                               })
                       }
                   }
               } else {
                   addLeagueRequest.run {
                       if (NameAr == null || NameEn == null || ImageFile == null || StartDate == null || EndDate == null) {
                           Toast.makeText(
                               this@AddLeagueActivity,
                               "please fill all field",
                               Toast.LENGTH_LONG
                           )
                               .show()
                           return
                       } else {
                           addLeague(addLeagueRequest).observe(
                               this@AddLeagueActivity,
                               Observer { response ->
                                   if (response.code() == 200) {
                                       Toast.makeText(
                                           this@AddLeagueActivity,
                                           "League Added Successfully",
                                           Toast.LENGTH_LONG
                                       )
                                           .show()
                                       finish()
                                   } else {
                                       Toast.makeText(
                                           this@AddLeagueActivity,
                                           response.message()+" "+response.code()+response.raw(),
                                           Toast.LENGTH_LONG).show()
                                   }
                               })
                       }
                   }
               }
           }

    }

    private fun updateLeague(addLeagueRequest: AddLeagueRequest): LiveData<Response<AddLeagueResponse>> {
        var body:MultipartBody.Part? = null
        if (leagueImage==null){
             body = null
        }else{
            val requestFile: RequestBody =
                addLeagueRequest.ImageFile!!.asRequestBody("multipart/form-data".toMediaTypeOrNull())

             body =
                MultipartBody.Part.createFormData("ImageFile", addLeagueRequest.ImageFile?.name, requestFile)
        }

        val leagueID: RequestBody =
            addLeagueRequest.LeagueId?.toString()?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!

        val nameEn: RequestBody =
            addLeagueRequest.NameEn?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val nameAr: RequestBody =
            addLeagueRequest.NameAr?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val startDate: RequestBody =
            addLeagueRequest.StartDate?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val endDate: RequestBody =
            addLeagueRequest.EndDate?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddLeagueResponse>> = liveData {
            val response = retService.PutPriodical(
                getLeague?.id.toString(),
                leagueID,
                nameEn,
                nameAr,
                body,
                startDate,
                endDate
            )
            emit(response)
        }

        return responseLiveData
    }

    private fun getLeague(stringExtra: String): LiveData<Response<LeagueModelResponse>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<LeagueModelResponse>> = liveData {
            val response = retService.getLeagueById(stringExtra)
            emit(response)
        }

        return responseLiveData
    }

    private fun addLeague(addLeagueRequest: AddLeagueRequest): LiveData<Response<AddLeagueResponse>> {
        val requestFile: RequestBody =
            addLeagueRequest.ImageFile!!.asRequestBody("multipart/form-data".toMediaTypeOrNull())

        val body: MultipartBody.Part =
            MultipartBody.Part.createFormData("ImageFile", addLeagueRequest.ImageFile?.name, requestFile)

        val nameEn: RequestBody =
            addLeagueRequest.NameEn?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val nameAr: RequestBody =
            addLeagueRequest.NameAr?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val startDate: RequestBody =
            addLeagueRequest.StartDate?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        val endDate: RequestBody =
            addLeagueRequest.EndDate?.toRequestBody("multipart/form-data".toMediaTypeOrNull())!!
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddLeagueResponse>> = liveData {
            val response = retService.PostPriodical(
                nameEn,
                nameAr,
                body,
                startDate,
                endDate
            )
            emit(response)
        }

        return responseLiveData
    }

    fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.apply {
            type = "image/*"
        }
        startActivityForResult(intent, 1)
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

                    leagueImage = File(FileUtils.getSmartFilePath(this, data.data!!) ?: "")

                    imgleaguelogo.setImageURI(uri)
                    //imgleaguelogo.invalidate()
                }
            }
        }
    }


    private fun selectDate(
        edtenddate: TextView,
        leagueEndDate: Date?,
        b: Boolean
    ) {
        val calendar = Calendar.getInstance()
        if (leagueEndDate != null) {
            calendar.time = leagueEndDate
        }
        cDate = Calendar.getInstance()
        DatePickerDialog(
            this,
            OnDateSetListener { datePicker, i, i1, i2 ->
                cDate.set(i, i1, i2)
                val i3 = if (i1 <= 8) "0${(i1 + 1)}" else "${(i1 + 1)}"
                val format = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
                edtenddate.text = "$i-$i3-$i2"
                if (b) {
                    startDate = "$i-$i3-$i2"
                } else {
                    endDate = "$i-$i3-$i2"
                }
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    fun deleteLeagueById(): LiveData<Response<DeleteLeagueResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<DeleteLeagueResponseModel>> = liveData {
            val response = retService.deleteLeague(getLeague?.id.toString())
            emit(response)
        }

        return responseLiveData
    }
}