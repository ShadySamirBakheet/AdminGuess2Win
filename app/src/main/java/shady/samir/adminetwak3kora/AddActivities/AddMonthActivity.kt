package shady.samir.adminetwak3kora.AddActivities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.ResponseModel.AddMonthModel
import shady.samir.adminetwak3kora.Models.DataModel.Month
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.text.SimpleDateFormat
import java.util.*

class AddMonthActivity : AppCompatActivity() {

    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var edtMonthName: EditText
    lateinit var edtMonthNameEn:EditText
    lateinit  var edtMonthStart: TextView
    lateinit  var edtMonthEnd:TextView

    var month: Month? = null
    lateinit var cDate: Calendar
    var startDate: String? = null
    var endDate:String? = null
    var startD:Date?=null
    var endD:Date?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_month)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        btnsave = findViewById(R.id.btnsave)
        btndelete = findViewById(R.id.btndelete)

        edtMonthName = findViewById(R.id.edtseasonNameAr)
        edtMonthStart = findViewById(R.id.edtseasonStart)
        edtMonthEnd = findViewById(R.id.edtseasonEnd)
        edtMonthNameEn = findViewById(R.id.edtseasonNameEn)

        edtMonthEnd.setOnClickListener {
            if (month != null) {
                selectDate(edtMonthEnd, null, false)
            } else {
                selectDate(edtMonthEnd, null, false)
            }
        }
        edtMonthStart.setOnClickListener {
            if (month != null) {
                selectDate(edtMonthStart, null, true)
            } else {
                selectDate(edtMonthStart, null, true)
            }
        }

        if (intent.getIntExtra("id",0) > 0) {
            supportActionBar!!.title = "Update Month"
            getMonth(intent.getIntExtra("id",0))
            btnsave.setOnClickListener { saveChange() }
            btndelete.setVisibility(View.VISIBLE)
            btndelete.setOnClickListener(View.OnClickListener { deleteSeason(intent.getIntExtra("id",0)) })
        } else {
            supportActionBar!!.setTitle("Add New Month")
            btnsave.setOnClickListener { saveData() }
            btndelete.setVisibility(View.GONE)
        }
    }

    private fun deleteSeason(id: Int) {

        delWeek(id).observe(this, androidx.lifecycle.Observer {
            if (it.code()==200){
                Toast.makeText(this,"Deleted Month "+month?.monthNameEn,Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
            }
        })

    }

    fun delWeek(id:Int): LiveData<Response<Month>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Month>> = liveData {
            val response = retService.DelMonth(id)
            emit(response)
        }

        return responseLiveData
    }

    private fun saveData() {
        val name = edtMonthName.text.toString().trim { it <= ' ' }
        val nameEn = edtMonthNameEn.text.toString().trim { it <= ' ' }
        val start =startDate!!
        val end = endDate!!
        if (start != "") {
            if (end != "") {
                val monthModel =
                    AddMonthModel(
                        start,
                        end,
                        name,nameEn
                    )
                addMonth(monthModel).observe(this, androidx.lifecycle.Observer {
                    if (it.code() == 201){
                        Toast.makeText(
                            this,
                            "Month added successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        edtMonthName.setText("")
                        edtMonthNameEn.setText("")
                        edtMonthStart.setText("")
                        edtMonthEnd.setText("")
                    } else
                        Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private fun saveChange() {
        val name = edtMonthName.text.toString().trim { it <= ' ' }
        val nameEn = edtMonthNameEn.text.toString().trim { it <= ' ' }
        val start = startDate!!
        val end = endDate!!
        month?.monthNameAr = name
        month?.monthNameEn = nameEn
        month?.monthStart = start.toString()
        month?.monthEnd=end.toString()

        if (start != "") {
            if (end != "") {
                val monthModel =
                    AddMonthModel(
                        start,
                        end,
                        name,nameEn
                    )
                month?.let {
                    updateWeek(it.monthID, it).observe(this, androidx.lifecycle.Observer {
                        if (it.code() == 201){
                            Toast.makeText(
                                this,
                                "Month Edited successfully",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            edtMonthName.setText("")
                            edtMonthNameEn.setText("")
                            edtMonthStart.setText("")
                            edtMonthEnd.setText("")
                        } else
                            Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                    })
                }
            }
        }

    }

    private fun getMonth(id: Int) {
        getWeek(id).observe(this, androidx.lifecycle.Observer {
            if (it.code()==200){
                month = it.body()
                edtMonthEnd.text = it.body()?.monthEnd
                edtMonthStart.text = it.body()?.monthStart
                edtMonthName.setText(it.body()?.monthNameAr)
                edtMonthNameEn.setText(it.body()?.monthNameEn)
                startDate = it.body()?.monthStart.toString()
                endDate = it.body()?.monthEnd.toString()
                val format = SimpleDateFormat("yyyy-MM-dd")
                startD = format.parse(startDate)
                endD = format.parse(endDate)
            }else{
                Toast.makeText(this,it.message(),Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun selectDate(edtMonth: TextView, monthD: Date?, b: Boolean ) {
        val calendar = Calendar.getInstance()
        if (monthD != null) {
            calendar.time = monthD
        }
        cDate = Calendar.getInstance()
        DatePickerDialog(
            this,
            OnDateSetListener { datePicker, i, i1, i2 ->
                cDate.set(i, i1, i2)

                val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                edtMonth.text = format.format(cDate.time)
                if (b) {
                    startDate =format.format(cDate.time)
                    startD = cDate.time
                } else {
                    endDate = format.format(cDate.time)
                    endD = cDate.time
                }
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    lateinit var retService: SportService

    fun addMonth(monthModel: AddMonthModel): LiveData<Response<Month>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Month>> = liveData {
            val response = retService.addMonth(monthModel)
            emit(response)
        }

        return responseLiveData
    }

    fun updateWeek(id: Int,month: Month): LiveData<Response<Month>> {

        val responseLiveData: LiveData<Response<Month>> = liveData {
            val response = retService.PutMonth(id,month)
            emit(response)
        }

        return responseLiveData
    }

    fun getWeek(id:Int): LiveData<Response<Month>> {

        val responseLiveData: LiveData<Response<Month>> = liveData {
            val response = retService.getMonth(id)
            emit(response)
        }

        return responseLiveData
    }
}
