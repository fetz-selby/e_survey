package com.steve.housing.views.fragment;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.steve.housing.R;

import java.util.ArrayList;
import java.util.Locale;

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
    private Spinner spinnerTypeOfCitizenship;
    private AutoCompleteTextView autoCompleteTextViewFirstCountry;
    private AutoCompleteTextView autoCompleteTextViewSecondCountry;


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
        autoCompleteTextViewFirstCountry = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewFirstCountry);
        autoCompleteTextViewSecondCountry = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewSecondCountry);
        spinnerTypeOfCitizenship = (Spinner) view.findViewById(R.id.spinnerCitizenshipType);
        ArrayList<String> countries = getCountries();
        ArrayAdapter<String> firstCountryAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, countries);
        ArrayAdapter<String> seconcCountryAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, countries);
        autoCompleteTextViewFirstCountry.setAdapter(firstCountryAdapter);
        autoCompleteTextViewFirstCountry.setThreshold(1);
        autoCompleteTextViewSecondCountry.setAdapter(seconcCountryAdapter);
        autoCompleteTextViewSecondCountry.setThreshold(1);
        autoCompleteTextViewSecondCountry.setEnabled(false);
        autoCompleteTextViewSecondCountry.setFocusable(false);



        ArrayAdapter<CharSequence> adapterCitizenship = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_citizenship, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterCitizenship.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerTypeOfCitizenship.setAdapter(adapterCitizenship);
        String typeOfCitizenship = (String) spinnerTypeOfCitizenship.getSelectedItem();

//        spinnerTypeOfCitizenship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        return view;
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
//
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

    public void onButtonClicked(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getFragmentManager(),"Date Picker");
    }
}
