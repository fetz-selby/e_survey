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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmergencyDetailsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmergencyDetailsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyDetailsFormFragment extends Fragment {
    TextInputLayout emergencyContactFullNameWrapper;
    TextInputLayout emergencyContactEmailWrapper;
    TextInputLayout emergencyContactPhoneWrapper;
    TextInputLayout emergencyContactCityWrapper;
    TextInputLayout emergencyContactAddressWrapper;
    TextInputLayout emergencyContactLicenseWrapper;
    EditText emergencyContactFullName;
    EditText emergencyContactEmail;
    EditText emergencyContactPhone;
    EditText emergencyContactCity;
    EditText emergencyContactAddress;
    EditText emergencyContactLicense;

    private OnFragmentInteractionListener mListener;

    public EmergencyDetailsFormFragment() {
        // Required empty public constructor
    }


    public static EmergencyDetailsFormFragment newInstance(String param1) {
        EmergencyDetailsFormFragment fragment = new EmergencyDetailsFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_emergency_detail_form, container, false);
       emergencyContactFullNameWrapper = (TextInputLayout) view.findViewById(R.id.emergencyContactNameWrapper);
       emergencyContactEmailWrapper = (TextInputLayout) view.findViewById(R.id.emergencyContactEmailWrapper);
       emergencyContactPhoneWrapper = (TextInputLayout) view.findViewById(R.id.emergencyContactPhoneWrapper);
       emergencyContactCityWrapper = (TextInputLayout) view.findViewById(R.id.emergencyContactCityWrapper);
       emergencyContactAddressWrapper = (TextInputLayout) view.findViewById(R.id.emergencyContactAddressWrapper);
       emergencyContactCity = (EditText) view.findViewById(R.id.editTextEmergencyContactCity);
       emergencyContactFullName = (EditText) view.findViewById(R.id.editTextEmergencyContactName);
       emergencyContactEmail = (EditText) view.findViewById(R.id.editTextEmergencyContactEmail);
       emergencyContactAddress = (EditText) view.findViewById(R.id.editTextEmergencyContactAddress);
       emergencyContactPhone = (EditText) view.findViewById(R.id.editTextEmergencyContactPhone);

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
