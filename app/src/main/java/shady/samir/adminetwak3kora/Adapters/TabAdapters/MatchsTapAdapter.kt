//package shady.samir.adminetwak3kora.Adapters.TabAdapters
//
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import shady.samir.adminetwak3kora.MatchsView.Fragments.MatchView.MatchViewFragment
//import shady.samir.adminetwak3kora.MatchsView.Fragments.ResultMatchView.ResultMatchViewFragment
//import shady.samir.adminetwak3kora.Models.Match
//import shady.samir.adminetwak3kora.MatchsView.Models.MatchGroup
//
//class MatchsTapAdapter(
//    fm: FragmentManager?,
//    matchList: List<Match>,
//    matchGroupList: List<MatchGroup>,
//    b: Boolean
//) :
//    FragmentPagerAdapter(fm!!) {
//    var matchGroupList: List<MatchGroup>
//    var matchList: List<Match>
//    var frgament: Boolean
//
//    override fun getItem(position: Int): Fragment {
//        return if (frgament) {
//            MatchViewFragment(matchGroupList[position])
//        } else {
//            ResultMatchViewFragment(matchGroupList[position])
//        }
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return matchGroupList[position].title
//    }
//
//    override fun getCount(): Int {
//        return matchGroupList.size
//    }
//
//    init {
//        this.matchList = matchList
//        this.matchGroupList = matchGroupList
//        frgament = b
//    }
//}
