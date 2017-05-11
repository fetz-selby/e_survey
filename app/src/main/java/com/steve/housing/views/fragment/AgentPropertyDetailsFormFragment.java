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
 * {@link AgentPropertyDetailsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgentPropertyDetailsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgentPropertyDetailsFormFragment extends Fragment {
    TextInputLayout agentContactFullNameWrapper;
    TextInputLayout agentContactEmailWrapper;
    TextInputLayout agentContactPhoneWrapper;
    TextInputLayout agentContactCityWrapper;
    TextInputLayout agentContactAddressWrapper;
    EditText agentContactFullName;
    EditText agentContactEmail;
    EditText agentContactPhone;
    EditText agentContactCity;
    EditText agentContactAddress;

    private OnFragmentInteractionListener mListener;

    public AgentPropertyDetailsFormFragment() {
        // Required empty public constructor
    }

    public static AgentPropertyDetailsFormFragment newInstance() {
        AgentPropertyDetailsFormFragment fragment = new AgentPropertyDetailsFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_agent_property_details_form, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        agentContactFullNameWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutAgentName);
        agentContactEmailWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutAgentEmail);
        agentContactPhoneWrapper = (TextInputLayout)  view.findViewById(R.id.textInputLayoutAgentPhone);
        agentContactCityWrapper =  (TextInputLayout) view.findViewById(R.id.textInputLayoutAgentCity);
        agentContactAddressWrapper = (TextInputLayout)view.findViewById(R.id.textInputLayoutAgentAddress);
        agentContactFullName = (EditText) view.findViewById(R.id.editTextEmergencyName);
        agentContactEmail = (EditText) view.findViewById(R.id.editTextEmergencyEmail);
        agentContactPhone = (EditText)  view.findViewById(R.id.editTextEmergencyPhone);
        agentContactCity = (EditText) view.findViewById(R.id.editTextEmergencyCity);
        agentContactAddress = (EditText) view.findViewById(R.id.editTextEmergencyAddress);
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
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()) {
//            realmAsyncTask.cancel();
//
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mRealm.close();
//
//    }

}