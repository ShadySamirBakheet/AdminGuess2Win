package shady.samir.adminetwak3kora.AddActivities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import shady.samir.adminetwak3kora.Adapters.ArrayAdapter.TeamArrayAdapter
import shady.samir.adminetwak3kora.Adapters.ArrayAdapter.TeamLeagueClickListener
import shady.samir.adminetwak3kora.Adapters.DataAdapters.TeamLeagueAdapter
import shady.samir.adminetwak3kora.AddActivities.ActivitiesViewModel.AddTeamToLeagueViewModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Teams.TeamsResponseModel
import shady.samir.adminetwak3kora.ViewHelper.HelperView
import shady.samir.adminetwak3kora.R

class AddTeamToLeagueActivity : AppCompatActivity() {

    lateinit var teamleaguelist: RecyclerView
    lateinit var edtTeam: Spinner
    lateinit var btnsave: FloatingActionButton
    private lateinit var viewModel: AddTeamToLeagueViewModel

    var leagueID: Int = 0
    var teamID: Int = -1
    var teamList:List<TeamsResponseModel.Team> = listOf()

    lateinit var helper: HelperView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_team_to_league)
        viewModel = AddTeamToLeagueViewModel()
        viewModel = ViewModelProviders.of(this).get(AddTeamToLeagueViewModel::class.java)

        teamleaguelist = findViewById(R.id.teamleaguelist)
        edtTeam = findViewById(R.id.edtTeam)
        btnsave = findViewById(R.id.btnsave)

        helper = HelperView()

        teamleaguelist.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        teamleaguelist.layoutManager = layoutManager

        supportActionBar?.title = "Add Team To "

        if (intent.getIntExtra("id", 0) > 0) {
            leagueID = intent.getIntExtra("id", 0)
            getLeague(leagueID)
            getTeams(leagueID)
            getTeams()
            getAllTeams()
            btnsave.setOnClickListener {
                if (teamID != -1) {
                    addTeamLeague(leagueID, teamID)
                } else {
                    helper.toastAShow(this, this, "choose team", R.drawable.ic_info)
                }
            }

//            edtTeam.setOnItemClickListener { parent, view, position, id ->
//                val ss = parent.getItemAtPosition(position)
//                teamID = 0
//
//            }

            edtTeam.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    teamID = teamList[position].id ?: -1
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //TODO("Not yet implemented")
                }
            }

        } else {
            helper.toastAShow(this, this, "Error !!!", R.drawable.ic_info)
        }

    }

    private fun addTeamLeague(leagueID: Int, teamID: Int) {
        viewModel.addTeamToLeague(leagueID.toString(), teamID.toString()).observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "team added successfully", Toast.LENGTH_LONG).show()
                getTeams()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getTeams() {
        viewModel.responseTeamsList(leagueID.toString()).observe(this, Observer {
            if (it.code() == 200) {
                val adapter = TeamLeagueAdapter(this, it.body()?.data!!, leagueID.toString())
                teamleaguelist.adapter =adapter
                adapter.teamLeagueClickListener = object :TeamLeagueClickListener{
                    override fun deleteTeam(teamId: String) {
                        this@AddTeamToLeagueActivity.deleteTeam(teamId)
                    }
                }

                teamleaguelist.layoutManager =
                    LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun getAllTeams() {
        viewModel.getAllTeams().observe(this, Observer { res ->
            if (res.code() == 200) {
                teamList = res.body()?.data!!
                edtTeam.setAdapter(TeamArrayAdapter(this, res.body()?.data!!))
            } else {
                Toast.makeText(this, res.message(), Toast.LENGTH_LONG).show()
            }
        })
    }

    fun deleteTeam(teamId:String){
        viewModel.deleteTeam(leagueID.toString(),teamId).observe(this, Observer {
            if (it.code() == 200) {
                Toast.makeText(this, "team deleted successfully", Toast.LENGTH_LONG).show()
                getTeams()
            } else {
                Toast.makeText(this, it.message(), Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getTeams(leagueID: Int) {

    }

    private fun getLeague(leagueID: Int) {

    }
}
