package com.steve.housing.views.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.steve.housing.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class PersonalDetailsFormFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private CircleImageView imageProfile;
    private Spinner spinnerMaritalStatus;
    private Spinner spinnerTypeOfDisabilities;

    public PersonalDetailsFormFragment() {
        // Required empty public constructor
    }

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static PersonalDetailsFormFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PersonalDetailsFormFragment fragment = new PersonalDetailsFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPage = getArguments().getInt(ARG_PAGE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_personal_details_form, container, false);
        spinnerMaritalStatus = (Spinner) view.findViewById(R.id.spinnerMaritalStatus);
        spinnerTypeOfDisabilities = (Spinner)view.findViewById(R.id.spinnerDisabilities);

        ArrayAdapter<CharSequence> adapterMaritalStatus = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_marital_status, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerMaritalStatus.setAdapter(adapterMaritalStatus);

        ArrayAdapter<CharSequence> adapterDisabilities = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_disabilities, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterDisabilities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerTypeOfDisabilities.setAdapter(adapterDisabilities);

        imageProfile = (CircleImageView) view.findViewById(R.id.profile_image_owner);
        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(R.string.picture_title)
                        .items(R.array.items_picture_menu)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/

                                Toast.makeText(getContext(), " " + which, Toast.LENGTH_LONG).show();
                                return true;
                            }
                        })

                        .show();

            }
        });


        return view;

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
