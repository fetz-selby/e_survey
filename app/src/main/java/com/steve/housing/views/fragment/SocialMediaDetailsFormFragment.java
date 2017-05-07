package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.steve.housing.R;

public class SocialMediaDetailsFormFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    public static final String ARG_PAGE = "ARG_PAGE";
    private Spinner spinnerSmartPhoneFeature;

    private int mPage;


    public SocialMediaDetailsFormFragment() {
        // Required empty public constructor
    }


    public static SocialMediaDetailsFormFragment newInstance(int page) {
        SocialMediaDetailsFormFragment fragment = new SocialMediaDetailsFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_social_media_details_form, container, false);
        spinnerSmartPhoneFeature = (Spinner) view.findViewById(R.id.spinnerPhoneFeature);
        final ArrayAdapter<CharSequence> adapterSmartPhoneFeature = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_yes_or_no, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterSmartPhoneFeature.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerSmartPhoneFeature.setAdapter(adapterSmartPhoneFeature);

        spinnerSmartPhoneFeature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"" +adapterSmartPhoneFeature.getItem(position), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
