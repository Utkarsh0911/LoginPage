package com.tcs.newsapp.LoginPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position)
        {
           0-> {return Login()}
          1-> {return Reg()}

            else ->{return Login()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position)
        {
            0 -> {return "Login"}
            1-> {return "Register"}

        }

        return super.getPageTitle(position)
    }
    override fun getCount(): Int {
       return 2;
    }
}