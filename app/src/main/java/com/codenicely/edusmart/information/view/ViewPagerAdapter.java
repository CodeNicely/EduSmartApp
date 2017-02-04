package com.codenicely.edusmart.information.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meghal on 6/19/2016.
 */
class ViewPagerAdapter extends FragmentPagerAdapter {

    final int TAB_COUNT = 5;
    private String tabTitles[] = new String[]{"Syllabus", "Assignments", "Resources", "Anouncements"};
    private List<InformationFragment> informationFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        informationFragmentList.add(position, InformationFragment.newInstance(position));
        return informationFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
