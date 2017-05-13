package com.steve.housing.views.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import com.steve.housing.models.RegionMDL;
import com.steve.housing.utils.GenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import fr.quentinklein.slt.LocationTracker;
import fr.quentinklein.slt.TrackerSettings;
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

        mRealm = Realm.getDefaultInstance();
//        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dialog = new ProgressDialog(getActivity());

        districtsData = retrieveDistricts();

        ArrayAdapter adapterDistrict = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, districtsData);
        spinnerDistrict.setAdapter(adapterDistrict);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + mRealm.where(DistrictMDL.class).equalTo("district", districtsData.get(position)).findFirst(), Toast.LENGTH_LONG).show();
                districtMDL = mRealm.where(DistrictMDL.class).equalTo("district", districtsData.get(position)).findFirst();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        floatingActionButtonCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "loading", Toast.LENGTH_LONG).show();
//                getLocation();
                LocationTracker tracker = new LocationTracker(getContext(), new TrackerSettings()
                        .setUseGPS(true)
                        .setUseNetwork(true)
                        .setUsePassive(true)) {
                    @Override
                    public void onLocationFound(Location location) {
                        Toast.makeText(getContext(), "" + location.getLatitude() + "" + location.getLongitude(), Toast.LENGTH_LONG).show();
                        editTextCoordinates.setText("" + location.getLatitude() + "" + location.getLongitude());

                    }

                    @Override
                    public void onTimeout() {

                    }
                };
                tracker.startListening();

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
                        Toast.makeText(getContext(), " failed", Toast.LENGTH_LONG).show();

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

//                        //Request location updates:
////                        locationManager.requestLocationUpdates(provider, 400, 1, this);
//                        LocationTracker tracker = new LocationTracker(ctx, new TrackerSettings()
//                                .setUseGPS(true)
//                                .setUseNetwork(true)
//                                .setUsePassive(true)) {
//                            @Override
//                            public void onLocationFound(Location location) {
//                                // Do some stuff
//                                editTextCoordinates.setText("" + location.getLatitude() + "" + location.getLongitude());
//
//                            }
//
//                            @Override
//                            public void onTimeout() {
//
//                            }
//                        };
//                        tracker.startListening();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    //
//    public boolean checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                new AlertDialog.Builder(getActivity())
//                        .setTitle(R.string.title_location_permission)
//                        .setMessage(R.string.text_location_permission)
//                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //Prompt the user once explanation has been shown
//                                ActivityCompat.requestPermissions(getActivity(),
//                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                        PERMISSION_ACCESS_COARSE_LOCATION);
//                            }
//                        })
//                        .create()
//                        .show();
//
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        PERMISSION_ACCESS_COARSE_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }


    //    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_ACCESS_COARSE_LOCATION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // All good!
//                } else {
//                    Toast.makeText(getContext(), "Need your location!", Toast.LENGTH_SHORT).show();
//                }
//
//                break;
//        }
//    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
//        double d = getInfoValueNumeric();
//        float f = (float)d;


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

    private void setGPSData() {
//        TextView txtGPSCoordinates = (TextView) rootView.findViewById((R.id.gps_coordinates));
//        TextView txtGPSAddress = (TextView) rootView.findViewById((R.id.gps_address));

        double[] coors = GenUtils.getGPSCoords(getActivity());

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        String strFullAddress = "N/A";

        try {
            addresses = geocoder.getFromLocation(coors[0], coors[1], 1);
            if (!addresses.isEmpty()) {
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                strFullAddress = address +
                        ", " + city +
                        ",  " + state +
                        ", " + country;
            }

        } catch (java.io.IOException e) {
            Log.e("setGPSData", e.getMessage());
        }

//        txtGPSCoordinates.setText(String.valueOf(coors[0]) + ", " + String.valueOf(coors[1]));
        editTextCoordinates.setText(String.valueOf(coors[0]) + ", " + String.valueOf(coors[1]));
        editTextAddress.setText(strFullAddress);

        Log.d("setGPSData", "Latitude: " + coors[0]);
        Log.d("setGPSData", "Longitude: " + coors[1]);
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
