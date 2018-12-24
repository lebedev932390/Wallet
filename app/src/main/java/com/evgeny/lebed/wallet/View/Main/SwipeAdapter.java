package com.evgeny.lebed.wallet.View.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new DayFragment();
            case 1:
                return new MonthFragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
