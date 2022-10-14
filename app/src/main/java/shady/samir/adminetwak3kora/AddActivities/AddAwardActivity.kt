package shady.samir.adminetwak3kora.AddActivities

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Award
import shady.samir.adminetwak3kora.Models.ResponseModel.Rewards.AddRewardResponseModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class AddAwardActivity : AppCompatActivity() {

    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var edtawardNameAr: EditText
    lateinit var edtawardNameEn: EditText
    lateinit var edtawardPoint: Spinner
    lateinit var edtawardPointto: Spinner
    lateinit var edtawardtype: Spinner
    lateinit var retService: SportService

    var edit = false
    var level: ArrayList<String> = ArrayList()
    var types: ArrayList<String> = ArrayList()
    var point:Int? = -1
    var pointto: Int? = -1
    var seltype: Int? = -1
    lateinit var award: Award
    lateinit var getReward: AddRewardResponseModel.Data


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_award)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        inti()

        if (intent.getIntExtra("id", 0) > 0) {
            supportActionBar?.title = "Update Award"

            getAward(intent.getIntExtra("id", 0)).observe(this, Observer {
                if (it.code() == 200) {
                    it.body()?.data?.run {
                        getReward = this
                        edtawardNameAr.setText(nameAr)
                        edtawardNameEn.setText(nameEn)
                        point = fromPoint
                        pointto = toPoint
                        seltype = type
                        edtawardtype.setSelection(type!!)
                        edtawardPoint.setSelection(fromPoint?.minus(1)!!)
                        edtawardPointto.setSelection(toPoint?.minus(1)!!)
                    }
                } else {
                    Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                }
            })
            btndelete.visibility = View.VISIBLE

            edit = true
            btnsave.setOnClickListener { saveChange() }
            btndelete.setOnClickListener {
                deleteAward()
            }

        } else {
            supportActionBar?.title = "Add Award"
            btndelete.visibility = View.GONE

            btnsave.setOnClickListener {
                saveData()
            }
        }

    }

    private fun inti() {

        edtawardNameAr = findViewById<EditText>(R.id.edtawardNameAr)
        edtawardNameEn = findViewById(R.id.edtawardNameEn)
        edtawardPoint = findViewById(R.id.edtawardPoint)
        edtawardtype = findViewById(R.id.edtawardtype)
        btnsave = findViewById(R.id.btnsave)
        btndelete = findViewById<Button>(R.id.btndelete)
        edtawardPointto = findViewById(R.id.edtawardPointto)

        level = java.util.ArrayList()
        level.add("Level 1")
        level.add("Level 2")
        level.add("Level 3")
        level.add("Level 4")
        level.add("Level 5")
        level.add("Level 6")
        level.add("Level 7")
        level.add("Level 8")
        level.add("Level 9")
        level.add("Level 10")
        level.add("Level 11")
        level.add("Level 12")
        level.add("Level 13")
        level.add("Level 14")
        level.add("Level 15")
        level.add("Level 16")
        level.add("Level 17")
        level.add("Level 18")
        level.add("Level 19")
        level.add("Level 20")
        level.add("Level 21")
        level.add("Level 22")
        level.add("Level 23")
        level.add("Level 24")
        level.add("Level 25")
        level.add("Level 26")
        level.add("Level 27")
        level.add("Level 28")
        level.add("Level 29")
        level.add("Level 30")
        level.add("Level 31")
        level.add("Level 32")
        level.add("Level 33")
        level.add("Level 34")
        level.add("Level 35")
        level.add("Level 36")
        level.add("Level 37")
        level.add("Level 38")
        level.add("Level 39")
        level.add("Level 40")
        level.add("Level 41")
        level.add("Level 42")
        level.add("Level 43")
        level.add("Level 44")
        level.add("Level 45")
        level.add("Level 46")
        level.add("Level 47")
        level.add("Level 48")
        level.add("Level 49")
        level.add("Level 50")
        types = ArrayList()
        types.add("For League")
        types.add("For Season")
        types.add("For Month")
        types.add("For Week")

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                level)
        edtawardPoint.setAdapter(adapter)
        edtawardPointto.setAdapter(adapter)
