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
public class OwnerListFragment extends Fragment {

    public OwnerListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_list, container, false);
        return view;
    }
}
