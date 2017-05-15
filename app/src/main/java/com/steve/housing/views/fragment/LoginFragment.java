package com.steve.housing.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.steve.housing.R;
import com.steve.housing.models.UserMDL;
import com.steve.housing.utils.Constants;
import com.steve.housing.utils.GenUtils;
import com.steve.housing.utils.VolleyRequests;
import com.steve.housing.views.activities.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getName();


    private VolleyRequests mVolleyRequest;
    private Realm mRealm;
    private SharedPreferences prefs;

    private TextInputLayout usernameTIL, passwordTIL;
    private TextInputEditText usernameET, passwordET;
    private Button loginBtn;
    private boolean usernameError, passwordError;
    private ProgressBar mProgressBar;
    private RealmAsyncTask realmAsyncTask;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mVolleyRequest = new VolleyRequests(getActivity());
        mRealm = Realm.getDefaultInstance();
        prefs = getActivity().getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);
        initField(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameError = GenUtils.isEmpty(usernameET, usernameTIL, "Enter your username");
                passwordError = GenUtils.isEmpty(passwordET, passwordTIL, "Enter your password");

                if (!(usernameError && passwordError)) {
                    Snackbar.make(v, "Login Error", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", usernameET.getText().toString().trim());
                    params.put("password", passwordET.getText().toString().trim());


                    mVolleyRequest.postData(Constants.LOGIN_URL, params, new VolleyRequests.VolleyPostCallBack() {

                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onSuccess(final JSONObject result) {
                            Log.d(TAG, result.toString());
                            try {
                                String user = result.getString("success");
                                String message = result.getString("message");
                                if (user.equals("true")) {

//                                    get data
                                    Map<String, String> params = new HashMap<String, String>();
                                    params.put("email", usernameET.getText().toString().trim());
                                    params.put("password", passwordET.getText().toString().trim());
                                    final UserMDL userMDL = new UserMDL();

                                    if (mRealm.where(UserMDL.class).findFirst().getId() != result.getJSONObject("agent").get("id").toString()) {


                                        try {

                                            userMDL.setId(result.getJSONObject("agent").get("id").toString());
                                            userMDL.setFirstName(result.getJSONObject("agent").get("firstname").toString());
                                            userMDL.setLastName(result.getJSONObject("agent").get("surname").toString());
                                            userMDL.setEmail(result.getJSONObject("agent").get("email").toString());
                                            userMDL.setDistrictMDL(result.getJSONObject("agent").get("district").toString());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    //
                                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            realm.copyToRealmOrUpdate(userMDL);

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
                                            Log.d("REal error", error.getMessage());

                                        }
                                    });
                                    setUserlogin();


//
//                                    realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
//                                        @Override
//                                        public void execute(Realm realm) {
//                                            String id = UUID.randomUUID().toString();
//
//                                            UserMDL userMDL = realm.createObject(UserMDL.class,id);
//                                            // personal data
//                                            try {
//
//                                                userMDL.setFirstName(result.getJSONObject("agent").get("firstname").toString());
////                                                userMDL.setwebId(result.getJSONObject("agent").get("id").toString());
//                                                userMDL.setLastName(result.getJSONObject("agent").get("lastname").toString());
//                                                userMDL.setEmail(result.getJSONObject("agent").get("email").toString());
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//
//                                        }
//                                    }, new Realm.Transaction.OnSuccess() {
//                                        @Override
//                                        public void onSuccess() {
//                                            Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                    }, new Realm.Transaction.OnError() {
//                                        @Override
//                                        public void onError(Throwable error) {
//                                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
//                                            Log.d("REal error", error.getMessage());
//
//                                        }
//                                    });


                                } else {
                                    msg("Username or password do not exist, try again");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onError(VolleyError error) {

                            Log.e("Shit", error.toString());
                            String message = null;
                            if (error instanceof NetworkError) {
                                message = "Cannot connect to Internet...Please check your connection!";
                            } else if (error instanceof ServerError) {
                                message = "The server could not be found. Please try again after some time!!";
                            } else if (error instanceof AuthFailureError) {
                                message = "Cannot connect to Internet...Please check your connection!";
                            } else if (error instanceof ParseError) {
                                message = "Parsing error! Please try again after some time!!";
                            } else if (error instanceof NoConnectionError) {
                                message = "Cannot connect to Internet...Please check your connection!";
                            } else if (error instanceof TimeoutError) {
                                message = "Connection TimeOut! Please check your internet connection.";

                            }
                            msg(message);
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onStart() {
                            mProgressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onFinish() {
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });


                }
            }
        });
    }

    public void initField(View view) {
        usernameET = (TextInputEditText) view.findViewById(R.id.username);
        passwordET = (TextInputEditText) view.findViewById(R.id.password);
        usernameTIL = (TextInputLayout) view.findViewById(R.id.usernameInputLayout);
        passwordTIL = (TextInputLayout) view.findViewById(R.id.passwordInputLayout);
        loginBtn = (Button) view.findViewById(R.id.loginBtn);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mProgressBar.setVisibility(View.INVISIBLE);

        usernameET.addTextChangedListener(new LoginFragment.MyTextWatcher(usernameET));
        passwordET.addTextChangedListener(new LoginFragment.MyTextWatcher(passwordET));

    }

    public void setUserlogin() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Constants.prefsLogin, true);
        editor.apply();

        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

    private void msg(String content) {

        try {
            new MaterialDialog.Builder(getContext())
                    .title("Sign In")
                    .content(content)
                    .positiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            // TODO

                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
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
                case R.id.username:
                    GenUtils.isEmpty(usernameET, usernameTIL, "Enter your username");
                    break;
                case R.id.password:
                    GenUtils.isEmpty(passwordET, passwordTIL, "Enter your password");
                    break;
                default:
            }
        }
    }
}
