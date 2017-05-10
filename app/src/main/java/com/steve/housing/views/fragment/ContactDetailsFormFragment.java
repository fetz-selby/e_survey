package com.steve.housing.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.PersonMDL;
import com.steve.housing.utils.GenUtils;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

import static com.steve.housing.utils.Constants.OwnerContactDataPreferences;
import static com.steve.housing.utils.Constants.ownerAddressKey;
import static com.steve.housing.utils.Constants.ownerDistrictKey;
import static com.steve.housing.utils.Constants.ownerEmailKey;
import static com.steve.housing.utils.Constants.ownerExtraPhoneKey;
import static com.steve.housing.utils.Constants.ownerPhoneKey;


/**

 */
public class ContactDetailsFormFragment extends Fragment {


    private static final String TAG = ContactDetailsFormFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    public static final String ARG_PAGE = "ARG_PAGE";
    private TextInputLayout textInputLayoutPhone, textInputLayoutExtraPhone, textInputLayoutEmail;
    private TextInputLayout textInputLayoutAddress, textInputLayoutDistrict;
    private EditText editTextPhone, editTextExtraPhone, editTextEmail, editTextAddress, editTextDistrict;
    private FloatingActionButton floatingActionButtonGetContact;
    private boolean phoneError, addressError, districtError;
    private SharedPreferences sharedpreferencesOwnerContact;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;


    public ContactDetailsFormFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ContactDetailsFormFragment newInstance(int page) {
        ContactDetailsFormFragment fragment = new ContactDetailsFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
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
        View view = inflater.inflate(R.layout.fragment_contact_details_form, container, false);
        mRealm = Realm.getDefaultInstance();
        initFields(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButtonGetContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneError = GenUtils.isEmpty(editTextPhone, textInputLayoutPhone, "Phone required");
                addressError = GenUtils.isEmpty(editTextAddress, textInputLayoutAddress, "Address required");
                districtError = GenUtils.isEmpty(editTextDistrict, textInputLayoutDistrict, "District required");


                Log.d(TAG, "Phone: " + phoneError + " Address:" + addressError +
                        " district: " + districtError);


                if (!(phoneError && addressError && districtError)) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                } else {

                    final String phoneData = editTextPhone.getText().toString();
                    final String extraPhoneData = editTextExtraPhone.getText().toString();
                    final String emailData = editTextEmail.getText().toString();
                    final String addressData = editTextAddress.getText().toString();
                    final String districtData = editTextDistrict.getText().toString();



                    SharedPreferences.Editor editor = sharedpreferencesOwnerContact.edit();

                    editor.putString(ownerPhoneKey, phoneData);
                    editor.putString(ownerExtraPhoneKey, extraPhoneData);
                    editor.putString(ownerEmailKey, emailData);
                    editor.putString(ownerAddressKey, addressData);
                    editor.putString(ownerDistrictKey, districtData);

                    editor.commit();
                    Toast.makeText(getContext(), "Thanks", Toast.LENGTH_LONG).show();

                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            PersonMDL personMDL = realm.where(PersonMDL.class).findAllSorted("createdDate").last();
                            personMDL.setPhoneNumber(phoneData);
                            personMDL.setAdditionalPhoneNumber((extraPhoneData.isEmpty()) ? "N/A" : extraPhoneData);
                            personMDL.setEmail((emailData.isEmpty()) ? "N/A" : emailData);
                            personMDL.setHouseAddress(addressData);
                            personMDL.setDistrict(districtData);
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getContext(), "Contact details updated", Toast.LENGTH_LONG).show();

                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(getContext(), "Contact update failed", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void initFields(View view) {
        textInputLayoutPhone = (TextInputLayout) view.findViewById(R.id.textInputLayoutPhoneNumber);
        textInputLayoutExtraPhone = (TextInputLayout) view.findViewById(R.id.textInputLayoutExtraPhoneNumber);
        textInputLayoutEmail = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutAddress = (TextInputLayout) view.findViewById(R.id.textInputLayoutAddress);
        textInputLayoutDistrict = (TextInputLayout) view.findViewById(R.id.textInputLayoutDistrict);

        editTextPhone = (EditText) view.findViewById(R.id.editTextPhoneNumber);
        editTextExtraPhone = (EditText) view.findViewById(R.id.editTextExtraPhoneNumber);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextAddress = (EditText) view.findViewById(R.id.editTextAddress);
        editTextDistrict = (EditText) view.findViewById(R.id.editTextDistrict);

        floatingActionButtonGetContact = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonContact);

        editTextPhone.addTextChangedListener(new ContactDetailsFormFragment.MyTextWatcher(editTextPhone));
        editTextAddress.addTextChangedListener(new ContactDetailsFormFragment.MyTextWatcher(editTextAddress));
        editTextDistrict.addTextChangedListener(new ContactDetailsFormFragment.MyTextWatcher(editTextDistrict));
        sharedpreferencesOwnerContact = this.getActivity().getSharedPreferences(OwnerContactDataPreferences, Context.MODE_PRIVATE);


    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()) {
                case R.id.editTextPhoneNumber:
                    GenUtils.isEmpty(editTextPhone, textInputLayoutPhone, "Phone required");
                    break;
                case R.id.editTextAddress:
                    GenUtils.isEmpty(editTextAddress, textInputLayoutAddress, "Address required");
                    break;
                case R.id.editTextDistrict:
                    GenUtils.isEmpty(editTextDistrict, textInputLayoutDistrict, "District required");
                    break;

                default:

            }
        }


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