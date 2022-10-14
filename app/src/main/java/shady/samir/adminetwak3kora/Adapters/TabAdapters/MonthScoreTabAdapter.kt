package shady.samir.adminetwak3kora.Adapters.TabAdapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Month
import shady.samir.adminetwak3kora.UsersScore.Fragments.MonthScore.MonthScoreFragment


class MonthScoreTabAdapter(
    fm: FragmentManager?,
    monthList: List<Month>?
) : FragmentPagerAdapter(fm!!) {
    lateinit var monthList: List<Month>
    override fun getPageTitle(position: Int): CharSequence? {
        var title = "Month : " + (position + 1)
         title = monthList.get(position)?.monthNameEn
        return title
    }

    override fun getItem(position: Int): Fragment {
        return MonthScoreFragment(monthList[position])
    }

    override fun getCount(): Int {
        return monthList.size
    }

    init {
        if (monthList != null) {
            this.monthList = monthList
        }
    }
}
