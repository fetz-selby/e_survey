package com.steve.housing.views.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.steve.housing.R;
import com.steve.housing.utils.CameraUtils;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IdentificationCardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IdentificationCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdentificationCardFragment extends Fragment {

    //this is the image view for show your picture taken,
    // if you click in this show the picture in all screen
    private ImageView imageView;
    //button to take picture
    private ImageButton btntakephoto;
    //button to select picture of your device
    private ImageButton btnselectedphoto;
    //button for save the photo in device
    private ImageButton saveImage;
    //button for go to fragment
//    private Button btnGoTo;
//    private TextView texttitle;
    private Spinner idTypeSpinner;

//    private FloatingActionMenu floatingBtnMenu;

    //    private FrameLayout frame;
    private View principalLayout;

//    //button for realized the facial recognition of your picture
//    private FloatingActionButton floatingBtnFacialRecognition;
//    //see the information data of the picture
//    private FloatingActionButton floatingBtnPhotoInformation;
//    //button for rotate the image in bitmap and in image view
//    private FloatingActionButton floatingBtnRotate;
//    //button for view the string base 64 trasnform the bitmap
//    private FloatingActionButton floatingBtnSeeString64;

    //Ever you need to call magical camera and permissionGranted
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 80;
    private Activity activity;
    private OnFragmentInteractionListener mListener;

    public IdentificationCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IdentificationCardFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        View rootView = inflater.inflate(R.layout.fragment_identification_card, container, false);

        //obtain the activity of the parent
        activity = getActivity();
        setUIComponents(rootView);

        //instance magical camera
        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        magicalPermissions = new MagicalPermissions(this, permissions);
        magicalCamera = new MagicalCamera(activity, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

        btntakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call the method of take the picture
                magicalCamera.takeFragmentPhoto(IdentificationCardFragment.this);
            }
        });

        btnselectedphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is the form to select picture of device
                magicalCamera.selectFragmentPicture(IdentificationCardFragment.this, "My Header Example");
            }
        });

//        saveImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//                    //save the photo in your memory external or internal of your device
//                    String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "myTestPhoto", MagicalCamera.JPEG, true);
//
//                    if (path != null) {
//                        CameraUtils.viewSnackBar(getString(R.string.message_save_manual) + path, principalLayout);
//                    } else {
//                        CameraUtils.viewSnackBar(getString(R.string.error_dont_save_photo), principalLayout);
//                    }
//                }
//            }
//        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//
//                    CameraUtils.magicalCameraBitmap = magicalCamera.getPhoto();
////                    Intent intent = new Intent(activity, ImageViewActivity.class);
////                    startActivity(intent);
//                }
//            }
//        });

//        floatingBtnRotate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//                    //for once click rotate the picture in 90ª, and set in the image view.
//                    magicalCamera.setPhoto(magicalCamera.rotatePicture(magicalCamera.getPhoto(), MagicalCamera.ORIENTATION_ROTATE_90));
//                    imageView.setImageBitmap(magicalCamera.getPhoto());
//                }
//            }
//        });

//        floatingBtnFacialRecognition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//                    Bitmap faceDetectorBitmap = magicalCamera.faceDetector(50, Color.GREEN);
//                    if (faceDetectorBitmap != null) {
//                        imageView.setImageBitmap(faceDetectorBitmap);
//                        List<Landmark> listMark = magicalCamera.getFaceRecognitionInformation().getListLandMarkPhoto();
//                    } else {
//                        CameraUtils.viewSnackBar(getString(R.string.error_not_detect_face), principalLayout);
//                    }
//                }
//            }
//        });

//        floatingBtnSeeString64.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//
//                    try {
//                        //convert the bitmap to bytes array
//                        byte[] bytesArray = ConvertSimpleImage.bitmapToBytes(magicalCamera.getPhoto(), CameraUtils.getFormat(activity));
//                        //convert the bytes to string 64, with this form is easly to send by web service or store data in DB
//                        String imageBase64 = ConvertSimpleImage.bytesToStringBase64(bytesArray);
//
//                        /*****************************************************************
//                         *                    Revert the process
//                         *****************************************************************/
//                        //if you need to revert the process
//                        //byte[] anotherArrayBytes = ConvertSimpleImage.stringBase64ToBytes(imageBase64);
//                        //again deserialize the image
//                        //Bitmap myImageAgain = ConvertSimpleImage.bytesToBitmap(anotherArrayBytes);
//
//                        String base64 = imageBase64.substring(0, 300);
//
//                        new MaterialDialog.Builder(activity)
//                                .title(getString(R.string.convert_to_string_base_64_title))
//                                .content(base64)
//                                .positiveText(getString(R.string.message_ok))
//                                .show();
//                    } catch (Exception e) {
//                        CameraUtils.viewSnackBar(getString(R.string.error_string_base_64), principalLayout);
//                    }
//                }
//            }
//        });

