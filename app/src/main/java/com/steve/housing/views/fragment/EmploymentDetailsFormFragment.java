package com.steve.housing.views.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.steve.housing.R;
import com.steve.housing.models.DistrictMDL;
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.utils.GenUtils;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmploymentDetailsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmploymentDetailsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmploymentDetailsFormFragment extends Fragment {


    public static final String ARG_PAGE = "ARG_PAGE";
    SharedPreferences sharedpreferencesOwnerEmploymentData;
    boolean professionError, employerError;
    private int mPage;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;

    private Spinner spinnerEmploymentStatus;
    private Spinner spinnerEmploymentSector;
    private EditText editTextEmployer, editTextProfession, editTextPosition, editTextWorkLocation;
    private TextInputLayout textInputLayoutEmployer, textInputLayoutProfession, textInputLayoutPosition,
            textInputLayoutWorkLocation;
    private FloatingActionButton floatingActionButtonGetEmployementData;
    private OnFragmentInteractionListener mListener;
    private String employmentStatus, employmentSector;

    public EmploymentDetailsFormFragment() {
        // Required empty public constructor
    }


    public static EmploymentDetailsFormFragment newInstance(int page) {
        EmploymentDetailsFormFragment fragment = new EmploymentDetailsFormFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
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
        View view = inflater.inflate(R.layout.fragment_employment_details_form, container, false);
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ArrayAdapter<CharSequence> adapterEmployementStatus = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_employment_status, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterEmployementStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerEmploymentStatus.setAdapter(adapterEmployementStatus);

        ArrayAdapter<CharSequence> adapterEmployementSector = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_employment_sector, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterEmployementSector.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerEmploymentSector.setAdapter(adapterEmployementSector);
        spinnerEmploymentStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                employmentStatus = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerEmploymentSector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                employmentSector = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        floatingActionButtonGetEmployementData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                professionError = GenUtils.isEmpty(editTextProfession, textInputLayoutProfession, "Profession required");
                employerError = GenUtils.isEmpty(editTextEmployer, textInputLayoutEmployer, "Employer required");

                if (!(professionError && employerError)) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                } else {


                    final String employerData = editTextEmployer.getText().toString();
                    final String professionData = editTextProfession.getText().toString();
                    final String positionData = editTextPosition.getText().toString();
                    final String officeLocation = editTextWorkLocation.getText().toString();

//
//                    SharedPreferences.Editor editor = sharedpreferencesOwnerEmploymentData.edit();
//
//                    editor.putString(ownerEmploymentSectorKey, employmentSector);
//                    editor.putString(ownerEmploymentStatusKey, employmentStatus);
//                    editor.putString(ownerEmployerKey, employerData);
//                    editor.putString(ownerProfessionKey, professionData);
//                    editor.putString(ownerPositionKey, positionData);
//                    editor.putString(ownerOfficeLocationKey, officeLocation);
//                    editor.commit();
//                    final SharedPreferences personalData = getActivity().getSharedPreferences(PdataPreferences, Context.MODE_PRIVATE);
//                    final SharedPreferences idData = getActivity().getSharedPreferences(OwnerContactDataPreferences, Context.MODE_PRIVATE);
//                    final SharedPreferences contactData = getActivity().getSharedPreferences(OwnerContactDataPreferences, Context.MODE_PRIVATE);
//                    final SharedPreferences languageData = getActivity().getSharedPreferences(OwenrLanguageDataPreferences, Context.MODE_PRIVATE);
//                    final SharedPreferences citizenshipData = getActivity().getSharedPreferences(OwnerCitizenshipDataPreferences, Context.MODE_PRIVATE);


                    Toast.makeText(getContext(), "Thanks", Toast.LENGTH_LONG).show();

//                    private String employer;
//                    private String employmentStatus;
//                    private String employmentSector;
//                    private String position;
//                    private String languageSpokenWritten;
//                    private String ResidentialAddress;

                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            OwnerMDL ownerMDL = realm.where(OwnerMDL.class).findAllSorted("createdDate").last();
                            ownerMDL.setEmployer((employerData.isEmpty()) ? "N/A" : employerData);
                            ownerMDL.setEmploymentSector((employmentSector.isEmpty()) ? "N/A" : employmentSector);
                            ownerMDL.setEmploymentStatus((employmentStatus.isEmpty()) ? "N/A" : employmentStatus);
                            ownerMDL.setPosition((positionData.isEmpty()) ? "N/A" : positionData);
                            ownerMDL.setProfession((professionData.isEmpty()) ? "N/A" : professionData);
                            ownerMDL.setWorkplaceLocation((officeLocation.isEmpty()) ? "N/A" : officeLocation);
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getContext(), "Employment  details updated", Toast.LENGTH_LONG).show();

                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(getContext(), "Contact update failed", Toast.LENGTH_LONG).show();

                        }
                    });
                    new MaterialDialog.Builder(getContext())
                            .title("RESULT")
                            .content(mRealm.where(OwnerMDL.class).findAll().toString())
                            .positiveText("OK")
                            .negativeText("DISMISS")
                            .show();
                    Toast.makeText(getContext(), mRealm.where(OwnerMDL.class).findAll().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


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

    public void initFields(View view) {

        spinnerEmploymentStatus = (Spinner) view.findViewById(R.id.spinnerEmploymentStatus);
        spinnerEmploymentSector = (Spinner) view.findViewById(R.id.spinnerEmploymentSector);

        textInputLayoutEmployer = (TextInputLayout) view.findViewById(R.id.textInputLayoutEmployer);
        textInputLayoutProfession = (TextInputLayout) view.findViewById(R.id.textInputLayoutProfession);
        textInputLayoutPosition = (TextInputLayout) view.findViewById(R.id.textInputLayoutPosition);
        textInputLayoutWorkLocation = (TextInputLayout) view.findViewById(R.id.textInputLayoutWorkplaceLocation);


        editTextEmployer = (EditText) view.findViewById(R.id.editTextEmployer);
        editTextProfession = (EditText) view.findViewById(R.id.editTextProfession);
        editTextPosition = (EditText) view.findViewById(R.id.editTextPosition);
        editTextWorkLocation = (EditText) view.findViewById(R.id.editTextWorkplaceLocation);

        floatingActionButtonGetEmployementData = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonEmploymentData);


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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                case R.id.editTextProfession:
                    GenUtils.isEmpty(editTextProfession, textInputLayoutProfession, "Profession required");
                    break;
                case R.id.editTextEmployer:
                    GenUtils.isEmpty(editTextEmployer, textInputLayoutEmployer, "Employer required");
                    break;

                default:

            }
        }
    }

    public ArrayList<String> retrieveDistricts() {
        ArrayList<String> regions = new ArrayList<>();

        RealmResults<DistrictMDL> realmResults = mRealm.where(DistrictMDL.class).findAll();


        for (DistrictMDL districtMDL : realmResults) {
            regions.add(districtMDL.getDistrict());
        }
        return regions;
    }
}