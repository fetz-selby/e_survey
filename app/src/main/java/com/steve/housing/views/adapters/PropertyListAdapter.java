package com.steve.housing.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.steve.housing.R;
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.models.PropertyMDL;
import com.steve.housing.utils.Constants;
import com.steve.housing.utils.VolleyRequests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by SOVAVY on 5/10/2017.
 */

public class PropertyListAdapter extends RealmRecyclerViewAdapter<PropertyMDL, PropertyListAdapter.PropertyListViewHolder> {

    public static final String TAG = "trial";
    private Context context;
    private Realm realm;
    private List<PropertyMDL> propertyModelList;
    private VolleyRequests mVolleyRequest;

    public PropertyListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<PropertyMDL> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
        this.context = context;

    }


    @Override
    public PropertyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.property_row, parent, false);
        mVolleyRequest = new VolleyRequests(parent.getContext());
        return new PropertyListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PropertyListViewHolder holder, int position) {
        final PropertyMDL propertyMDL = getData().get(position);
        holder.data = propertyMDL;
//        holder.data = categoryModel;
//        PropertyMDL propertyMDL = getItem(position);

//        holder.propertyId.setText(String.valueOf(propertyMDL.getId()));
        holder.propertyType.setText(String.valueOf(propertyMDL.getPropertyType()));
        holder.propertyClassification.setText(propertyMDL.getCreatedDate().toString());

        holder.propertyButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.data != null) {
                    JSONArray jsonArray = new JSONArray();

//                    jsonObject.put(propertyMDL.getOwnerList().toString())

                    for (OwnerMDL ownerMDL : propertyMDL.ownerList) {
                        try {

                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("firstname", ownerMDL.getFirstname());
                            jsonObject.put("lastname", ownerMDL.getLastname());
                            jsonArray.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    Log.d("Trial", "" + propertyMDL.getOwnerList().toString());

                    Log.d("Trial", jsonArray.toString() + " Size :" + jsonArray.length());


                    Map<String, String> params = new HashMap<>();

                    params.put("pins", propertyMDL.getPins());
                    params.put("propertyType", propertyMDL.getPropertyType());
                    params.put("classication", propertyMDL.getClassification());
                    params.put("address", propertyMDL.getAddress());
                    params.put("owners", jsonArray.toString());
                    params.put("electricitySource", propertyMDL.getElectricitySource());


                    Log.d(TAG, "Params" + params.toString());

                    mVolleyRequest.postData(Constants.PROPERTIES_URL, params, new VolleyRequests.VolleyPostCallBack() {

                        @Override
                        public void onSuccess(JSONObject result) throws JSONException {
//                            Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show();
                            Toast.makeText(context, result.get("message").toString(), Toast.LENGTH_LONG).show();
                            result.getJSONObject("property").get("_id");
                            Log.d(TAG, "Results" + result.toString());

                            holder.progressBar.setVisibility(View.INVISIBLE);
                            if (result.getString("success").equals("true")) {
                                holder.textViewUpdateStatus.setVisibility(View.VISIBLE);
//                                holder.textViewUpdateStatus.setTextColor()
                            }

                        }

                        @Override
                        public void onError(VolleyError error) {
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
                            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                            Log.d(TAG, error.toString());
                            holder.progressBar.setVisibility(View.INVISIBLE);

                        }

                        @Override
                        public void onStart() {
                            Toast.makeText(context, "starting", Toast.LENGTH_LONG).show();
                            holder.progressBar.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onFinish() {
                            holder.progressBar.setVisibility(View.INVISIBLE);


                        }
                    });

                }


            }
        });


    }

    public class PropertyListViewHolder extends RecyclerView.ViewHolder {
        public PropertyMDL data;
        TextView propertyId;
        TextView propertyClassification;
        TextView propertyType;
        Button propertyButtonSubmit;
        ProgressBar progressBar;
        TextView textViewUpdateStatus;


        public PropertyListViewHolder(View itemView) {
            super(itemView);
//            propertyCardview = (CardView) itemView.findViewById(R.id.property_items_row_image)

//            propertyId = (TextView) itemView.findViewById(R.id.property_items_row_text_top);
            propertyClassification = (TextView) itemView.findViewById(R.id.property_items_row_text_bottom);
            propertyType = (TextView) itemView.findViewById(R.id.property_items_row_text_top);
            propertyButtonSubmit = (Button) itemView.findViewById(R.id.buttonSubmit);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarSync);
            textViewUpdateStatus = (TextView) itemView.findViewById(R.id.textViewUpdateStatus);


        }
    }


//    @Override
//    public int getItemCount() {
//        return propertyModelList.size();
//    }


}
