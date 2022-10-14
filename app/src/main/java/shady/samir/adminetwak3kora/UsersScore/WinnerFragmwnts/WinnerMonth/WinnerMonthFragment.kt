package shady.samir.adminetwak3kora.UsersScore.WinnerFragmwnts.WinnerMonth

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
import shady.samir.adminetwak3kora.Adapters.TabAdapters.MonthScoreTabAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Month

import shady.samir.adminetwak3kora.R

class WinnerMonthFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    var monthList : List<Month>? =  null

    companion object {
        fun newInstance() = WinnerMonthFragment()
    }

    private lateinit var viewModel: WinnerMonthViewModel

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.winner_month_fragment, container, false)
        tabLayout = root.findViewById(R.id.tablayout)
        viewPager = root.findViewById(R.id.viewpager)

        viewModel = WinnerMonthViewModel()

        viewModel.responseMonthList().observe(this, Observer {

            if (it.code() == 200){
                monthList = it.body()
                val adapter = MonthScoreTabAdapter(childFragmentManager,  monthList)
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
        viewModel = ViewModelProviders.of(this).get(WinnerMonthViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
