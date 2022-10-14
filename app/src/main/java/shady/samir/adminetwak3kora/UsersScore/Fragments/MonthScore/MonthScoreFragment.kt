package shady.samir.adminetwak3kora.UsersScore.Fragments.MonthScore

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
import shady.samir.adminetwak3kora.Models.DataModel.Month
import shady.samir.adminetwak3kora.Models.ResponseModel.Winners.MonthWinnerModel

import shady.samir.adminetwak3kora.R

class MonthScoreFragment(var month: Month) : Fragment() {

    lateinit var leaguescorelist: RecyclerView


    lateinit var leagueWinnerModel: MonthWinnerModel
    private lateinit var viewModel: MonthScoreViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val root = inflater.inflate(R.layout.month_score_fragment, container, false)
        leaguescorelist = root.findViewById(R.id.monthscorelist)

        viewModel = MonthScoreViewModel()

        leaguescorelist.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        leaguescorelist.layoutManager = layoutManager


            viewModel.responseLeagueList(month.monthID).observe(this, Observer {
                if (it.code() == 200) {
                    leagueWinnerModel = it.body()!!
                    val adapter : ScoreAdapter? = context?.let { ScoreAdapter(it,leagueWinnerModel) }
                    leaguescorelist.setAdapter(adapter)
                } else {
                    Toast.makeText(requireContext(), it.message(), Toast.LENGTH_LONG).show()
                }


            })


        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MonthScoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
