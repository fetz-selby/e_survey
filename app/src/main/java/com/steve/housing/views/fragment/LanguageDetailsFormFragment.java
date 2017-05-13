package com.steve.housing.views.fragment;

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
import android.widget.EditText;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.utils.GenUtils;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LanguageDetailsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LanguageDetailsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguageDetailsFormFragment extends Fragment {
//    private ImageButton btnAddLanguage;
//    private Spinner spinnerPrimaryLanguage;
//    private Spinner spinnerSecondaryLanguage;
//    private EditText editTextPrimaryLanguage;
//    private ArrayAdapter<CharSequence> adapter;

    public static final String ARG_PAGE = "ARG_PAGE";
    EditText editTextLanguageSpoken, editTextLanguageWritten, editTextLanguageSpokenWrtitten;
    TextInputLayout textInputLayoutLanguageSpoken, textInputLayoutLanguageWritten, textInputLayoutLanguageSpokenWritten;
    FloatingActionButton floatingActionButtonAddLanguage;
    SharedPreferences sharedpreferencesOwnerLanguageData;
    private OnFragmentInteractionListener mListener;
    private int mPage;
    private boolean languageSpokenWrittenError, languageSpokenError, languageWrittenError;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;


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
        mRealm = Realm.getDefaultInstance();
        // Inflate the layout for this fragment
//        adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.items_languages, android.R.layout.simple_spinner_dropdown_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        editTextPrimaryLanguage = (EditText) view.findViewById(R.id.editTextPrimaryLanguage);
//        spinnerPrimaryLanguage = (Spinner) view.findViewById(R.id.spinnerPrimaryLanguage);
//        spinnerPrimaryLanguage.setAdapter(adapter);
//        final LinearLayout linearLayoutFormLanguage = (LinearLayout) view.findViewById(R.id.linearLayoutLanguage);
//        btnAddLanguage = (ImageButton) view.findViewById(R.id.buttonAddLanguage);
//        addMoreLanguage(linearLayoutFormLanguage, btnAddLanguage);
        initFields(view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButtonAddLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                languageSpokenError = GenUtils.isEmpty(editTextLanguageSpoken, textInputLayoutLanguageSpoken, "Language spoken required");
                languageWrittenError = GenUtils.isEmpty(editTextLanguageWritten, textInputLayoutLanguageWritten, "Language written required");
                languageSpokenWrittenError = GenUtils.isEmpty(editTextLanguageSpokenWrtitten, textInputLayoutLanguageSpokenWritten, "Language spoken and written required");

                if (!(languageSpokenError && languageWrittenError && languageSpokenWrittenError)) {
                    Toast.makeText(getContext(), "Error check Fields", Toast.LENGTH_LONG).show();
                } else {
                    final String languageSpokenData = editTextLanguageSpoken.getText().toString();
                    final String languageWrittenData = editTextLanguageWritten.getText().toString();
                    final String languageSpokenWrittenData = editTextLanguageSpokenWrtitten.getText().toString();


                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            OwnerMDL ownerMDL = realm.where(OwnerMDL.class).findAllSorted("createdDate").last();
                            ownerMDL.setLanguageSpoken(languageSpokenData);
                            ownerMDL.setLanguageWritten(languageWrittenData);
                            ownerMDL.setGetLanguageSpokenWritten(languageSpokenWrittenData);

                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getContext(), "Language details updated", Toast.LENGTH_LONG).show();

                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(getContext(), "Language update failed", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });

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

    public void initFields(View view) {
        editTextLanguageSpoken = (EditText) view.findViewById(R.id.editTextLanguageSpoken);
        editTextLanguageWritten = (EditText) view.findViewById(R.id.editTextWriten);
        editTextLanguageSpokenWrtitten = (EditText) view.findViewById(R.id.editTextSpokenWritten);
        floatingActionButtonAddLanguage = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonGetLanguages);
        textInputLayoutLanguageSpoken = (TextInputLayout) view.findViewById(R.id.textInputLayoutLanguageSpoken);
        textInputLayoutLanguageSpokenWritten = (TextInputLayout) view.findViewById(R.id.textInputLayoutSpokenWritten);
        textInputLayoutLanguageWritten = (TextInputLayout) view.findViewById(R.id.textInputLayoutWritten);

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

//    private void addMoreLanguage(final LinearLayout linearLayoutForm, ImageButton imageButton) {
//        imageButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                final RelativeLayout newView = (RelativeLayout) getActivity().getLayoutInflater()
//                        .inflate(R.layout.row_detail, null);
//                newView.setLayoutParams(new LinearLayout.
//                        LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
//                        , ViewGroup.LayoutParams.WRAP_CONTENT));
//                spinnerSecondaryLanguage = (Spinner) newView.findViewById(R.id.spinnerLanguage);
//
//                // Apply the adapter to the spinner
//                spinnerSecondaryLanguage.setAdapter(adapter);
//
//                ImageButton btnRemove = (ImageButton) newView.findViewById(R.id.btnRemove);
//                btnRemove.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        linearLayoutForm.removeView(newView);
//                    }
//                });
//                linearLayoutForm.addView(newView);
//            }
//        });
//    }


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
                case R.id.editTextLanguageSpoken:
                    GenUtils.isEmpty(editTextLanguageSpoken, textInputLayoutLanguageSpoken, "Language Spoken required");
                    break;
                case R.id.editTextWriten:
                    GenUtils.isEmpty(editTextLanguageWritten, textInputLayoutLanguageWritten, "Language written required");
                    break;
                case R.id.editTextSpokenWritten:
                    GenUtils.isEmpty(editTextLanguageSpokenWrtitten, textInputLayoutLanguageSpokenWritten, "Language written and written required");
                    break;

                default:

            }
        }
    }
}
