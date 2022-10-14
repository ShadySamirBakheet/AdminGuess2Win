package shady.samir.adminetwak3kora.Adapters.TabAdapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomeTabAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    var fragments = ArrayList<Fragment>()
    var titles = ArrayList<String>()

    fun addFragment(fragment: Fragment,  title: String){
        fragments.add(fragment)
        titles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

}