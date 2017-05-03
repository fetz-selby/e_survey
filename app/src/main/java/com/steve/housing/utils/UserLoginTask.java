//package com.steve.housing.utils;
//
//import android.os.AsyncTask;
//
//import com.steve.housing.R;
//
//import java.io.IOException;
//
//import retrofit2.Retrofit;
//
///**
// * Created by SOVAVY on 5/2/2017.
// */
//
//public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mEmail;
//        private final String mPassword;
//
//        UserLoginTask(String email, String password) {
//            mEmail = email;
//            mPassword = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            Retrofit restAdapter = new Retrofit.Builder()
//                    .baseUrl(Constants.ROOT_API_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            IConstructSecureAPI service = restAdapter.create(IConstructSecureAPI.class);
//            Call<JsonElement> result = service.getToken(mEmail, mPassword, "password");
//            try {
//                //using sync request and get response
//                JsonElement element = result.execute().body();
//                if(element!=null) {
//                    //do whatever you want with your response
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//            showProgress(false);
//
//            if (success) {
//                if(clientType != 2){
//                    mPasswordView.setError(getString(R.string.error_incorrect_password));
//                    mPasswordView.requestFocus();
//                    clientType = 0;
//                }
//                else {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivityForResult(intent, 1);
//                }
////                finish();
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
//        }
//
//    }
//}}
