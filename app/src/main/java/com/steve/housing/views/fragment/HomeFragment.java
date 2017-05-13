package com.steve.housing.views.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.steve.housing.R;
import com.steve.housing.views.activities.OwnerListActivity;
import com.steve.housing.views.activities.PropertyListActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {

    private Button buttonPropertiesList, buttonOwnersList;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonPropertiesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPropertyList = new Intent(getActivity(), PropertyListActivity.class);
                startActivity(intentPropertyList);

            }
        });

        buttonOwnersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOwnerList= new Intent(getActivity(), OwnerListActivity.class);
                startActivity(intentOwnerList);

            }
        });
    }


    private void initViews(View view) {
        buttonOwnersList = (Button) view.findViewById(R.id.buttonOwnerList);
        buttonPropertiesList = (Button) view.findViewById(R.id.buttonPropertyList);
    }
}
