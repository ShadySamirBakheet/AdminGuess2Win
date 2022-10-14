package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerWeek

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
import shady.samir.adminetwak3kora.Adapters.TabAdapters.WeekScoreTabAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Week

import shady.samir.adminetwak3kora.R

class WinnerWeekFragment : Fragment() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    var seasonList : List<Week>? =  null
    companion object {
        fun newInstance() = WinnerWeekFragment()
    }

    private lateinit var viewModel: WinnerWeekViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.winner_week_fragment, container, false)
        tabLayout = root.findViewById(R.id.tablayout)
        viewPager = root.findViewById(R.id.viewpager)

        viewModel = WinnerWeekViewModel()

        viewModel.responseLeagueList().observe(this, Observer {

            if (it.code() == 200){
                seasonList = it.body()
                val adapter = WeekScoreTabAdapter(childFragmentManager,  seasonList)
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
        viewModel = ViewModelProviders.of(this).get(WinnerWeekViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
