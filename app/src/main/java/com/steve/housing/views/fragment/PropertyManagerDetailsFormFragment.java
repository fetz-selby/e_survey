package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.steve.housing.R;


public class PropertyManagerDetailsFormFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    TextInputLayout propertyManagerFullNameWrapper;
    TextInputLayout propertyManagerEmailWrapper;
    TextInputLayout propertyManagerPhoneWrapper;
    TextInputLayout propertyManagerCityWrapper;
    TextInputLayout propertyManagerAddressWrapper;
    TextInputLayout propertyManagerLicenseWrapper;
    EditText propertyManagerFullName;
    EditText propertyManagerEmail;
    EditText propertyManagerPhone;
    EditText propertyManagerCity;
    EditText propertyManagerAddress;
    EditText propertyManagerLicense;



    public PropertyManagerDetailsFormFragment() {
        // Required empty public constructor
    }


    public static PropertyManagerDetailsFormFragment newInstance(String param1, String param2) {
        PropertyManagerDetailsFormFragment fragment = new PropertyManagerDetailsFormFragment();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_manager_details_form, container, false);
        propertyManagerFullNameWrapper = (TextInputLayout) view.findViewById(R.id.propertyManagerNameWrapper);
        propertyManagerEmailWrapper = (TextInputLayout) view.findViewById(R.id.propertyManagerEmailWrapper);
        propertyManagerPhoneWrapper = (TextInputLayout) view.findViewById(R.id.propertyManagerPhoneWrapper);
        propertyManagerCityWrapper = (TextInputLayout) view.findViewById(R.id.propertyManagerCityWrapper);
        propertyManagerAddressWrapper = (TextInputLayout) view.findViewById(R.id.propertyManagerAddressWrapper);
        propertyManagerLicenseWrapper = (TextInputLayout) view.findViewById(R.id.propertyManagerAddressWrapper);
        propertyManagerCity = (EditText) view.findViewById(R.id.editTextPropertyManagerCity);
        propertyManagerFullName = (EditText) view.findViewById(R.id.editTextPropertyManagerName);
        propertyManagerEmail = (EditText) view.findViewById(R.id.editTextPropertyManagerEmail);
        propertyManagerAddress = (EditText) view.findViewById(R.id.editTextPropertyManagerAddress);
        propertyManagerPhone = (EditText) view.findViewById(R.id.editTextPropertyManagerPhone);
        propertyManagerLicense = (EditText) view.findViewById(R.id.editTextPropertyManagerLicense);


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
