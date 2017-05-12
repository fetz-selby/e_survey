package com.steve.housing.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.steve.housing.R;
import com.steve.housing.models.DistrictMDL;
import com.steve.housing.models.RegionMDL;
import com.steve.housing.utils.Constants;
import com.steve.housing.utils.VolleyRequests;
import com.steve.housing.views.activities.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class SplashScreenActivityFragment extends Fragment {


    private VolleyRequests mVolleyRequest;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;
    private SharedPreferences prefs;


    private ProgressBar mProgressBar;


    public SplashScreenActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        mVolleyRequest = new VolleyRequests(getActivity());
        mRealm = Realm.getDefaultInstance();
        prefs = getActivity().getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        mRealm = Realm.getDefaultInstance();
        initField(view);
        if (checkDistricts().isEmpty() || checkRegions().isEmpty())
        {
            getRegions();
            getDistricts();
        }else{
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }





        return view;


    }


    private RealmResults<DistrictMDL> checkDistricts() {
        return mRealm.where(DistrictMDL.class).findAll();
    }

    private RealmResults<RegionMDL> checkRegions() {
      return   mRealm.where(RegionMDL.class).findAll();
    }

    private void getDistricts() {
        mVolleyRequest.JsonObjRequest(Constants.DISTRICT_URL, new VolleyRequests.VolleyJsonCallBack() {
            @Override
            public void onSuccess(JSONObject result) {


                Gson gson = new GsonBuilder().create();
                Type collectionDistrict = new TypeToken<Collection<DistrictMDL>>() {
                }.getType();
                Collection<DistrictMDL> realmDistricts = null;
                try {
                    realmDistricts = gson.fromJson(String.valueOf(result.getJSONArray("districts")), collectionDistrict);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Open a transaction to store items into the realm
                // Use copyToRealm() to convert the objects into proper RealmObjects managed by Realm.
                mRealm.beginTransaction();
                mRealm.copyToRealmOrUpdate(realmDistricts);
                mRealm.commitTransaction();

                mProgressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onError(VolleyError error) {

                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStart() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getRegions() {
        mVolleyRequest.JsonObjRequest(Constants.REGION_URL, new VolleyRequests.VolleyJsonCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
                new MaterialDialog.Builder(getContext())
                        .title("Results")
                        .content(result.toString())
                        .positiveText(R.string.ok)
                        .negativeText(R.string.md_cancel_label)
                        .show();
                Gson gson = new GsonBuilder().create();

                Type collectionType = new TypeToken<Collection<RegionMDL>>() {
                }.getType();
                Collection<RegionMDL> realmRegions = null;
                try {
                    realmRegions = gson.fromJson(String.valueOf(result.getJSONArray("regions")), collectionType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Open a transaction to store items into the realm
                // Use copyToRealm() to convert the objects into proper RealmObjects managed by Realm.
                mRealm.beginTransaction();
                mRealm.copyToRealmOrUpdate(realmRegions);
                mRealm.commitTransaction();

                mProgressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onError(VolleyError error) {

                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStart() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initField(View view) {

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBarLoadData);
    }


}
