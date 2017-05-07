package com.steve.housing.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.steve.housing.views.fragment.CitizenshipDetailsFormFragment;
import com.steve.housing.views.fragment.ContactDetailsFormFragment;
import com.steve.housing.views.fragment.EmploymentDetailsFormFragment;
import com.steve.housing.views.fragment.IdentificationCardFragment;
import com.steve.housing.views.fragment.LanguageDetailsFormFragment;
import com.steve.housing.views.fragment.PersonalDetailsFormFragment;
import com.steve.housing.views.fragment.SocialMediaDetailsFormFragment;

/**
 * Created by SOVAVY on 4/25/2017.
 */

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Personal", "Identification", "Contact", "Language",
            "Citizenship", "Employment", "Social Media"};

    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PersonalDetailsFormFragment();
            case 1:
                return new IdentificationCardFragment();
            case 2:
                return new ContactDetailsFormFragment();
            case 3:
                return new LanguageDetailsFormFragment();
            case 4:
                return new CitizenshipDetailsFormFragment();
            case 5:
                return new EmploymentDetailsFormFragment();
            case 6:
                return new SocialMediaDetailsFormFragment();

        }
        return null;
//        return PersonalDetailsFormFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
