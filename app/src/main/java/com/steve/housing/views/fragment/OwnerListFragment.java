package com.steve.housing.views.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.steve.housing.R;
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.models.PropertyMDL;
import com.steve.housing.views.adapters.OwnerListAdapter;
import com.steve.housing.views.adapters.PropertyListAdapter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class OwnerListFragment extends Fragment {
    private Realm realm;
    private RecyclerView recyclerView;
    private Menu menu;
    private OwnerListAdapter adapter;
    private RealmResults<OwnerMDL> ownerList;

    public OwnerListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_list, container, false);

        realm = Realm.getDefaultInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewOwner) ;
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {

        final RealmResults<OwnerMDL> results = realm.where(OwnerMDL.class).findAll();
        final List<OwnerMDL> ownerMDLList = results;

//        CategoryAdapter adapter = new CategoryAdapter(getActivity(),realm.where(PropertyMDL.class).findAllAsync(),true);
//        ryvItems.setAdapter(adapter);
        adapter = new OwnerListAdapter(realm.where(OwnerMDL.class).findAllAsync(),true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

//        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
//        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
//        touchHelper.attachToRecyclerView(recyclerView);
    }
}
