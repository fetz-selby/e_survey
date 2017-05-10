package com.steve.housing.views.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.steve.housing.models.PropertyModel;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by SOVAVY on 5/10/2017.
 */

public class PropertyListAdapter extends RealmRecyclerViewAdapter<PropertyModel, PropertyListAdapter.PropertyListViewHolder> {
    private Context context;
    private Realm realm;
    private List<PropertyModel> propertyModelList;
    public PropertyListAdapter(@Nullable OrderedRealmCollection<PropertyModel> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public PropertyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PropertyListViewHolder holder, int position) {

    }

    public class PropertyListViewHolder extends RecyclerView.ViewHolder {
        public PropertyListViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