//        floatingBtnPhotoInformation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//                    //for see the data you need to call this method ever
//                    //if the function return true you have the posibility of see the data
//                    if (magicalCamera.initImageInformation()) {
//
//                        StringBuilder builderInformation = new StringBuilder();
//
//                        //question in have data
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getLatitude() + ""))
//                            builderInformation.append(getString(R.string.info_data_latitude) + magicalCamera.getPrivateInformation().getLatitude() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getLatitudeReference()))
//                            builderInformation.append(getString(R.string.info_data_latitude_referene) + magicalCamera.getPrivateInformation().getLatitudeReference() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getLongitude() + ""))
//                            builderInformation.append(getString(R.string.info_data_longitude) + magicalCamera.getPrivateInformation().getLongitude() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getLongitudeReference()))
//                            builderInformation.append(getString(R.string.info_data_longitude_reference) + magicalCamera.getPrivateInformation().getLongitudeReference() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getDateTimeTakePhoto()))
//                            builderInformation.append(getString(R.string.info_data_date_time_photo) + magicalCamera.getPrivateInformation().getDateTimeTakePhoto() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getDateStamp()))
//                            builderInformation.append(getString(R.string.info_data_date_stamp_photo) + magicalCamera.getPrivateInformation().getDateStamp() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getIso()))
//                            builderInformation.append(getString(R.string.info_data_ISO) + magicalCamera.getPrivateInformation().getIso() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getOrientation()))
//                            builderInformation.append(getString(R.string.info_data_orientation_photo) + magicalCamera.getPrivateInformation().getOrientation() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getImageLength()))
//                            builderInformation.append(getString(R.string.info_data_image_lenght) + magicalCamera.getPrivateInformation().getImageLength() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getImageWidth()))
//                            builderInformation.append(getString(R.string.info_data_image_width) + magicalCamera.getPrivateInformation().getImageWidth() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getModelDevice()))
//                            builderInformation.append(getString(R.string.info_data_model_device) + magicalCamera.getPrivateInformation().getModelDevice() + "\n");
//
//                        if (Utils.notNullNotFill(magicalCamera.getPrivateInformation().getMakeCompany()))
//                            builderInformation.append(getString(R.string.info_data_make_company) + magicalCamera.getPrivateInformation().getMakeCompany() + "\n");
//
//                        new MaterialDialog.Builder(activity)
//                                .title(getString(R.string.message_see_photo_information))
//                                .content(builderInformation.toString())
//                                .positiveText(getString(R.string.message_ok))
//                                .show();
//                    } else {
//                        CameraUtils.viewSnackBar(getString(R.string.error_not_have_data), principalLayout);
//                    }
//                }
//            }
//        });

//        btnGoTo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.finish();
//                activity.onBackPressed();
//            }
//        });

        return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //this is for rotate picture in this method
        //magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_180);

        if (resultCode == Activity.RESULT_OK) {
            //you should to call the method ever, for obtain the bitmap photo (= magicalCamera.getPhoto())
            magicalCamera.resultPhoto(requestCode, resultCode, data);

            if (CameraUtils.validateMagicalCameraNull(activity, principalLayout, magicalCamera)) {
//                floatingBtnMenu.setVisibility(View.VISIBLE);
                saveImage.setVisibility(View.VISIBLE);
                //set the photo in image view
                imageView.setImageBitmap(magicalCamera.getPhoto());

                //save the picture inmemory device, and return the physical path of this photo
                String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "myTestPhoto", MagicalCamera.JPEG, true);

//                if (path != null) {
//                    CameraUtils.viewSnackBar(getString(R.string.message_save_manual) + path, principalLayout);
//                } else {
//                    CameraUtils.viewSnackBar(getString(R.string.error_dont_save_photo), principalLayout);
//                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Map<String, Boolean> map = magicalPermissions.permissionResult(requestCode, permissions, grantResults);
        for (String permission : map.keySet()) {
            Log.d("PERMISSIONS", permission + " was: " + map.get(permission));
        }
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


    private void setUIComponents(View rootView) {
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        btntakephoto = (ImageButton) rootView.findViewById(R.id.btntakephoto);
        btnselectedphoto = (ImageButton) rootView.findViewById(R.id.btnselectedphoto);
//        btnGoTo = (Button) rootView.findViewById(R.id.btnGoTo);
//        texttitle = (TextView) rootView.findViewById(R.id.texttitle);
        saveImage = (ImageButton) rootView.findViewById(R.id.saveImage);
//        floatingBtnRotate = (FloatingActionButton) rootView.findViewById(R.id.floatingBtnRotate);
//        floatingBtnFacialRecognition = (FloatingActionButton) rootView.findViewById(R.id.floatingBtnFacialRecognition);
//        floatingBtnPhotoInformation = (FloatingActionButton) rootView.findViewById(R.id.floatingBtnPhotoInformation);
//        floatingBtnSeeString64 = (FloatingActionButton) rootView.findViewById(R.id.floatingBtnSeeString64);
//        frame = (FrameLayout) rootView.findViewById(R.id.frame);
//        principalLayout = rootView.findViewById(R.id.principalLayout);
//        floatingBtnMenu = (FloatingActionMenu) rootView.findViewById(R.id.floatingBtnMenu);


//        floatingBtnMenu.setVisibility(View.GONE);
        saveImage.setVisibility(View.GONE);
//        btnGoTo.setText(getString(R.string.go_to_activity));
//        texttitle.setText(getString(R.string.title_fragment));
        idTypeSpinner = (Spinner) rootView.findViewById(R.id.spinnerIdType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.id_type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        idTypeSpinner.setAdapter(adapter);

    }
}
