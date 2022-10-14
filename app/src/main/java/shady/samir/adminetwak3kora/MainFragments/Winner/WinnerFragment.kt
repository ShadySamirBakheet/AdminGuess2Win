package shady.samir.adminetwak3kora.MainFragments.Winner

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import shady.samir.adminetwak3kora.Adapters.TabAdapters.HomeTabAdapter
import shady.samir.adminetwak3kora.MainFragments.League.LeagueFragment
import shady.samir.adminetwak3kora.MainFragments.Matches.MatchesFragment
import shady.samir.adminetwak3kora.MainFragments.ResultsMatches.ResultsMatchesFragment
import shady.samir.adminetwak3kora.MainFragments.Team.TeamFragment

import shady.samir.adminetwak3kora.R
import shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerLeague.WinnerLeagueFragment
import shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerMonth.WinnerMonthFragment
import shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerSeason.WinnerSeasonFragment
import shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerSeason.WinnerSeasonViewModel
import shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerWeek.WinnerWeekFragment

class WinnerFragment : Fragment() {

    lateinit var tablayout: TabLayout
    lateinit var viewpager: ViewPager
    lateinit var adapter: HomeTabAdapter

    companion object {
        fun newInstance() = WinnerFragment()
    }

    private lateinit var viewModel: WinnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.winner_fragment, container, false)

        tablayout = root.findViewById(R.id.tablayout)
        viewpager = root.findViewById(R.id.viewpager)

        adapter = HomeTabAdapter(childFragmentManager)

        adapter.addFragment(WinnerLeagueFragment(),"Leagues")
        adapter.addFragment(WinnerSeasonFragment(),"Seasons")
        adapter.addFragment(WinnerMonthFragment(),"Months")
        adapter.addFragment(WinnerWeekFragment(), "Weeks")

        viewpager.adapter = adapter;
        tablayout.setupWithViewPager(viewpager)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WinnerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
