package com.steve.housing.views.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.DistrictMDL;
import com.steve.housing.models.GpsCoordinatesMDL;
import com.steve.housing.models.LocationMDL;
import com.steve.housing.models.PropertyMDL;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;


public class PropertyFormExtraDetailsFormFragment extends Fragment implements LocationListener {

    public static final int PERMISSION_ACCESS_COARSE_LOCATION = 99;
    float value;
    FloatingActionButton floatingActionButonPropertyExtra;
    ArrayList<String> districtsData;
    DistrictMDL districtMDL;
    private ProgressDialog dialog;
    private LocationManager mlocManager = null;
    private Context ctx;
    private ProgressBar gpsLoading = null;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;
    private EditText editTextNumberOfUnits, editTextAddress, editTextCoordinates, editTextLandmark;
    private Spinner spinnerDistrict;
    private String numberofUnits, district, address;
    private float longitude, latitute;
    private FloatingActionButton floatingActionButtonCoordinates;
    private LocationManager locationManager;
    private OnFragmentInteractionListener mListener;
    private String districtValue;

    public PropertyFormExtraDetailsFormFragment() {
        // Required empty public constructor
    }


    public static PropertyFormExtraDetailsFormFragment newInstance(String param1, String param2) {
        PropertyFormExtraDetailsFormFragment fragment = new PropertyFormExtraDetailsFormFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_form_extra_details_form, container, false);
        editTextAddress = (EditText) view.findViewById(R.id.editTextPropertyAddress);
        spinnerDistrict = (Spinner) view.findViewById(R.id.spinnerPropertyDistrict);
        editTextNumberOfUnits = (EditText) view.findViewById(R.id.editTextNumberOfUnits);
        editTextCoordinates = (EditText) view.findViewById(R.id.editTextPropertyCoordinates);
//        editTextLandmark = (EditText) view.findViewById(R.id.editTextPropertyLandmark) ;
        floatingActionButtonCoordinates = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonPropertyCoordinates);
        floatingActionButonPropertyExtra = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonGetPropertyExtraDetails);
        gpsLoading = (ProgressBar) view.findViewById(R.id.gps_loading_panel);
        final String districtValue;

        mRealm = Realm.getDefaultInstance();
//        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dialog = new ProgressDialog(getActivity());

        districtsData = retrieveDistricts();

        ArrayAdapter adapterDistrict = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, districtsData);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "" + mRealm.where(DistrictMDL.class).equalTo("district", districtsData.get(position)).findFirst(), Toast.LENGTH_LONG).show();
//                districtMDL = mRealm.where(DistrictMDL.class).equalTo("district", districtsData.get(position)).findFirst();
                district = districtsData.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        floatingActionButtonCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "loading", Toast.LENGTH_LONG).show();
                getLocation();

//                checkLocationPermission();
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // You need to ask the user to enable the permissions
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                            PERMISSION_ACCESS_COARSE_LOCATION);
                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.title_location_permission)
                            .setMessage(R.string.text_location_permission)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //Prompt the user once explanation has been shown
                                    ActivityCompat.requestPermissions(getActivity(),
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            PERMISSION_ACCESS_COARSE_LOCATION);
                                }
                            })
                            .create()
                            .show();

                } else {

                }
            }
        });
        floatingActionButonPropertyExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                numberofUnits = editTextNumberOfUnits.getText().toString();

//                /district = spinnerDistrict.getText().toString();
                address = editTextAddress.getText().toString();
                realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        PropertyMDL propertyMDL = realm.where(PropertyMDL.class).findAllSorted("createdDate").last();
                        String id = UUID.randomUUID().toString();
                        String location_id = UUID.randomUUID().toString();
                        GpsCoordinatesMDL gpsCoordinatesMDL = realm.createObject(GpsCoordinatesMDL.class, id);
                        LocationMDL locationMDL = realm.createObject(LocationMDL.class, location_id);
                        propertyMDL.setFamilyUnit((numberofUnits.isEmpty()) ? "N/A" : numberofUnits);
//                        propertyMDL.setDistrict((district.isEmpty()) ? "N/A" : district);
//                        propertyMDL.setAgentContactCity((address.isEmpty()) ? "N/A" : address);
                        gpsCoordinatesMDL.setLongitude((longitude == 0) ? 0 : value);
                        gpsCoordinatesMDL.setLongitude((latitute == 0) ? 0 : value);
                        districtMDL = realm.where(DistrictMDL.class).equalTo("district", district).findFirst();
                        locationMDL.setDistrictMDL(districtMDL);
//                        locationMDL.setDistrictMDL();
                        propertyMDL.setAddress(address);
                        locationMDL.setGps(gpsCoordinatesMDL);
                        propertyMDL.setLocationMDL(locationMDL);

                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getContext(), "details updated", Toast.LENGTH_LONG).show();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                });


            }
        });
        return view;
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {


                    }

                } else {


                }
                return;
            }

        }
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

    @Override
    public void onLocationChanged(Location location) {
        editTextCoordinates.setText(location.getLatitude() + ", " + location.getLongitude());
        longitude = (float) location.getLongitude();
        latitute = (float) location.getLatitude();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getContext(), "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();

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


    public ArrayList<String> retrieveDistricts() {
        ArrayList<String> regions = new ArrayList<>();

        RealmResults<DistrictMDL> realmResults = mRealm.where(DistrictMDL.class).findAll();


        for (DistrictMDL districtMDL : realmResults) {
            regions.add(districtMDL.getDistrict());
        }
        return regions;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
