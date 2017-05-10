package com.steve.housing.views.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steve.housing.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PropertyViewPagerFragment extends Fragment {

    public PropertyViewPagerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_property_view_pager, container, false);
    }
}
