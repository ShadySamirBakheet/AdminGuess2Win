package shady.samir.adminetwak3kora.UsersScore.Fragments.SeasonScore

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
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.SeasonWinnerModel

import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.UsersScore.Fragments.MonthScore.ScoreAdapter

class SeasonScoreFragment(var seasonId: Int): Fragment() {

    lateinit var leaguescorelist: RecyclerView

    lateinit var leagueWinnerModel: SeasonWinnerModel

    private lateinit var viewModel: SeasonScoreViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.season_score_fragment, container, false)
        leaguescorelist = root.findViewById(R.id.seasonscorelist)

        viewModel = SeasonScoreViewModel()

        leaguescorelist.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        leaguescorelist.layoutManager = layoutManager


        viewModel.responseLeagueList(seasonId).observe(this, Observer {
            if (it.code() == 200) {
                leagueWinnerModel = it.body()!!
                val adapter = context?.let { ScoreAdapter(it,leagueWinnerModel) }
                leaguescorelist.setAdapter(adapter)
            } else {
                Toast.makeText(requireContext(), it.message(), Toast.LENGTH_LONG).show()
            }


        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeasonScoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
