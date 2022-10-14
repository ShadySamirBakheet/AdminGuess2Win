package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerLeague

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import shady.samir.adminetwak3kora.Adapters.TabAdapters.LeagueScoreTabAdapter
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.R

class WinnerLeagueFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    var leagueList : LeaguesResponseModel? =  null

    companion object {
        fun newInstance() =
            WinnerLeagueFragment()
    }

    private lateinit var viewModel: WinnerLeagueViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.winner_league_fragment, container, false)

        tabLayout = root.findViewById(R.id.tablayout)
        viewPager = root.findViewById(R.id.viewpager)

        viewModel = WinnerLeagueViewModel()

        viewModel.responseLeagueList().observe(this, Observer {

            if (it.code() == 200){
                leagueList = it.body()
                val adapter = LeagueScoreTabAdapter(childFragmentManager,  leagueList)
                viewPager.adapter = adapter
                tabLayout.setupWithViewPager(viewPager)
            }
            else{
                Toast.makeText(requireContext(),it.message(),Toast.LENGTH_LONG).show()
            }

        })


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WinnerLeagueViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
