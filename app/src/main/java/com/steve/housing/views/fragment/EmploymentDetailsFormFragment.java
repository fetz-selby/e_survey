package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.steve.housing.R;

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

    private int mPage;
    private Spinner spinnerEmploymentStatus;
    private Spinner spinnerEmploymentSector;

    private OnFragmentInteractionListener mListener;

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
        spinnerEmploymentStatus = (Spinner) view.findViewById(R.id.spinnerEmploymentStatus);
        spinnerEmploymentSector = (Spinner) view.findViewById(R.id.spinnerEmploymentSector);
        ArrayAdapter<CharSequence> adapterEmployementStatus = ArrayAdapter.createFromResource(getActivity(),
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