//        val adapter1: ArrayAdapter<String> =
//            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, types.toList())

        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            types)

        edtawardtype.setAdapter(adapter1)
        edtawardtype.onItemSelectedListener  =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    seltype = position
                }

            }

        edtawardPoint.onItemSelectedListener  =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    point = position+1
                }

            }
        edtawardPointto.onItemSelectedListener  =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    pointto = position+1
                }

            }
    }

    private fun saveData() {
        val nameAr: String = edtawardNameAr.text.toString().trim()
        val nameEn = edtawardNameEn.text.toString().trim()
        if (nameAr != "") {
            if (nameEn != "") {
                if (point!! > -1) {
                    if (pointto!! > -1) {
                        if (seltype!! > -1) {
                            award =
                                Award(
                                    nameAr,
                                    nameEn,
                                    point!!,
                                    pointto!!,
                                    seltype!!
                                )
                            addReward().observe(this, Observer {
                                if (it.code() == 200) {
                                    Toast.makeText(
                                        this,
                                        "reward added successfully",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                    edtawardNameAr.setText("")
                                    edtawardNameEn.setText("")
                                    edtawardPoint.setSelection(0)
                                    edtawardPointto.setSelection(0)
                                    edtawardtype.setSelection(0)
                                } else
                                    Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                            })

                        } else {
                            Toast.makeText(
                                this@AddAwardActivity,
                                "please fill all filed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@AddAwardActivity,
                            "please fill all filed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@AddAwardActivity,
                        "please fill all filed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this@AddAwardActivity, "please fill all filed", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this@AddAwardActivity, "please fill all filed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun deleteAward() {
        deleteReward().observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "reward deleted successfully", Toast.LENGTH_LONG)
                    .show()
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveChange() {
        val nameAr: String = edtawardNameAr.text.toString().trim()
        val nameEn = edtawardNameEn.text.toString().trim()
        if (!nameAr.equals("")) {
            if (!nameEn.equals("")) {
                if (point!! > -1) {
                    if (pointto!! > -1) {
                        if (seltype!! > -1) {
                            getReward.nameAr = nameAr
                            getReward.nameEn = nameEn
                            getReward.fromPoint = point
                            getReward.type = seltype
                            getReward.toPoint = pointto
                            updateReward().observe(this, Observer {
                                if (it.code() == 200) {
                                    Toast.makeText(
                                        this,
                                        "reward updated successfully",
                                        Toast.LENGTH_LONG
                                    )
                                        .show()
                                } else {
                                    Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                                }
                            })

                        } else {
                            Toast.makeText(
                                this@AddAwardActivity,
                                "please fill all filed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@AddAwardActivity,
                            "please fill all filed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@AddAwardActivity,
                        "please fill all filed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this@AddAwardActivity, "please fill all filed", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this@AddAwardActivity, "please fill all filed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getAward(id: Int): LiveData<Response<AddRewardResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddRewardResponseModel>> = liveData {
            val response = retService.updateReward(id.toString())
            emit(response)
        }

        return responseLiveData
    }

    fun addReward(): LiveData<Response<AddRewardResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddRewardResponseModel>> = liveData {
            val response = retService.addReward(award)
            emit(response)
        }

        return responseLiveData
    }

    fun updateReward(): LiveData<Response<AddRewardResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddRewardResponseModel>> = liveData {
            val response = retService.updateReward(getReward.id.toString(), getReward)
            emit(response)
        }

        return responseLiveData
    }

    fun deleteReward(): LiveData<Response<AddRewardResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddRewardResponseModel>> = liveData {
            val response = retService.deleteReward(getReward.id.toString())
            emit(response)
        }

        return responseLiveData
    }
}
