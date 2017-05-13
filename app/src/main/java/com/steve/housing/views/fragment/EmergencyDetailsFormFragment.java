package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.EmergencyContactMDL;
import com.steve.housing.models.PropertyMDL;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

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
    FloatingActionButton floatingActionButtonEmergencyContact;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;
    private String name, email, phone, city, address;


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
        mRealm = Realm.getDefaultInstance();
        initViews(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButtonEmergencyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = emergencyContactFullName.getText().toString();
                phone = emergencyContactPhone.getText().toString();
                email = emergencyContactEmail.getText().toString();
                city = emergencyContactCity.getText().toString();
                address = emergencyContactAddress.getText().toString();


                realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        PropertyMDL propertyMDL = realm.where(PropertyMDL.class).findAllSorted("createdDate").last();
                        //                        String id = UUID.randomUUID().toString();
//                        PropertyMDL propertyMDL = realm.createObject(PropertyMDL.class, id);
                        String id = UUID.randomUUID().toString();
                        EmergencyContactMDL emergencyContactMDL = realm.createObject(EmergencyContactMDL.class, id);
                        emergencyContactMDL.setEmergencyContactAddress((address.isEmpty()) ? "N/A" : address);
                        emergencyContactMDL.setEmergencyContactCity((city.isEmpty()) ? "N/A" : city);
                        emergencyContactMDL.setEmergencyContactEmail((email.isEmpty()) ? "N/A" : email);
                        emergencyContactMDL.setEmergencyContactName((name.isEmpty()) ? "N/A" : name);
                        emergencyContactMDL.setEmergencyContactphone((phone.isEmpty()) ? "N/A" : phone);
                        propertyMDL.setEmergencyContactMDL(emergencyContactMDL);

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
        floatingActionButtonEmergencyContact = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonPropertyEmergencyContact);
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
