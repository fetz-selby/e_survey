package com.steve.housing.views.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
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
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.utils.GenUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;


public class PersonalDetailsFormFragment extends Fragment {

    private static final String TAG = PersonalDetailsFormFragment.class.getSimpleName();
    private static final int SELECT_FILE = 100;
    private static final int REQUEST_CAMERA = 101;
    private static final int PIC_CROP = 102;
    private ImageView imageProfile;
    private Spinner spinnerMaritalStatus;
    private Spinner spinnerTypeOfDisabilities;
    private TextInputLayout firstnameTIL, lastnameTIL, othernameTIL;
    private EditText firstnameET, lastnameET, othernameET;
    private String maritalStatus;
    private String disability;
    private FloatingActionButton fab;
    private boolean firstnameErr, lastnameErr, othernameErr, maritalErr, disabilityErr;
    private Realm mRealm;
    private Uri mPhotoURI;
    private String data;
    private String encodedImage = "";
    private RealmAsyncTask realmAsyncTask;


    public PersonalDetailsFormFragment() {
        // Required empty public constructor
    }

    public static PersonalDetailsFormFragment newInstance(String name) {
        PersonalDetailsFormFragment fragment = new PersonalDetailsFormFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_details_form, container, false);

        mRealm = Realm.getDefaultInstance();


        initFields(view);


        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adapterMaritalStatus = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_marital_status, android.R.layout.simple_spinner_item);
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaritalStatus.setAdapter(adapterMaritalStatus);

        ArrayAdapter<CharSequence> adapterDisabilities = ArrayAdapter.createFromResource(getActivity(),
                R.array.items_disabilities, android.R.layout.simple_spinner_item);
        adapterDisabilities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeOfDisabilities.setAdapter(adapterDisabilities);

        imageProfile.setOnClickListener(new View.OnClickListener() {
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

        spinnerMaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maritalStatus = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTypeOfDisabilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                disability = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstnameErr = GenUtils.isEmpty(firstnameET, firstnameTIL, "Firstname required");
                lastnameErr = GenUtils.isEmpty(lastnameET, lastnameTIL, "Lastname required");
                othernameErr = GenUtils.isEmpty(othernameET, othernameTIL, "Othername required");
                maritalErr = (maritalStatus.contains("Status"));
                disabilityErr = (disability.contains("Status"));

                Log.d(TAG, "firstname: " + firstnameErr + " lastname :" + lastnameErr +
                        " othername: " + othernameErr + " marital: " + maritalErr + " disability: " + disabilityErr);


                if (!(firstnameErr && lastnameErr && othernameErr && !maritalErr && !disabilityErr)) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                } else {

                    final String firstNameData = firstnameET.getText().toString();
                    final String lastNameData = lastnameET.getText().toString();
                    final String otherNameData = othernameET.getText().toString();
                    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    final Date date = new Date();


                    Toast.makeText(getContext(), "Thanks", Toast.LENGTH_LONG).show();

                    //Toast.makeText(getContext(), "No Error", Toast.LENGTH_LONG).show();
//                    onPersonalDetailsDataSetListener.setName(firstnameET.getText().toString().trim());

//                    mRealm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//
//                            String id = UUID.randomUUID().toString();
//                            OwnerMDL personMDL = realm.createObject(OwnerMDL.class, id);
//                            personMDL.setFirstname(firstnameET.getText().toString().trim());
//                            personMDL.setLastname(lastnameET.getText().toString().trim());
//                            personMDL.setOthername(othernameET.getText().toString().trim());
//                            personMDL.setMaritalStatus(maritalStatus);
//                            personMDL.setDisability(disability);
//                            personMDL.setImagedata(encodedImage);
//
//                            GenUtils.getToastMessage(getActivity(), "Personal Info Saved Successfully");
//                        }
//                    });


                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            String id = UUID.randomUUID().toString();
                            OwnerMDL ownerMDL = realm.createObject(OwnerMDL.class, id);
                            // personal data
                            ownerMDL.setFirstname(firstnameET.getText().toString().trim());
                            ownerMDL.setLastname(lastnameET.getText().toString().trim());
                            ownerMDL.setOthername(othernameET.getText().toString().trim());
                            ownerMDL.setMaritalStatus(maritalStatus);
                            ownerMDL.setDisability(disability);
                            ownerMDL.setOwnerPhoto(encodedImage);
                            ownerMDL.setCreatedDate(dateFormat.format(date));
                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();

                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

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

        Log.d(TAG, "Photo File " + photoFile);

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
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 128);
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

    private void onCroppedFinished(Bitmap bitmap) {


        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();

            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
            imageProfile.setImageBitmap(bitmap);
            Log.d(TAG, "Ecoded image: " + encodedImage);
            //mRemove.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public void initFields(View view) {
        spinnerMaritalStatus = (Spinner) view.findViewById(R.id.spinnerMaritalStatus);
        spinnerTypeOfDisabilities = (Spinner) view.findViewById(R.id.spinnerDisabilities);
        imageProfile = (ImageView) view.findViewById(R.id.profile_image_owner);

        firstnameTIL = (TextInputLayout) view.findViewById(R.id.firstnameTIL);
        lastnameTIL = (TextInputLayout) view.findViewById(R.id.lastnameTIL);
        othernameTIL = (TextInputLayout) view.findViewById(R.id.othernameTIL);

        firstnameET = (EditText) view.findViewById(R.id.firstnameET);
        lastnameET = (EditText) view.findViewById(R.id.lastnameET);
        othernameET = (EditText) view.findViewById(R.id.othernameET);

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonPdata);

        firstnameET.addTextChangedListener(new MyTextWatcher(firstnameET));
        lastnameET.addTextChangedListener(new MyTextWatcher(lastnameET));
        othernameET.addTextChangedListener(new MyTextWatcher(othernameET));


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            onPersonalDetailsDataSetListener = (OnPersonalDetailsDataSetListener) context;
//        }catch (Exception e ){}


    }

//    public interface OnPersonalDetailsDataSetListener {
//        public void setName(String name);
//
//    }

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
                case R.id.firstnameET:
                    GenUtils.isEmpty(firstnameET, lastnameTIL, "Firstname required");
                    break;
                case R.id.lastnameET:
                    GenUtils.isEmpty(lastnameET, lastnameTIL, "Lastname required");
                    break;
                case R.id.othernameET:
                    GenUtils.isEmpty(othernameET, othernameTIL, "Othername required");
                    break;

                default:

            }
        }
    }
}
