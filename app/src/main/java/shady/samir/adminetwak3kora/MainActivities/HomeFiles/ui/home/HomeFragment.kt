package shady.samir.adminetwak3kora.MainActivities.HomeFiles.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import shady.samir.adminetwak3kora.Adapters.TabAdapters.HomeTabAdapter
import shady.samir.adminetwak3kora.AddActivities.*
import shady.samir.adminetwak3kora.MainFragments.Award.AwardFragment
import shady.samir.adminetwak3kora.MainFragments.Helper.HelperFragment
import shady.samir.adminetwak3kora.MainFragments.League.LeagueFragment
import shady.samir.adminetwak3kora.MainFragments.Matches.MatchesFragment
import shady.samir.adminetwak3kora.MainFragments.Month.MonthFragment
import shady.samir.adminetwak3kora.MainFragments.Notification.NotificationFragment
import shady.samir.adminetwak3kora.MainFragments.Profiles.ProfilesFragment
import shady.samir.adminetwak3kora.MainFragments.ResultsMatches.ResultsMatchesFragment
import shady.samir.adminetwak3kora.MainFragments.Season.SeasonFragment
import shady.samir.adminetwak3kora.MainFragments.Team.TeamFragment
import shady.samir.adminetwak3kora.MainFragments.Week.WeekFragment
import shady.samir.adminetwak3kora.MainFragments.Winner.WinnerFragment
import shady.samir.adminetwak3kora.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var tablayout:TabLayout
    lateinit var viewpager:ViewPager
    lateinit var adapter: HomeTabAdapter
    lateinit var fabAdd:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        tablayout = root.findViewById(R.id.tablayout)
        viewpager = root.findViewById(R.id.viewpager)
        fabAdd = root.findViewById(R.id.fabAdd)

        adapter =
            HomeTabAdapter(
                parentFragmentManager
            )
        adapter.addFragment(LeagueFragment(),"League")
        adapter.addFragment(TeamFragment(),"Team")
        adapter.addFragment(MatchesFragment(),"Matches")
        adapter.addFragment(ResultsMatchesFragment(), "Results Matches")
        adapter.addFragment(AwardFragment(), "Award")
        adapter.addFragment(NotificationFragment(), "Notification")
        adapter.addFragment(WeekFragment(), "Weeks")
        adapter.addFragment(MonthFragment(), "Months")
        adapter.addFragment(SeasonFragment(), "Seasons")
        adapter.addFragment(ProfilesFragment(), "Profiles")
        adapter.addFragment(WinnerFragment(), "Winners")
        adapter.addFragment(HelperFragment(), "Helper")

        viewpager.adapter=adapter;
        tablayout.setupWithViewPager(viewpager)
        
        action()

        //tablayout.selectTab(tablayout.getTabAt(5))

        return root
    }

    private fun action() {
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tablayout.getTabAt(0)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(1)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(2)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(3)?.isSelected!!) {
                    fabAdd.hide()
                } else if (tablayout.getTabAt(4)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(5)?.isSelected!!) {
                    fabAdd.hide()
                } else if (tablayout.getTabAt(6)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(7)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(8)?.isSelected!!) {
                    fabAdd.show()
                } else if (tablayout.getTabAt(9)?.isSelected!!) {
                    fabAdd.hide()
                } else if (tablayout.getTabAt(10)?.isSelected!!) {
                    fabAdd.hide()
                } else if (tablayout.getTabAt(11)?.isSelected!!) {
                    fabAdd.show()
                }
            }

        })

        fabAdd.setOnClickListener { view ->
            if (tablayout.getTabAt(0)?.isSelected!!) {
                startActivity(Intent(context,AddLeagueActivity::class.java))
            } else if (tablayout.getTabAt(1)?.isSelected!!) {
                startActivity(Intent(context,AddTeamActivity::class.java))
            } else if (tablayout.getTabAt(2)?.isSelected!!) {
                startActivity(Intent(context,AddMatchActivity::class.java))
            }  else if (tablayout.getTabAt(4)?.isSelected!!) {
                startActivity(Intent(context,AddAwardActivity::class.java))
            } else if (tablayout.getTabAt(6)?.isSelected!!) {
                startActivity(Intent(context,AddWeekActivity::class.java))
            } else if (tablayout.getTabAt(7)?.isSelected!!) {
                startActivity(Intent(context,AddMonthActivity::class.java))
            } else if (tablayout.getTabAt(8)?.isSelected!!) {
                startActivity(Intent(context,AddSeasonActivity::class.java))
            }else if (tablayout.getTabAt(11)?.isSelected!!) {
                startActivity(Intent(context,AddHelperActivity::class.java))
            }
        }

    }
}

