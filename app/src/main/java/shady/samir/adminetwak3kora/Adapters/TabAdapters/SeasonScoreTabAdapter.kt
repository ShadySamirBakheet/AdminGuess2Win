package shady.samir.adminetwak3kora.Adapters.TabAdapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import shady.samir.adminetwak3kora.Models.DataModel.Season
import shady.samir.adminetwak3kora.UsersScore.Fragments.SeasonScore.SeasonScoreFragment
import java.util.*


class SeasonScoreTabAdapter(
    fm: FragmentManager?,
    seasonList: List<Season>?
) :
    FragmentPagerAdapter(fm!!) {
    lateinit var seasonList: List<Season?>

    override fun getPageTitle(position: Int): CharSequence? {
        var title = "Season : " + (position + 1)
        title = seasonList.get(position)!!.nameEn
        return title
    }

    override fun getItem(position: Int): Fragment {
        return SeasonScoreFragment(seasonList[position]!!.id)
    }

    override fun getCount(): Int {
        return seasonList.size
    }

    init {
        if (seasonList != null) {
            this.seasonList = seasonList
        }
    }
}
