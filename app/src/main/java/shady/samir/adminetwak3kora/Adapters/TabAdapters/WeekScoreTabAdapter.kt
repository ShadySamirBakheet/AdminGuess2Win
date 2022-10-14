package shady.samir.adminetwak3kora.Adapters.TabAdapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Week
import shady.samir.adminetwak3kora.UsersScore.Fragments.WeekScore.WeekScoreFragment


class WeekScoreTabAdapter(
    fm: FragmentManager?,
    weekList: List<Week>?
) :
    FragmentPagerAdapter(fm!!) {
    lateinit var weekList: List<Week>
    override fun getItem(position: Int): Fragment {
        return WeekScoreFragment(weekList[position].weekID)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = "Week : " + (position + 1)
        title=weekList.get(position).weekNameEn
        return title
    }

    override fun getCount(): Int {
        return weekList.size
    }

    init {
        if (weekList != null) {
            this.weekList = weekList
        }
    }
}
