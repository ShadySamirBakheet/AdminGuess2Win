package shady.samir.adminetwak3kora.AddActivities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import retrofit2.Response
import shady.samir.adminetwak3kora.Adapters.ArrayAdapter.LeagueArrayAdapter
import shady.samir.adminetwak3kora.Adapters.ArrayAdapter.TeamArrayAdapter
import shady.samir.adminetwak3kora.AddActivities.ActivitiesViewModel.AddMatchViewModel
import shady.samir.adminetwak3kora.LogInSystem.model.MatchParcelableModel
import shady.samir.adminetwak3kora.Models.DataModel.Match
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeagueResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.AddMatchRequestModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.AddMatchResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.DeleteMatchResponseModel
import shady.samir.adminetwak3kora.Models.ResponseModel.MatchResponse.MatchModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.Repositories.API.RetrofitInstance
import shady.samir.adminetwak3kora.Repositories.API.SportService
import java.sql.Time
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class AddMatchActivity : AppCompatActivity() {
    lateinit var btndelete: Button
    lateinit var btnsave: Button
    lateinit var edtmatchTeamOne: Spinner
    lateinit var edtmatchTeamTwo: Spinner
    lateinit var edtmatchLeague: Spinner
    lateinit var edtmatchDateStart: TextView
    var addMatchRequestModel: AddMatchRequestModel = AddMatchRequestModel()
    lateinit var match: Match
    lateinit var getMatch: MatchModel

    lateinit var retService: SportService

    var teamOne: Int = -1
    var teamTwo: Int = -1
    var leagueID = -1


    var isFinsh = false

    lateinit var date: Calendar
    lateinit var cDate: Calendar
    var teamList: List<TeamsResponseModel.Team> = ArrayList()
    var leagueList: List<LeaguesResponseModel.League> = ArrayList()

    var dateMatch: Date? = null

    lateinit var team1: TeamsResponseModel.Team
    lateinit var team2: TeamsResponseModel.Team
    lateinit var league: LeaguesResponseModel.League

    lateinit var leaguesResponseModel: LeaguesResponseModel
    lateinit var leagueResponseModel: LeagueResponseModel
    lateinit var teamsResponseModel: TeamsResponseModel


    private lateinit var viewModel: AddMatchViewModel
    var matchModel: MatchParcelableModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_match)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)

        viewModel = getViewModel()

        inti()

        edtmatchDateStart.setOnClickListener { setDate() }
        matchModel = intent.getParcelableExtra("match")
        if (matchModel != null) {
            supportActionBar!!.title = "Update Match"
            getMatch(intent.getIntExtra("id", 0))
            btndelete.visibility = View.VISIBLE
            btndelete.setOnClickListener(View.OnClickListener { deleteMatch() })
            btnsave.setOnClickListener {
                saveChange()
            }
            Log.e("ADD_MATCH", "here1")
            edtmatchDateStart.text = matchModel?.date?.replace("T", " ")
        } else if (intent.getIntExtra("leagueID", -1) > -1) {
            supportActionBar!!.title = "Add New Match"
            val s = intent.getIntExtra("leagueID", -1)
            btndelete.visibility = View.GONE
            btnsave.setOnClickListener {
                Log.e("ADD_MATCH", "here2 save")
                saveData()
            }
            findleague(edtmatchLeague, s)
            leagueID = s
            Log.e("ADD_MATCH", "here2")
        } else {
            supportActionBar!!.title = "Add New Match"
            btndelete.visibility = View.GONE
            btnsave.setOnClickListener {
                Log.e("ADD_MATCH", "here3 save")
                saveData()
            }
            Log.e("ADD_MATCH", "here3")
        }
    }

    private fun inti() {
        btndelete = findViewById(R.id.btndelete)
        btnsave = findViewById(R.id.btnsave)
        edtmatchLeague = findViewById(R.id.edtmatchLeague)
        edtmatchTeamTwo = findViewById(R.id.edtmatchTeamTwo)
        edtmatchTeamOne = findViewById(R.id.edtmatchTeamOne)
        edtmatchDateStart = findViewById(R.id.edtmatchDateStart)

        getListTeamALeague()

        ClickListener()
    }

    private fun getListTeamALeague() {
        viewModel.responseLeagueList().observe(this, Observer { res ->
            if (res.code() == 200) {
                leaguesResponseModel = res.body()!!
                leagueList = leaguesResponseModel.data!!
                val adapter =
                    LeagueArrayAdapter(leagueList, this)
                edtmatchLeague.setAdapter(adapter)
                matchModel?.run {
                    val index = leagueList.indexOf(leagueList.filter { it.id == leagueId }[0])
                    edtmatchLeague.setSelection(index)
                }
            } else {
                Toast.makeText(this, res.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getTeamsByLeague() {
        viewModel.responseTeamList(league).observe(this, Observer { res ->
            if (res.code() == 200) {

                teamList = res.body()?.data ?: emptyList()

                if (!teamList.isEmpty()) {
                    val adapter =
                        TeamArrayAdapter(this, teamList!!)
                    edtmatchTeamOne.setAdapter(adapter)
                    edtmatchTeamTwo.setAdapter(adapter)
                    matchModel?.run {
                        val indexTeamOne =
                            teamList.indexOf(teamList.filter { it.id == teamOneId }[0])
                        edtmatchTeamOne.setSelection(indexTeamOne)

                        val indexTeamTwo =
                            teamList.indexOf(teamList.filter { it.id == teamTwoId }[0])
                        edtmatchTeamOne.setSelection(indexTeamTwo)
                    }
                }

            } else {
                Toast.makeText(this, res.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun ClickListener() {

        edtmatchTeamOne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                team1 = teamList.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }

        edtmatchTeamTwo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                team2 = teamList.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // TODO("Not yet implemented")
            }
        }

        edtmatchLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                league = leagueList.get(position)
                getTeamsByLeague()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }
        }
    }

    private fun getMatch(id: Int) {

    }


    private fun findleague(
        edtmatchLeague: Spinner, matchLeague: Int
    ) {


    }

    private fun findteam(
        edtmatchTeamOne: AutoCompleteTextView, matchTeamOne: String
    ) {

    }

    private fun deleteMatch() {
        deleteMatchApi().observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "match deleted successfully", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveChange() {
        teamOne = team1.id ?: -1
        teamTwo = team2.id ?: -1
        leagueID = league.id ?: -1
        try {
            if (teamOne != -1) {
                if (teamTwo != -1) {
                    if (leagueID != -1) {
                        val format: Format = SimpleDateFormat("yyyy-MM-dd")
                        val format2: Format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")

                        addMatchRequestModel.run {
                            id = matchModel?.matchId
                            firstTeamId = teamOne
                            secondTeamId = teamTwo
                            leagueId = leagueID
                            isStarted = matchModel?.isStarted == 1
                            isEnded = matchModel?.isEnded == 1
                            date = matchModel?.date?.split("T")?.get(0)
                            time = matchModel?.date
                            noOfFirstTeamGoals =
                                if (matchModel?.noOfGoalsTeam1 == -1) null else matchModel?.noOfGoalsTeam1
                            noOfSecondTeamGoals =
                                if (matchModel?.noOfGoalsTeam1 == -1) null else matchModel?.noOfGoalsTeam1
                        }

//                        parseMatch()
//
                        updateReward().observe(this, androidx.lifecycle.Observer {
                            if (it.code() == 200) {
                                Toast.makeText(
                                    this,
                                    "match updated successfully",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                finish()
                            } else
                                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                        })


                    } else {
                        Toast.makeText(
                            this@AddMatchActivity,
                            "please fill all filed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@AddMatchActivity,
                        "please fill all filed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                Toast.makeText(this@AddMatchActivity, "please fill all filed", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            Toast.makeText(this@AddMatchActivity, "please fill all filed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun parseMatch() {
        var isSt: Boolean = false
        if (match.matchDateStart.equals(Date())) {
            isSt = true
        }
        getMatch = MatchModel(
            match.matchDateStart,
            match.matchTeamOne,
            match.matchID,
            match.finsh,
            isSt,
            match.matchLeague,
            null,
            null,
            match.matchTeamTwo,
            Time(match.matchDateStart.time)
        )

    }

    private fun saveData() {
        teamOne = team1.id ?: -1
        teamTwo = team2.id ?: -1
        leagueID = league.id ?: -1
        try {
            if (teamOne != -1) {
                if (teamTwo != -1) {
                    if (leagueID != -1 && dateMatch != null) {
                        val format: Format = SimpleDateFormat("yyyy-MM-dd")
                        val format2: Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

                        addMatchRequestModel.run {
                            firstTeamId = teamOne
                            secondTeamId = teamTwo
                            leagueId = leagueID
                            isStarted = false
                            isEnded = false
                            date = format.format(dateMatch)
                            time = format2.format(dateMatch)
                            noOfFirstTeamGoals = null
                            noOfSecondTeamGoals = null
                        }

                        addMatch().observe(this, androidx.lifecycle.Observer {
                            if (it.code() == 200) {
                                Toast.makeText(
                                    this,
                                    "match added successfully",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                finish()
                            } else
                                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
                        })
                    } else {
                        Toast.makeText(
                            this@AddMatchActivity,
                            "please fill all filed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@AddMatchActivity,
                        "please fill all filed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                Toast.makeText(this@AddMatchActivity, "please fill all filed", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            Toast.makeText(this@AddMatchActivity, "please fill all filed", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setDate() {
        cDate = Calendar.getInstance()
        date = Calendar.getInstance()
        DatePickerDialog(
            this,
            OnDateSetListener { datePicker, i, i1, i2 ->
                date[i, i1] = i2
                selectTime()
            },
            cDate[Calendar.YEAR],
            cDate[Calendar.MONTH],
            cDate[Calendar.DAY_OF_MONTH]
        ).show()
    }

    private fun selectTime() {
        cDate = Calendar.getInstance()
        TimePickerDialog(this, OnTimeSetListener { timePicker, ho, mi ->
            date[Calendar.HOUR_OF_DAY] = ho
            date[Calendar.MINUTE] = mi
            val format: Format = SimpleDateFormat("dd/MM/yyyy HH:mm")
            edtmatchDateStart.text = format.format(date.time)
            dateMatch = date.time
        }, cDate[Calendar.HOUR_OF_DAY], cDate[Calendar.MINUTE], false).show()
    }


    fun addMatch(): LiveData<Response<AddMatchResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<AddMatchResponseModel>> = liveData {
            val response = retService.PostMatch(addMatchRequestModel)
            emit(response)
        }

        return responseLiveData
    }

    fun updateReward(): LiveData<Response<DeleteMatchResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<DeleteMatchResponseModel>> = liveData {
            val response = retService.PutMatch(matchModel?.matchId.toString(), addMatchRequestModel)
            emit(response)
        }

        return responseLiveData
    }

    fun deleteMatchApi(): LiveData<Response<DeleteMatchResponseModel>> {
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(SportService::class.java)
        val responseLiveData: LiveData<Response<DeleteMatchResponseModel>> = liveData {
            val response = retService.DeleteMatch(matchModel?.matchId.toString())
            emit(response)
        }

        return responseLiveData
    }

    private fun getViewModel(): AddMatchViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return AddMatchViewModel() as T
            }
        })[AddMatchViewModel::class.java]
    }

}
