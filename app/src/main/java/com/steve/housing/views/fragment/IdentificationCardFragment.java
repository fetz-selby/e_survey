package com.steve.housing.views.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.steve.housing.R;
import com.steve.housing.models.PersonMDL;
import com.steve.housing.utils.GenUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

import static com.steve.housing.utils.Constants.IdDataPreferences;
import static com.steve.housing.utils.Constants.idImageKey;
import static com.steve.housing.utils.Constants.idTextKey;
import static com.steve.housing.utils.Constants.idTypeKey;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IdentificationCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IdentificationCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdentificationCardFragment extends Fragment {

    private static final String TAG = IdentificationCardFragment.class.getSimpleName();


    private ImageView imageId;
    private Spinner spinnerIdCard;
    private TextInputLayout idTIL;
    private EditText idET;
    private String idText, idType;
    private FloatingActionButton fab;
    private boolean idErr, spinnerIdCardErr;
    private Realm mRealm;
    private RealmAsyncTask realmAsyncTask;
    private static final int SELECT_FILE = 100;
    private static final int REQUEST_CAMERA = 101;
    private static final int PIC_CROP = 102;
    private Uri mPhotoURI;
    private String encodedImage = "";
    private OnFragmentInteractionListener mListener;
    private SharedPreferences sharedpreferencesOwnerID;


    SharedPreferences sharedpreferencesPersonalData;


    public IdentificationCardFragment() {
        // Required empty public constructor
    }

    public static IdentificationCardFragment newInstance(String param1, String param2) {
        IdentificationCardFragment fragment = new IdentificationCardFragment();

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
        View view = inflater.inflate(R.layout.fragment_identification_card, container, false);
        mRealm = Realm.getDefaultInstance();

        initFields(view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<CharSequence> adapterDisabilities = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_id_type, android.R.layout.simple_spinner_item);
        adapterDisabilities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIdCard.setAdapter(adapterDisabilities);

        imageId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getContext())
                        .title(R.string.picture_title)
                        .items(R.array.items_picture_menu)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(getContext(), " " + which, Toast.LENGTH_LONG).show();

                                switch (which) {
//                                    case 0:
//                                        galleryIntent();
//                                        break;
                                    case 0:
                                        cameraIntent();
                                        break;
                                }
                                return true;
                            }
                        })

                        .show();

            }
        });


        spinnerIdCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idText = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                idErr = GenUtils.isEmpty(idET, idTIL, "ID required");
                spinnerIdCardErr = idText.isEmpty();

                Log.d(TAG, "ID: " + idErr);


                if (!(idErr)) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                } else {

                    String idData = idET.getText().toString();

                    SharedPreferences.Editor editor = sharedpreferencesOwnerID.edit();

                    editor.putString(idTextKey, idData);
                    editor.putString(idTypeKey, idText);
                    editor.putString(idImageKey, encodedImage);
                    editor.commit();
                    Toast.makeText(getContext(), "Thanks", Toast.LENGTH_LONG).show();

                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            PersonMDL personMDL = realm.where(PersonMDL.class).findAllSorted("createdDate").last();
                            personMDL.setIdentificationNumber(idText);
                            personMDL.setIdentificationType(idType);
                            personMDL.setIdentificationPicture(encodedImage);
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getContext(), "ID details updated", Toast.LENGTH_LONG).show();

                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(getContext(), "ID details update failed", Toast.LENGTH_LONG).show();

                        }
                    });


                    //Toast.makeText(getContext(), "No Error", Toast.LENGTH_LONG).show();

//                    mRealm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//
//                            String id = UUID.randomUUID().toString();
//                            PersonMDL personMDL = realm.createObject(PersonMDL.class, id);
//
//                            personMDL.setImagedata(encodedImage);
//
//                            GenUtils.getToastMessage(getActivity(), "Personal Info Saved Successfully");
//                        }
//                    });
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_FILE:
                Uri imageUri = data.getData();
                Log.d(TAG, "Select Croped Uri: " + imageUri.getEncodedPath());
                performCrop(imageUri);
                break;
            case REQUEST_CAMERA:
                performCrop(mPhotoURI);
                break;
            case PIC_CROP:
                Bundle extras = data.getExtras();
                // get the cropped bitmap
                Bitmap selectedBitmap = extras.getParcelable("data");
                onCroppedFinished(selectedBitmap);
                break;
        }


    }


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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {

        Intent intent = new Intent();

        File photoFile = null;

        try {
            photoFile = createImageFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "ID Photo " + photoFile);

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(intent, REQUEST_CAMERA);

    }

    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        mPhotoURI = Uri.fromFile(image);

        return image;
    }

    private void performCrop(Uri picUri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", true);
            cropIntent.putExtra("aspectX", 2);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 128);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            GenUtils.getToastMessage(getContext(), errorMessage);
        }
    }

    public void initFields(View view) {

        spinnerIdCard = (Spinner) view.findViewById(R.id.spinnerIdCard);
        imageId = (ImageView) view.findViewById(R.id.imageId);
        idET = (EditText) view.findViewById(R.id.idET);
        idTIL = (TextInputLayout) view.findViewById(R.id.idTIL);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonIdPick);
        idET.addTextChangedListener(new IdentificationCardFragment.MyTextWatcher(idET));
        sharedpreferencesOwnerID = this.getActivity().getSharedPreferences(IdDataPreferences, Context.MODE_PRIVATE);


    }


    private void onCroppedFinished(Bitmap bitmap) {


        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();

            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            imageId.setImageBitmap(bitmap);
            Log.d(TAG, "Ecoded image: " + encodedImage);
            //mRemove.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                case R.id.idET:
                    GenUtils.isEmpty(idET, idTIL, "ID Required");
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
