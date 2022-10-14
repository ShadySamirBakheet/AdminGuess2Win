package shady.samir.adminetwak3kora.Adapters.TabAdapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shady.samir.adminetwak3kora.Models.ResponseModel.Leagues.LeaguesResponseModel
import shady.samir.adminetwak3kora.UsersScore.Fragments.LeagueScore.LeagueScoreFragment


class LeagueScoreTabAdapter(
    fm: FragmentManager?,
    leagueList: LeaguesResponseModel?
) :
    FragmentPagerAdapter(fm!!) {
    var leagueList: LeaguesResponseModel
    override fun getPageTitle(position: Int): CharSequence? {
        var title = leagueList.data?.get(position)?.name

        return title
    }

    override fun getItem(position: Int): Fragment {
        return LeagueScoreFragment(leagueList.data?.get(position))
    }

    override fun getCount(): Int {
        return leagueList.data?.size!!
    }

    init {
        this.leagueList = leagueList!!
    }
}
