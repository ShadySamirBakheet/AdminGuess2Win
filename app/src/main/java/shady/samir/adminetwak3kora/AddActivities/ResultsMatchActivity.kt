package shady.samir.adminetwak3kora.AddActivities

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response
import shady.samir.adminetwak3kora.Models.DataModel.Match
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.MatchModel
import shady.samir.adminetwak3kora.Models.ResultMatch
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService

class ResultsMatchActivity : AppCompatActivity() {
    lateinit var retService: SportService
    var scoreone = 0
    var scoretwo: Int = 0
    lateinit var scoreteamtwo: NumberPicker
    lateinit var scoreteamone: NumberPicker
    lateinit var txtteamtwo: TextView
    lateinit var txtteamone: TextView
    lateinit var btnsave: Button
    lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_match)
        inti()
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        intent.getParcelableExtra<ResultMatch>("match")?.run {
            txtteamone.text = firstTeam
            txtteamtwo.text = secondTeam
        }

        if (intent.getIntExtra("id", 0) > 0) {
            supportActionBar?.title = "Add Result Match"

            getMatch(intent.getIntExtra("id", 0))

        }

    }


    private fun getMatch(id: Int) {

    }

    private fun getTeamName(txtteamone: TextView, matchTeamOne: String) {

    }

    private fun inti() {
        scoreteamtwo = findViewById(R.id.scoretxtteamtwo)
        scoreteamone = findViewById(R.id.scoreteamone)
        txtteamtwo = findViewById(R.id.txtteamtwo)
        txtteamone = findViewById(R.id.txtteamone)
        btnsave = findViewById(R.id.btnsave)
        scoreteamtwo.maxValue = 50
        scoreteamtwo.minValue = 0
        scoreteamtwo.run {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                scoretwo = newVal
            }
        }

        scoreteamone.maxValue = 50
        scoreteamone.minValue = 0
        scoreteamone.run {
            maxValue = 100
            minValue = 0
            setOnValueChangedListener { picker, oldVal, newVal ->
                scoreone = newVal
            }
        }
        btnsave.setOnClickListener { saveScore() }
    }

    private fun saveScore() {
//        match.matchResultTeamOne = scoreone
//        match.matchResultTeamTwo = scoretwo
//        match.finsh = false
        addMatchResult().observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "result added successfully", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addMatchResult(): LiveData<Response<Void>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<Void>> = liveData {
            val response = retService.addMatchResult(
                intent.getIntExtra("id", 0).toString(),
                scoreone.toString(),
                scoretwo.toString()
            )
            emit(response)
        }

        return responseLiveData
    }
}
