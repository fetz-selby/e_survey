package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.PropertyMDL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;


public class PropertyFormDetailsFragment extends Fragment {

    Spinner spinnerPropertyType;
    Spinner spinnerClassification;
    Spinner spinnerOwnershipStatus;
    Spinner spinnerElectricitySource;
    Spinner spinnerRegistered;
    FloatingActionButton floatingActionButtonProperty;
    String propertyType, classification, ownershipStatus, electricitySource, registered;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;
    boolean registeredValue;


    private OnFragmentInteractionListener mListener;

    public PropertyFormDetailsFragment() {
        // Required empty public constructor
    }


    public static PropertyFormDetailsFragment newInstance(String param1, String param2) {
        PropertyFormDetailsFragment fragment = new PropertyFormDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_property_form_details, container, false);
        mRealm = Realm.getDefaultInstance();
        initViews(view);

        ArrayAdapter<CharSequence> adapterPropertyType = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_type_of_property, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterPropertyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerPropertyType.setAdapter(adapterPropertyType);
        spinnerPropertyType.post(new Runnable() {
            @Override
            public void run() {
                spinnerPropertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        propertyType = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        ArrayAdapter<CharSequence> adapterClassification = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_property_classification, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterClassification.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerClassification.setAdapter(adapterClassification);
        spinnerClassification.post(new Runnable() {
            @Override
            public void run() {
                spinnerClassification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        classification = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
        ArrayAdapter<CharSequence> adapterOwnershipStatus = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_ownership_status, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterClassification.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerOwnershipStatus.setAdapter(adapterOwnershipStatus);
        spinnerOwnershipStatus.post(new Runnable() {
            @Override
            public void run() {
                spinnerOwnershipStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        ownershipStatus = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        ArrayAdapter<CharSequence> adapterElectricitySource = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_source_of_electricity, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterElectricitySource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerElectricitySource.setAdapter(adapterElectricitySource);
        spinnerElectricitySource.post(new Runnable() {
            @Override
            public void run() {
                spinnerElectricitySource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        electricitySource = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
        ArrayAdapter<CharSequence> adapterRegisteredStatus = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_yes_or_no, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterElectricitySource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        spinnerRegistered.setAdapter(adapterRegisteredStatus);
        spinnerRegistered.post(new Runnable() {
            @Override
            public void run() {
                spinnerRegistered.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        registered = parent.getItemAtPosition(position).toString();
                        if (registered.equals("Yes")) {
                            registeredValue = true;
                        } else {
                            registeredValue = false;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        floatingActionButtonProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                final Date date = new Date();
                realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
//                        String id = UUID.randomUUID().toString();
//                        PropertyMDL propertyMDL = realm.createObject(PropertyMDL.class, id);
                        // personal data

                        PropertyMDL propertyMDL = realm.where(PropertyMDL.class).findAllSorted("createdDate").last();
                        propertyMDL.setClassification(classification);
                        propertyMDL.setElectricitySource(electricitySource);
                        propertyMDL.setOwnershipType(ownershipStatus);
                        propertyMDL.setPropertyType(propertyType);
                        propertyMDL.setRegistered(registeredValue);
                        propertyMDL.setCreatedDate(dateFormat.format(date));
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        return view;
    }

    private void initViews(View view) {
        spinnerPropertyType = (Spinner) view.findViewById(R.id.spinnerPropertyType);
        spinnerClassification = (Spinner) view.findViewById(R.id.spinnerPropertyClassification);
        spinnerOwnershipStatus = (Spinner) view.findViewById(R.id.spinnerOwnershipStatus);
        spinnerElectricitySource = (Spinner) view.findViewById(R.id.spinnerElectricitySource);
        spinnerRegistered = (Spinner) view.findViewById(R.id.spinnerRegistered);
        floatingActionButtonProperty = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonProperty);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
}
