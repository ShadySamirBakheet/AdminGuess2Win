package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerSeason

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import shady.samir.adminetwak3kora.Adapters.TabAdapters.SeasonScoreTabAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Season

import shady.samir.adminetwak3kora.R

class WinnerSeasonFragment : Fragment() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    var seasonList : List<Season>? =  null
    companion object {
        fun newInstance() = WinnerSeasonFragment()
    }

    private lateinit var viewModel: WinnerSeasonViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.winner_season_fragment, container, false)
        tabLayout = root.findViewById(R.id.tablayout)
        viewPager = root.findViewById(R.id.viewpager)

        viewModel = WinnerSeasonViewModel()

        viewModel.responseLeagueList().observe(this, Observer {

            if (it.code() == 200){
                seasonList = it.body()
                val adapter = SeasonScoreTabAdapter(childFragmentManager,  seasonList)
                viewPager.adapter = adapter
                tabLayout.setupWithViewPager(viewPager)
            }
            else{
                Toast.makeText(requireContext(),it.message(), Toast.LENGTH_LONG).show()
            }

        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WinnerSeasonViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
