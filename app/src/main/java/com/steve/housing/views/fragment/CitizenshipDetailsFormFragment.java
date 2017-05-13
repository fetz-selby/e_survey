package com.steve.housing.views.fragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.OwnerMDL;

import java.util.ArrayList;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CitizenshipDetailsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CitizenshipDetailsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitizenshipDetailsFormFragment extends Fragment {


    public static final String ARG_PAGE = "ARG_PAGE";
    SharedPreferences sharedpreferencesOwnerCitizenship;
    FloatingActionButton floatingActionButtonCitizenship;
    String citizenshipTypedata;
    private Spinner spinnerTypeOfCitizenship;
    private AutoCompleteTextView autoCompleteTextViewFirstCountry;
    private AutoCompleteTextView autoCompleteTextViewSecondCountry;
    private EditText editTextEthnicity, editTextDob, editTextBirthPlace;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;

    private OnFragmentInteractionListener mListener;

    public CitizenshipDetailsFormFragment() {
        // Required empty public constructor
    }


    public static CitizenshipDetailsFormFragment newInstance(int page) {
        CitizenshipDetailsFormFragment fragment = new CitizenshipDetailsFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_citizenship_details_form, container, false);
        initViews(view);
        mRealm = Realm.getDefaultInstance();


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> countries = getCountries();
        ArrayAdapter<String> firstCountryAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, countries);
        ArrayAdapter<String> seconcCountryAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, countries);
        autoCompleteTextViewFirstCountry.setAdapter(firstCountryAdapter);
        autoCompleteTextViewFirstCountry.setThreshold(1);
        autoCompleteTextViewSecondCountry.setAdapter(seconcCountryAdapter);
        autoCompleteTextViewSecondCountry.setThreshold(1);


        ArrayAdapter<CharSequence> adapterCitizenship = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_citizenship, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterCitizenship.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerTypeOfCitizenship.setAdapter(adapterCitizenship);

        spinnerTypeOfCitizenship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                citizenshipTypedata = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        floatingActionButtonCitizenship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((autoCompleteTextViewFirstCountry.getText().toString().isEmpty() && editTextBirthPlace.getText().toString().isEmpty() && editTextDob.getText().toString().isEmpty())) {
                    Toast.makeText(getContext(), "DOB,country of origin and Birth place needed", Toast.LENGTH_LONG).show();

//                    public static final String ownerDateofBirthKey = "ownerDateOfBirthKey";
//                    public static final String ownerCitizenshipTypeKey = "ownerCitizenshipTypeKey";
//                    public static final String ownerBirthPlaceKey = "ownerBirthPlaceKey";
//                    public static final String ownerSecondCountryKey = "ownerSecondCountryKey";
//                    public static final String ownerNationalityKey = "ownerNationalityKey";
//                    public static final String ownerEthnicityKey = "ownerEthnicityKey";
                } else {

                    final String dobData = editTextDob.getText().toString();
                    final String secondCountryData = autoCompleteTextViewSecondCountry.getText().toString();
                    final String ethnicityData = editTextEthnicity.getText().toString();
                    final String birthPlaceData = editTextBirthPlace.getText().toString();
                    final String nationalityData = autoCompleteTextViewFirstCountry.getText().toString();

//                    private String nationalityType;
//                    private String nationality;
//                    private String dualCityzenship;
//                    private String ethnicity;
//                    private String birthPlace;
//                    private String dateOfBirth;
                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            OwnerMDL ownerMDL = realm.where(OwnerMDL.class).findAllSorted("createdDate").last();
                            ownerMDL.setNationalityType(citizenshipTypedata);
                            ownerMDL.setNationality(nationalityData);
                            ownerMDL.setDualCityzenship((secondCountryData.isEmpty()) ? "N/A" : secondCountryData);
                            ownerMDL.setEthnicity((ethnicityData.isEmpty()) ? "N/A" : ethnicityData);
                            ownerMDL.setBirthPlace(birthPlaceData);
                            ownerMDL.setDateOfBirth(dobData);
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getContext(), "Citizenship details updated", Toast.LENGTH_LONG).show();

                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(getContext(), "Citizenship  update failed", Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }
        });


    }

    private void initViews(View view) {
        autoCompleteTextViewFirstCountry = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewFirstCountry);
        autoCompleteTextViewSecondCountry = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewSecondCountry);
        spinnerTypeOfCitizenship = (Spinner) view.findViewById(R.id.spinnerCitizenshipType);
        editTextEthnicity = (EditText) view.findViewById(R.id.editTextEthnicity);
        editTextDob = (EditText) view.findViewById(R.id.editTextDateOfBirth);
        editTextBirthPlace = (EditText) view.findViewById(R.id.editTextPlaceOfBirth);
        floatingActionButtonCitizenship = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonCitizenship);

    }

    @NonNull
    private ArrayList<String> getCountries() {
        //        get list of countries
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        return countries;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
