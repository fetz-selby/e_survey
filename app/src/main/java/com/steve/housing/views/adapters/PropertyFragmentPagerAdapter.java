package com.steve.housing.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.steve.housing.views.fragment.AgentPropertyDetailsFormFragment;
import com.steve.housing.views.fragment.EmergencyDetailsFormFragment;
import com.steve.housing.views.fragment.PropertyFormDetailsFragment;
import com.steve.housing.views.fragment.PropertyFormExtraDetailsFormFragment;
import com.steve.housing.views.fragment.PropertyManagerDetailsFormFragment;

/**
 * Created by SOVAVY on 5/10/2017.
 */

public class PropertyFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String tabTitles[] = new String[]{"Property Status", "Property Identitfication", "Property Manager","Emergency Contact","Agent Contact"};

    public PropertyFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PropertyFormDetailsFragment();
            case 1:
                return new PropertyFormExtraDetailsFormFragment();
            case 2:
                return new PropertyManagerDetailsFormFragment();
            case 3:
                return  new EmergencyDetailsFormFragment();
            case 4:
                return  new AgentPropertyDetailsFormFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabTitles.length;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
