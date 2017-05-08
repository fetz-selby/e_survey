package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.steve.housing.R;


public class PropertyFormDetailsFragment extends Fragment {

    Spinner spinnerPropertyType;
    Spinner spinnerClassification;
    Spinner spinnerOwnershipStatus;
    Spinner spinnerElectricitySource;
    CheckBox checkBoxYes;
    CheckBox checkBoxNo;
    EditText editTextPropertyIdentificationNumber;
    EditText editTextTitleRegistrationNumber;
    EditText editTextIdentureNumber;
    EditText editTextTaxIdentationNumber;


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
        spinnerPropertyType = (Spinner) view.findViewById(R.id.spinnerPropertyType);
        spinnerClassification = (Spinner) view.findViewById(R.id.spinnerPropertyClassification);
        spinnerOwnershipStatus = (Spinner) view.findViewById(R.id.spinnerOwnershipStatus);
        spinnerElectricitySource = (Spinner) view.findViewById(R.id.spinnerElectricitySource);

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

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
