package com.modnsolutions.tabexperiement;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.modnsolutions.tabexperiement.TabFragment1;
import com.modnsolutions.tabexperiement.TabFragment2;
import com.modnsolutions.tabexperiement.TabFragment3;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param i position
     * @return
     */
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            case 2: return new TabFragment3();
            default: return null;
        }
    }

    /**
     * Return the number of views available
     *
     * @return
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
