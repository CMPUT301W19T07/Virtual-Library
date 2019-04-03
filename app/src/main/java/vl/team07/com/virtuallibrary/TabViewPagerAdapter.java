/*
 * Class Name
 *
 * Date of Initiation
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * A page adapter for the tab view
 * @version 1.0
 * @since 1.0
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Instantiates a new Tab view pager adapter.
     *
     * @param manager the manager
     */
    public TabViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    /**
     * Returns item at a given position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    /**
     * Returns the number of fragments
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * Add fragment.
     *
     * @param fragment the fragment
     * @param title    the title
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
