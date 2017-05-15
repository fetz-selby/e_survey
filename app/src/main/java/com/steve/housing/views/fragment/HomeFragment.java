package com.steve.housing.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.steve.housing.R;
import com.steve.housing.views.activities.PropertyListActivity;
import com.steve.housing.views.activities.PropertyViewPagerActivity;
import com.steve.housing.views.activities.ViewPagerActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    private Button buttonCreateProperty, buttonCreateOwner, buttonListProperties;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCreateProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), PropertyViewPagerActivity.class));

            }
        });

        buttonCreateOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViewPagerActivity.class));

            }
        });
        buttonListProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PropertyListActivity.class));
            }
        });
    }


    private void initViews(View view) {
        buttonCreateOwner = (Button) view.findViewById(R.id.buttonOwnerList);
        buttonCreateProperty = (Button) view.findViewById(R.id.buttonPropertyList);
        buttonListProperties = (Button) view.findViewById(R.id.buttonListProperties);
    }
}
