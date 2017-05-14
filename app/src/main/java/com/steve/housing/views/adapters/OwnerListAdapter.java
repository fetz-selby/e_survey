package com.steve.housing.views.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.steve.housing.R;
import com.steve.housing.models.OwnerMDL;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by root on 5/12/17.
 */

public class OwnerListAdapter extends RealmRecyclerViewAdapter<OwnerMDL, OwnerListAdapter.OwnerListViewHolder> {
    public OwnerListAdapter(Context context , @Nullable OrderedRealmCollection<OwnerMDL> data, boolean autoUpdate) {
        super(context,data, autoUpdate);
    }

    @Override
    public OwnerListAdapter.OwnerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.owner_row, parent, false);
        return new OwnerListAdapter.OwnerListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OwnerListAdapter.OwnerListViewHolder holder, int position) {
        final OwnerMDL ownerMDL = getData().get(position);
        holder.data = ownerMDL;
//
//        holder.propertyId.setText(String.valueOf(propertyMDL.getId()));
//        holder.propertyType.setText(String.valueOf(propertyMDL.getPropertyType()));
//        holder.propertyClassification.setText(String.valueOf(propertyMDL.getClassification()));
//        holder.propertyButtonAddOwner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        holder.ownerName.setText(ownerMDL.getLastname() + " " + ownerMDL.getLastname());
        holder.ownerPhone.setText(ownerMDL.getPhoneNumber());
        holder.ownerViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getClass(), OwnerDetails.class);
//                intent.putExtra("Key", ownerMDL.getId());
//                startActivity(intent);

            }
        });

    }

    public class OwnerListViewHolder extends RecyclerView.ViewHolder {
        public OwnerMDL data;
        TextView ownerName;
        TextView ownerPhone;
        Button ownerViewDetails;

        public OwnerListViewHolder(View itemView) {
            super(itemView);
            ownerName = (TextView) itemView.findViewById(R.id.owner_items_row_text_top);
            ownerPhone = (TextView) itemView.findViewById(R.id.owner_items_row_text_bottom);
            ownerViewDetails = (Button) itemView.findViewById(R.id.buttonViewOwnerDetail);
        }
    }
}
