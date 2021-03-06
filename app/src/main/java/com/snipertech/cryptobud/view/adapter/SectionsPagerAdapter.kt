package com.snipertech.cryptobud.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.snipertech.cryptobud.R
import com.snipertech.cryptobud.view.ui.HistoryFragment
import com.snipertech.cryptobud.view.ui.HomeFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, text :String?, key: String?) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val message = text
    val encryptionKey = key
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment.newInstance(
                    message,
                    encryptionKey
                )
            }
            else -> {
                HistoryFragment.newInstance(
                    "",
                    ""
                )
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}