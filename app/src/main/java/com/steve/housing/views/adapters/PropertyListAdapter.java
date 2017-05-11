package com.steve.housing.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.steve.housing.R;
import com.steve.housing.models.PropertyMDL;
import com.steve.housing.utils.Constants;
import com.steve.housing.utils.VolleyRequests;
import com.steve.housing.views.activities.ViewPagerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

import static java.security.AccessController.getContext;

/**
 * Created by SOVAVY on 5/10/2017.
 */

public class PropertyListAdapter extends RealmRecyclerViewAdapter<PropertyMDL, PropertyListAdapter.PropertyListViewHolder> {
    private Context context;
    private Realm realm;
    private List<PropertyMDL> propertyModelList;
    private VolleyRequests mVolleyRequest;
    private ProgressBar mProgressBar;
    public PropertyListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<PropertyMDL> data, boolean autoUpdate) {
        super(data, autoUpdate);

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
        holder.propertyClassification.setText(String.valueOf(propertyMDL.getClassification()));
        holder.propertyButtonAddOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent newOwner = new Intent(getContext(), ViewPagerActivity.class);
//                context.startActivity(newOwner);
            }
        });
        holder.propertyButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<String, String>();
//                pins	"33970"
//                propertyType	"Living quarters attached to office"
//                classification	"Residential"
//                address	"946 Hortense Rapid"
//                electricitySource	"Private generator"
//                __v	0
//                status	"A"
//                modifiedDate	"2017-05-10T19:51:55.465Z"
//                createdDate	"2017-05-10T19:51:55.465Z"
                params.put("pins", String.valueOf(propertyMDL.getPins()));
                params.put("propertyType", propertyMDL.getPropertyType());


                mVolleyRequest.postData(Constants.LOGIN_URL, params, new VolleyRequests.VolleyPostCallBack() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onSuccess(JSONObject result) {
                        Log.d("Trial", result.toString());
                        try {
                            String user = result.getString("success");
                            String message = result.getString("message");
                            if (user.equals("true")) {

////                                    get data
//                                Map<String, String> params = new HashMap<String, String>();
//                                params.put("email", usernameET.getText().toString().trim());
//                                params.put("password", passwordET.getText().toString().trim());
//                                setUserlogin();
                            } else {
//                                msg("Username or password do not exist, try again");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onError(VolleyError error) {
//                        msg(error.getMessage());
                        Log.e("Shit", error.toString());
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
        });

    }

    public class PropertyListViewHolder extends RecyclerView.ViewHolder {
        TextView propertyId;
        TextView propertyClassification;

       TextView propertyType;
        Button propertyButtonSubmit;
        Button propertyButtonAddOwner;
        public PropertyMDL data;




        public PropertyListViewHolder(View itemView){
        super(itemView);
//            propertyCardview = (CardView) itemView.findViewById(R.id.property_items_row_image)

//            propertyId = (TextView) itemView.findViewById(R.id.property_items_row_text_top);
            propertyClassification = (TextView) itemView.findViewById(R.id.property_items_row_text_bottom);
            propertyType = (TextView) itemView.findViewById(R.id.property_items_row_text_top);
            propertyButtonSubmit = (Button) itemView.findViewById(R.id.buttonSubmit);
            propertyButtonAddOwner = (Button) itemView.findViewById(R.id.owner_items_row_add);



        }
    }


//    @Override
//    public int getItemCount() {
//        return propertyModelList.size();
//    }


}
