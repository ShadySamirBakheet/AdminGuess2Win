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
import shady.samir.adminetwak3kora.Models.ResponseModel.AddWeekModel
import shady.samir.adminetwak3kora.Models.DataModel.Week
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddWeekActivity : AppCompatActivity() {
    lateinit var btndelete: Button
    lateinit var btnsave: Button

    lateinit var edtWeekNameAr: EditText
    lateinit var edtWeekNameEn: EditText
    lateinit var edtWeekStart: TextView
    lateinit var edtWeekEnd: TextView

    var week: Week? = null
    lateinit var cDate: Calendar
    lateinit var startDate: String
    lateinit var endDate: String
    lateinit var endD:Date
    lateinit var startD:Date

    lateinit var retService: SportService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_week)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        btnsave = findViewById(R.id.btnsave)
        btndelete = findViewById(R.id.btndelete)
        edtWeekNameAr = findViewById(R.id.edtweekNameAr)
        edtWeekNameEn = findViewById(R.id.edtWeekNameEn)
        edtWeekStart = findViewById(R.id.edtWeekStart)
        edtWeekEnd = findViewById(R.id.edtWeekEnd)


        edtWeekEnd.setOnClickListener {
            if (week != null) {
                selectDate(edtWeekEnd, endD, false)
            } else {
                selectDate(edtWeekEnd, null, false)
            }

        }
        edtWeekStart.setOnClickListener {
            if (week != null) {
                selectDate(edtWeekStart, startD, true)
            } else {
                selectDate(edtWeekStart, null, true)
            }
        }

        if (intent.getIntExtra("id", 0) > 0) {
            supportActionBar!!.title = "Update Week"
            getSeason(intent.getIntExtra("id", 0))
            btnsave.setOnClickListener { saveChange() }
            btndelete.setVisibility(View.VISIBLE)
            btndelete.setOnClickListener(View.OnClickListener { deleteSeason(intent.getIntExtra("id", 0)) })
        } else {
            supportActionBar!!.setTitle("Add New Week")
            btnsave.setOnClickListener { saveData() }
            btndelete.setVisibility(View.GONE)
        }

    }

    private fun deleteSeason(id: Int) {

        delWeek(id).observe(this, androidx.lifecycle.Observer {
            if (it.code()==200){
                Toast.makeText(this,"Deleted Week "+week?.weekNameEn,Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
            }
        })

    }

    fun delWeek(id:Int): LiveData<Response<Week>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Week>> = liveData {
            val response = retService.DelWeek(id)
            emit(response)
        }

        return responseLiveData
    }

    private fun saveData() {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val name = edtWeekNameAr.text.toString().trim { it <= ' ' }
        val nameEn = edtWeekNameEn.text.toString().trim { it <= ' ' }
        val start = format.format(startD)
        val end =format.format(endD)
        if (start != "") {
            if (end != "") {
                if (!name.equals("")){
                    if (!nameEn.equals("")){
                        val model =
                            AddWeekModel(
                                (start),
                                (end),
                                name,
                                nameEn
                            )
                        addWeek(model).observe(this, androidx.lifecycle.Observer {
                            if (it.code() == 201) {
                                Toast.makeText(
                                    this,
                                    "week added successfully",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                finish()
                                edtWeekNameAr.setText("")
                                edtWeekNameEn.setText("")
                                edtWeekStart.setText("")
                                edtWeekEnd.setText("")
                            } else
                                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                        })
                    }
                }
            }
        }
    }


    private fun saveChange() {
        val name = edtWeekNameAr.text.toString().trim { it <= ' ' }
        val nameEn = edtWeekNameEn.text.toString().trim { it <= ' ' }
        val start = startDate!!
        val end = endDate!!
        week?.weekNameAr = name
        week?.weekNameEn = nameEn
        week?.weekStart = start
        week?.weekEnd = end

        if (start != "") {
            if (end != "") {
                if (!name.equals("")){
                    if (!nameEn.equals("")){

                        week?.let {
                            updateWeek(it.weekID, it).observe(this, androidx.lifecycle.Observer {
                                if (it.code() == 201) {
                                    Toast.makeText(
                                        this,
                                        "week Update successfully",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    finish()
                                    edtWeekNameAr.setText("")
                                    edtWeekNameEn.setText("")
                                    edtWeekStart.setText("")
                                    edtWeekEnd.setText("")
                                } else
                                    Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                            })
                        }
                    }
                }
            }
        }

    }

    private fun getSeason(id: Int) {
        getWeek(id).observe(this, androidx.lifecycle.Observer {
            if (it.code()==200){
                week = it.body()
                edtWeekEnd.text = it.body()?.weekEnd
                edtWeekStart.text = it.body()?.weekStart
                edtWeekNameAr.setText(it.body()?.weekNameAr)
                edtWeekNameEn.setText(it.body()?.weekNameEn)
                startDate = it.body()?.weekStart.toString()
                endDate = it.body()?.weekEnd.toString()
                val format = SimpleDateFormat("yyyy-MM-dd")
                startD = format.parse(startDate)
                endD = format.parse(endDate)
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun selectDate(edtMonth: TextView, monthD: Date?, b: Boolean) {
        val calendar = Calendar.getInstance()
        if (monthD != null) {
            calendar.time = monthD
        }
        cDate = Calendar.getInstance()
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cDate.set(year, month, dayOfMonth)

            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            edtMonth.text = format.format(cDate.time)

            if (b) {
                startDate = format.format(cDate.time)
                startD = cDate.time
                Toast.makeText(this,startD.toString(),Toast.LENGTH_LONG).show()

            } else {
                endDate = format.format(cDate.time)
                endD = cDate.time
                Toast.makeText(this,endD.toString(),Toast.LENGTH_LONG).show()
            }
        },    calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]).show()
    }

    fun addWeek(weekModel: AddWeekModel): LiveData<Response<Week>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Week>> = liveData {
            val response = retService.addWeek(weekModel)
            emit(response)
        }

        return responseLiveData
    }

    fun updateWeek(id: Int,week: Week): LiveData<Response<Week>> {

        val responseLiveData: LiveData<Response<Week>> = liveData {
            val response = retService.PutWeek(id,week)
            emit(response)
        }

        return responseLiveData
    }

    fun getWeek(id:Int): LiveData<Response<Week>> {

        val responseLiveData: LiveData<Response<Week>> = liveData {
            val response = retService.getWeek(id)
            emit(response)
        }

        return responseLiveData
    }
}
