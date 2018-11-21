package com.software.miedo.pocketfisireal.core;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.software.miedo.pocketfisireal.data.local.SessionManager;
import com.software.miedo.pocketfisireal.presentation.fragments.courses.CoursesFragment;
import com.software.miedo.pocketfisireal.presentation.fragments.news.NewsFragment;
import com.software.miedo.pocketfisireal.presentation.fragments.support.Identified.SupportIdentifiedFragment;
import com.software.miedo.pocketfisireal.presentation.fragments.support.SupportLoginFragment;

public class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    NewsFragment newsFragment = new NewsFragment();
    CoursesFragment coursesFragment = new CoursesFragment();


    SupportIdentifiedFragment supportIdentifiedFragment = new SupportIdentifiedFragment();
    SupportLoginFragment supportLoginFragment = new SupportLoginFragment();


    public FixedTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    /*@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Sobreescribimos este método para que no los fragmentos nos se destruyan xd :'v lo hicee
    }*/


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return newsFragment;
            case 1:
                return coursesFragment;
            case 2:
                return supportLoginFragment;
            case 3:
                return supportIdentifiedFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    public void doRefresh(int position) {
        switch (position) {
            case 0:
                newsFragment.reload();
                break;
            case 1:
                coursesFragment.reload();
                break;
            case 2:
                break;
            case 3:
                supportIdentifiedFragment.reload();
                break;
            default:
        }
    }


}

