package com.tcs.newsapp.LoginPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tcs.newsapp.LoginPage.ui.dashboard.DashboardFragment
import com.tcs.newsapp.LoginPage.ui.home.HomeFragment
import com.tcs.newsapp.LoginPage.ui.notifications.NotificationsFragment

class loginPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position)
        {
            0-> {return DashboardFragment()}
            1-> {return HomeFragment()}
            2-> {return NotificationsFragment()
            }
            else ->{return Login()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0 -> {return "Hospital"}
            1-> {return "Atm"}
            1-> {return "school"}
        }

        return super.getPageTitle(position)
    }
    override fun getCount(): Int {
        return 3;
    }
}