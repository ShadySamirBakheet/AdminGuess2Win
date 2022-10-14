package shady.samir.adminetwak3kora.AddActivities

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Season
import shady.samir.adminetwak3kora.Models.DataModel.Week
import shady.samir.adminetwak3kora.Models.ResponseModel.AddSeasonModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AddSeasonActivity : AppCompatActivity() {

    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var edtseasonNameAr:EditText
    lateinit var edtseasonNameEn:EditText
    lateinit var edtseasonStart:TextView
    lateinit var edtseasonEnd:TextView

    var season: Season? = null
    lateinit var cDate: Calendar
    lateinit var startD: Date
    lateinit var endD:Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_season)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        btnsave = findViewById(R.id.btnsave)
        btndelete = findViewById(R.id.btndelete)
        edtseasonNameAr = findViewById(R.id.edtseasonNameAr)
        edtseasonNameEn = findViewById(R.id.edtseasonNameEn)
        edtseasonStart = findViewById(R.id.edtseasonStart)
        edtseasonEnd = findViewById(R.id.edtseasonEnd)

        edtseasonEnd.setOnClickListener {
            if (season != null) {
                selectDate(edtseasonEnd, season?.endSeason, false)
            } else {
                selectDate(edtseasonEnd, null, false)
            }
        }
        edtseasonStart.setOnClickListener {
            if (season != null) {
                selectDate(edtseasonStart, season?.startSeason, true)
            } else {
                selectDate(edtseasonStart, null, true)
            }
        }

        if (intent.getIntExtra("id",0) > 0) {
            supportActionBar!!.title = "Update Season"
            getSeason(intent.getIntExtra("id",0))
            btnsave.setOnClickListener { saveChange() }
            btndelete.setVisibility(View.VISIBLE)
            btndelete.setOnClickListener(View.OnClickListener { deleteSeason(intent.getIntExtra("id",0)) })
        } else {
            supportActionBar!!.setTitle("Add New Season")
            btnsave.setOnClickListener { saveData() }
            btndelete.setVisibility(View.GONE)
        }

    }

    private fun deleteSeason(id: Int) {

        delWeek(id).observe(this, androidx.lifecycle.Observer {
            if (it.code()==200){
                Toast.makeText(this,"Deleted Week "+season?.nameEn,Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
            }
        })

    }

    fun delWeek(id:Int): LiveData<Response<Season>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Season>> = liveData {
            val response = retService.DelSeason(id)
            emit(response)
        }

        return responseLiveData
    }

    private fun saveData() {
        val format: Format = SimpleDateFormat("yyyy-MM-dd")
        val name = edtseasonNameAr.text.toString().trim { it <= ' ' }
        val nameEn = edtseasonNameEn.text.toString().trim { it <= ' ' }
        val start = format.format(startD)
        val end = format.format(endD)
        if (name != "") {
            if (start != "") {
                if (end != "") {
                    val seasonModel = AddSeasonModel(
                        EndSeason = end,
                        NameAr = name,
                        NameEn = nameEn,
                        StartSeason = start
                    )

                    addMonth(seasonModel).observe(this, androidx.lifecycle.Observer {
                        if (it.code() == 201){
                            Toast.makeText(
                                this,
                                "Season Edited successfully",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            finish()
                        } else
                            Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                    })

                }
            }
        }
    }

    private fun saveChange() {
        val format: Format = SimpleDateFormat("yyyy-MM-dd")
        val name = edtseasonNameAr.text.toString().trim { it <= ' ' }
        val nameEn = edtseasonNameEn.text.toString().trim { it <= ' ' }
        val start = format.format(startD)
        val end = format.format(endD)
        season?.nameEn = nameEn
        season?.startSeason = start
        season?.endSeason = end

        if (name != "") {
            if (start != "") {
                if (end != "") {
                    season.run {
                        this?.nameAr = name
                        this?.nameEn = nameEn
                        this?.startSeason = start
                        this?.endSeason = end
                    }

                    season?.let {
                        updateWeek(it.id, it).observe(this, androidx.lifecycle.Observer {
                            if (it.code() == 201){
                                Toast.makeText(
                                    this,
                                    "Season Edited successfully",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                finish()
                            } else
                                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                        })
                    }

                }
            }
        }

    }

    private fun getSeason(id: Int) {
        getWeek(id).observe(this, androidx.lifecycle.Observer {
            if (it.code()==200){
                season = it.body()
                edtseasonEnd.text = it.body()?.endSeason
                edtseasonNameAr.setText( it.body()?.nameAr)
                edtseasonNameEn.setText(it.body()?.nameEn)
                edtseasonStart.setText(it.body()?.startSeason)
                var startDate = it.body()?.startSeason.toString()
                var endDate = it.body()?.endSeason.toString()
                val format = SimpleDateFormat("yyyy-MM-dd")
                startD = format.parse(startDate)
                endD = format.parse(endDate)
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun selectDate(edtMonth: TextView, monthD: String?, b: Boolean ) {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        if (monthD != null) {
            calendar.time = format.parse(monthD)
        }
        cDate = Calendar.getInstance()
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { datePicker, i, i1, i2 ->
                cDate.set(i, i1, i2)

                edtMonth.text = format.format(cDate.getTime())
                if (b) {
                    startD = cDate.getTime()
                } else {
                    endD = cDate.getTime()
                }
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    lateinit var retService: SportService

    fun addMonth(monthModel: AddSeasonModel): LiveData<Response<Season>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Season>> = liveData {
            val response = retService.addSeason(monthModel)
            emit(response)
        }

        return responseLiveData
    }

    fun updateWeek(id: Int,month: Season): LiveData<Response<Season>> {

        val responseLiveData: LiveData<Response<Season>> = liveData {
            val response = retService.PutSeason(id,month)
            emit(response)
        }

        return responseLiveData
    }

    fun getWeek(id:Int): LiveData<Response<Season>> {

        val responseLiveData: LiveData<Response<Season>> = liveData {
            val response = retService.getSeason(id)
            emit(response)
        }

        return responseLiveData
    }
}
