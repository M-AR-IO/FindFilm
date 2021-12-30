package my.mobile.findfilm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import my.mobile.findfilm.ui.favorit.tab.FilmFavoritFragment
import my.mobile.findfilm.ui.favorit.tab.TvFavoritFragment

class FavoritTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val tabs: List<Fragment>
    init {
        tabs = listOf(
            FilmFavoritFragment(),
            TvFavoritFragment()
        )
    }
    override fun getCount() = tabs.size

    override fun getItem(position: Int) = tabs[position]

}