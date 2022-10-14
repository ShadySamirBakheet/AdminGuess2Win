package shady.samir.adminetwak3kora.UsersScore.Fragments.LeagueScore

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shady.samir.adminetwak3kora.Adapters.DataAdapters.ScoreAdapter
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.LeagueWinnerModel
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel

import shady.samir.adminetwak3kora.R

class LeagueScoreFragment(var league : LeaguesResponseModel.League?) : Fragment() {

    lateinit var leaguescorelist:RecyclerView
    private lateinit var viewModel: LeagueScoreViewModel

    lateinit var leagueWinnerModel: LeagueWinnerModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.league_score_fragment, container, false)

        leaguescorelist = root.findViewById(R.id.leaguescorelist)

        viewModel = LeagueScoreViewModel()

        leaguescorelist.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        leaguescorelist.layoutManager = layoutManager

        league?.id?.let {
            viewModel.responseLeagueList(it).observe(this, Observer {

                if (it.code() == 200) {
                    leagueWinnerModel = it.body()!!
                    val adapter : ScoreAdapter? = context?.let { ScoreAdapter(it,leagueWinnerModel) }
                    leaguescorelist.setAdapter(adapter)
                } else {
                    Toast.makeText(requireContext(), it.message(), Toast.LENGTH_LONG).show()
                }

            })
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeagueScoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
