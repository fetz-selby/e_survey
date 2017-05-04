package com.steve.housing.views.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.steve.housing.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LanguageDetailsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LanguageDetailsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguageDetailsFormFragment extends Fragment {
    private ImageButton btnAddLanguage;
    private Spinner spinnerPrimaryLanguage;
    private Spinner spinnerSecondaryLanguage;
    private EditText editTextPrimaryLanguage;
    private ArrayAdapter<CharSequence> adapter;
    private OnFragmentInteractionListener mListener;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;


    public LanguageDetailsFormFragment() {
        // Required empty public constructor
    }


    public static LanguageDetailsFormFragment newInstance(int page) {
        LanguageDetailsFormFragment fragment = new LanguageDetailsFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_language_details_form, container, false);
        // Inflate the layout for this fragment
        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_languages, android.R.layout.simple_spinner_dropdown_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTextPrimaryLanguage = (EditText) view.findViewById(R.id.editTextPrimaryLanguage);
        spinnerPrimaryLanguage = (Spinner) view.findViewById(R.id.spinnerPrimaryLanguage);
        spinnerPrimaryLanguage.setAdapter(adapter);
        final LinearLayout linearLayoutFormLanguage = (LinearLayout) view.findViewById(R.id.linearLayoutLanguage);
        btnAddLanguage = (ImageButton) view.findViewById(R.id.buttonAddLanguage);
        addMoreLanguage(linearLayoutFormLanguage, btnAddLanguage);

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

    private void addMoreLanguage(final LinearLayout linearLayoutForm, ImageButton imageButton) {
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final RelativeLayout newView = (RelativeLayout) getActivity().getLayoutInflater()
                        .inflate(R.layout.row_detail, null);
                newView.setLayoutParams(new LinearLayout.
                        LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT));
                spinnerSecondaryLanguage = (Spinner) newView.findViewById(R.id.spinnerLanguage);

                // Apply the adapter to the spinner
                spinnerSecondaryLanguage.setAdapter(adapter);

                ImageButton btnRemove = (ImageButton) newView.findViewById(R.id.btnRemove);
                btnRemove.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        linearLayoutForm.removeView(newView);
                    }
                });
                linearLayoutForm.addView(newView);
            }
        });
    }
}
