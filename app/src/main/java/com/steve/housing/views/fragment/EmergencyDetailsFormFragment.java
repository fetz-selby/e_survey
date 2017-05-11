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
    EditText emergencyContactFullName;
    EditText emergencyContactEmail;
    EditText emergencyContactPhone;
    EditText emergencyContactCity;
    EditText emergencyContactAddress;


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
        initViews(view);

        return view;
    }

    private void initViews(View view) {
        emergencyContactFullNameWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmergencyName);
        emergencyContactEmailWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmergencyEmail);
        emergencyContactPhoneWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmergencyPhone);
        emergencyContactCityWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmergencyCity);
        emergencyContactAddressWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmergencyAddress);
        emergencyContactCity = (EditText) view.findViewById(R.id.editTextEmergencyCity);
        emergencyContactFullName = (EditText) view.findViewById(R.id.editTextEmergencyName);
        emergencyContactEmail = (EditText) view.findViewById(R.id.editTextEmergencyEmail);
        emergencyContactAddress = (EditText) view.findViewById(R.id.editTextEmergencyAddress);
        emergencyContactPhone = (EditText) view.findViewById(R.id.editTextEmergencyPhone);
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
