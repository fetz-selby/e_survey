package com.steve.housing.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.steve.housing.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by SOVAVY on 5/5/2017.
 */

public class VolleyUtils {

    public static JSONObject BlockingRequest(String url) {
        String fullUrl = ServerUtils.getFullUrl(url);
        String VOLLEY_GET_TAG = "volley_get_tag";

        final RequestFuture<JSONObject> futureRequest = RequestFuture.newFuture();
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, fullUrl, new JSONObject(), futureRequest, futureRequest);

        AppController.getInstance().getRequestQueue().add(jsonObjReq);

        try {
            return futureRequest.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static class CustomRequest extends Request<JSONObject> {
        private Response.Listener<JSONObject> listener;
        private Map<String, String> params;
        private Map<String, String> headers;

        public CustomRequest(String url, Map<String, String> params,
                             Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.listener = reponseListener;
            this.params = params;
        }

        public CustomRequest(int method, String url, Map<String, String> params,
                             Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener) {
            super(method, url, errorListener);
            this.listener = reponseListener;
            this.params = params;
        }

        protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            Log.d("CustomRequest", "PARAMS: " + params.toString());
            return params;
        }

        ;

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Log.d("CustomRequest", "HEADERS: " + headers.toString());
            return headers;
        }

        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONObject(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }

        @Override
        protected void deliverResponse(JSONObject response) {
            listener.onResponse(response);
        }
    }


    public interface VolleyTasksPostExecuteListener {
        void methodToCallBack();
    }


    public static class VolleyTasks {
        public int intTimeout = 20000;
        public JSONObject response;
        public Context context;
        public String TAG = VolleyTasks.class.getSimpleName();
        public int method = Request.Method.GET;
        public String url = "";
        public Map<String, String> params = new HashMap<String, String>();
        public Map<String, String> headers = new HashMap<String, String>();
        public ProgressDialog pDialog = null;
        public String strDialogLoadingMsg = Constants.loadingDialogMsg;
        public boolean blnShowDialog = true;
        public boolean blnShouldExecute = true;
        public boolean blnResponseValid = false;
        public boolean blnForceExecute = false;
        public VolleyTasksPostExecuteListener postExecuteListener;

        public VolleyTasks(Context context) {
            this.context = context;
        }

        public Map<String, String> getParams() {
            return this.params;
        }

        public Map<String, String> getHeaders() {
            return this.headers;
        }

        protected void preExecute() {
            if (this.blnShowDialog) {
                this.pDialog = new ProgressDialog(context);
                this.pDialog.setMessage(this.strDialogLoadingMsg);
                this.pDialog.show();
            }
        }

        public void vlyExecute() {
            if (URLUtil.isValidUrl(this.url)) {
                VolleyUtils.CustomRequest jsObjRequest = new VolleyUtils.CustomRequest(method, this.url,
                        this.getParams(), this.successListener(), this.errorListener());

                jsObjRequest.headers = this.getHeaders();

                jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                        this.intTimeout,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                AppController.getInstance().addToRequestQueue(jsObjRequest, Constants.VOLLEY_GET_TAG);
            }
        }

        public void execute() {
            this.preExecute();

            if (this.blnShouldExecute) {
                vlyExecute();
            } else {
                postExecute();
            }
        }

        protected void onSuccess(JSONObject response) {
            this.response = response;
        }

        protected void onError(NetworkResponse response) {
        }

        protected void postExecute() {

            if (this.blnShowDialog) {
                if (this.pDialog.isShowing())
                    this.pDialog.dismiss();
            }

            if (postExecuteListener != null)
                postExecuteListener.methodToCallBack();

        }

        // Listeners
        protected Response.Listener successListener() {

            return new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    onSuccess(response);
                    postExecute();
                }
            };

        }


        protected Response.ErrorListener errorListener() {

            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());

                    NetworkResponse response = error.networkResponse;
                    if (response != null && response.data != null) {
                        Log.e(TAG, "ErrorListener (" + response.statusCode + "): " + new String(response.data));
                        onError(response);
                    } else {
                        if (VolleyTasks.this.blnShowDialog) {
                            Toast.makeText(context, Constants.noNetworkMsg, Toast.LENGTH_LONG).show();
                        }
                    }

                    postExecute();

                }
            };

        }


    }


    public static class vlySaveFromServer extends VolleyUtils.VolleyTasks {
        Realm realm;

        public vlySaveFromServer(Context context, Realm realm) {
            super(context);
            this.realm = realm;
        }

        public RealmResults<?> getQueryset() {
            return null;
        }

        protected void preExecute() {
            super.preExecute();

            if (!blnForceExecute) {
                if (getQueryset().size() != 0)
                    this.blnShouldExecute = false;
            }

        }

        protected Response.Listener successListener() {

            return new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    onSuccess(response);

                    boolean reRun = false;
                    if (response.has("next")) {
                        try {
                            String strUrl = response.getString("next");
                            if (URLUtil.isValidUrl(strUrl))
                                reRun = true;
                            vlySaveFromServer.this.url = strUrl;
                            vlySaveFromServer.this.vlyExecute();
                        } catch (JSONException e) {
                            Log.e(TAG, "vlySaveFromServer(successListener): " + e.getMessage());
                        }
                    }

                    if (!reRun)
                        postExecute();
                }
            };

        }


    }


}
