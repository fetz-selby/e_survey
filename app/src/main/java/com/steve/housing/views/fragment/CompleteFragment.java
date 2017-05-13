package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.steve.housing.R;
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.models.PropertyMDL;
import com.steve.housing.utils.Constants;
import com.steve.housing.utils.VolleyRequests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

public class CompleteFragment extends Fragment {

    private static final String TAG = CompleteFragment.class.getSimpleName();
    public String realmKey;
    ArrayList<String> ownersData;
    Button buttonSubmit;
    OwnerMDL ownerMDL;
    private OnFragmentInteractionListener mListener;
    private Spinner spinnerOwnerList;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;
    private String id;


    private VolleyRequests mVolleyRequest;
    private ProgressBar mProgressBar;


//    private VolleyRequests;


    public CompleteFragment() {
        // Required empty public constructor
    }

    public static CompleteFragment newInstance(String param1, String param2) {
        CompleteFragment fragment = new CompleteFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complete, container, false);
        mVolleyRequest = new VolleyRequests(getActivity());
        mRealm = Realm.getDefaultInstance();
        spinnerOwnerList = (Spinner) view.findViewById(R.id.spinnerOwnersList);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmitData);
        ownersData = retrieveOwners();

        ArrayAdapter adapterOwner = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, ownersData);
        spinnerOwnerList.setAdapter(adapterOwner);
        spinnerOwnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "" + mRealm.where(OwnerMDL.class).equalTo("id", ownersData.get(position)).findFirst(), Toast.LENGTH_LONG).show();

                realmKey = ownersData.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        PropertyMDL propertyMDL = realm.where(PropertyMDL.class).findAllSorted("createdDate").last();

                        ownerMDL = realm.where(OwnerMDL.class).equalTo("id", realmKey).findFirst();

                        propertyMDL.ownerList.add(ownerMDL);
                        id = propertyMDL.getId();
                        Log.d(TAG, propertyMDL.getOwnerList().get(0).toString());


                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getContext(), "details updated", Toast.LENGTH_LONG).show();


                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getContext(), " failed " + error.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                });

                PropertyMDL propertyMDL = mRealm.where(PropertyMDL.class).equalTo("id", id).findFirst();
                if (propertyMDL != null) {
                    JSONArray jsonArray = new JSONArray();

//                    jsonObject.put(propertyMDL.getOwnerList().toString())

                    for (OwnerMDL ownerMDL : propertyMDL.ownerList) {
                        try {

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("firstname", ownerMDL.getFirstname());
                            jsonObject.put("lastname", ownerMDL.getLastname());
                            jsonArray.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    Log.d(TAG, "" + propertyMDL.getOwnerList().toString());

                    Log.d(TAG, jsonArray.toString() + " Size :" + jsonArray.length());


                    Map<String, String> params = new HashMap<>();

                    params.put("pins", propertyMDL.getPins());
                    params.put("propertyType", propertyMDL.getPropertyType());
                    params.put("classication", propertyMDL.getClassification());
                    params.put("address", propertyMDL.getAddress());
                    params.put("owners", jsonArray.toString());

                    Log.d(TAG, "Params" + params.toString());

                    mVolleyRequest.postData(Constants.url, params, new VolleyRequests.VolleyPostCallBack() {

                        @Override
                        public void onSuccess(JSONObject result) {
                            Toast.makeText(getContext(), result.toString(), Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Results" + result.toString());

                        }

                        @Override
                        public void onError(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            Log.d(TAG, error.toString().toString());

                        }

                        @Override
                        public void onStart() {
                            Toast.makeText(getContext(), "starting", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFinish() {

                        }
                    });

                }


            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public ArrayList<String> retrieveOwners() {
        ArrayList<String> regions = new ArrayList<>();

        RealmResults<OwnerMDL> realmResults = mRealm.where(OwnerMDL.class).findAll();


        for (OwnerMDL ownerMDL : realmResults) {
            regions.add(ownerMDL.getId());
        }
        return regions;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()) {
            realmAsyncTask.cancel();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
