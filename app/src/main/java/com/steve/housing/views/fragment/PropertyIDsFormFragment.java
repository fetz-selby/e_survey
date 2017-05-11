package com.steve.housing.views.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropertyIDsFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropertyIDsFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyIDsFormFragment extends Fragment {

    private Realm mRealm;

    private RealmAsyncTask realmAsyncTask;
    private EditText editTextIdenture, editTextTitleNumber, editTextPins;
    private FloatingActionButton floatingActionButtonPropertyIds;
    String identure , titleNumber, pins;


    private OnFragmentInteractionListener mListener;

    public PropertyIDsFormFragment() {
        // Required empty public constructor
    }


    public static PropertyIDsFormFragment newInstance(String param1, String param2) {
        PropertyIDsFormFragment fragment = new PropertyIDsFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_property_ids_form, container, false);
        mRealm = Realm.getDefaultInstance();
        initViews(view);

        floatingActionButtonPropertyIds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pins = editTextPins.getText().toString();
                identure = editTextIdenture.getText().toString();
                titleNumber = editTextTitleNumber.getText().toString();
                realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        PropertyMDL propertyMDL = realm.where(PropertyMDL.class).findAllSorted("createdDate").last();
                        propertyMDL.setIdentureNumber((identure.isEmpty()) ? "N/A" : identure);
                        propertyMDL.setTitleNumber((titleNumber.isEmpty()) ? "N/A" : titleNumber);
                        propertyMDL.setPins((pins.isEmpty()) ? "N/A" : pins);

                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getContext(), " details updated", Toast.LENGTH_LONG).show();

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(getContext(), "update failed", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });
        return view;
    }

    private void initViews(View view) {
        mRealm = Realm.getDefaultInstance();
        editTextIdenture = (EditText) view.findViewById(R.id.editTextIdentureNumber);
        editTextPins = (EditText) view.findViewById(R.id.editTextPin);
        editTextTitleNumber = (EditText) view.findViewById(R.id.editTextTitleRegistrationNumber);
        floatingActionButtonPropertyIds = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonPropertyId);
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


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
