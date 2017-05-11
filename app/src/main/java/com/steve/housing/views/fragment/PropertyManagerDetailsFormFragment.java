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
import com.steve.housing.models.PropertyMDL;

import io.realm.Realm;
import io.realm.RealmAsyncTask;


public class PropertyManagerDetailsFormFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    TextInputLayout propertyPmanagerFullNameWrapper;
    TextInputLayout propertyPmanagerEmailWrapper;
    TextInputLayout propertyPmanagerPhoneWrapper;
    TextInputLayout propertyPmanagerCityWrapper;
    TextInputLayout propertyPmanagerAddressWrapper;
    TextInputLayout propertyPmanagerLicenseWrapper;
    EditText propertyPmanagerFullName;
    EditText propertyPmanagerEmail;
    EditText propertyPmanagerPhone;
    EditText propertyPmanagerCity;
    EditText propertyPmanagerAddress;
    EditText propertyPmanagerLicense;
    FloatingActionButton floatingActionButtonPmanager;
    private String name, email, phone, city, address, license;


    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;


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
        mRealm = Realm.getDefaultInstance();
        intViews(view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButtonPmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = propertyPmanagerFullName.getText().toString();
                phone = propertyPmanagerPhone.getText().toString();
                email = propertyPmanagerEmail.getText().toString();
                city = propertyPmanagerCity.getText().toString();
                address = propertyPmanagerAddress.getText().toString();
                license = propertyPmanagerLicense.getText().toString();

                realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        PropertyMDL propertyMDL = realm.where(PropertyMDL.class).findAllSorted("createdDate").last();
                        propertyMDL.setPropertyManagerContactAddress((address.isEmpty()) ? "N/A" : address);
                        propertyMDL.setPropertyManagerContactCity((city.isEmpty()) ? "N/A" : city);
                        propertyMDL.setPropertyManagerEmail((email.isEmpty()) ? "N/A" : email);
                        propertyMDL.setPropertyManagerContactName((name.isEmpty()) ? "N/A" : name);
                        propertyMDL.setAgentContactphone((phone.isEmpty()) ? "N/A" : phone);
                        propertyMDL.setPropertyManagerLicenseNumber((phone.isEmpty()) ? "N/A" : phone);
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

    private void intViews(View view) {
        propertyPmanagerFullNameWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutPmanagerName);
        propertyPmanagerEmailWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutPmanagerEmail);
        propertyPmanagerPhoneWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutPmanagerPhone);
        propertyPmanagerCityWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutPmanagerCity);
        propertyPmanagerAddressWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutPmanagerAddress);
        propertyPmanagerLicenseWrapper = (TextInputLayout) view.findViewById(R.id.textInputLayoutPmanagerLicenceNumber);
        propertyPmanagerCity = (EditText) view.findViewById(R.id.editTextPManagerCity);
        propertyPmanagerFullName = (EditText) view.findViewById(R.id.editTextPManagerName);
        propertyPmanagerEmail = (EditText) view.findViewById(R.id.editTextPManagerEmail);
        propertyPmanagerAddress = (EditText) view.findViewById(R.id.editTextPManagerAddress);
        propertyPmanagerPhone = (EditText) view.findViewById(R.id.editTextPropertyManagerPhone);
        propertyPmanagerLicense = (EditText) view.findViewById(R.id.editTextPManagerLicenceNumber);
        floatingActionButtonPmanager = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonPmanager);
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
