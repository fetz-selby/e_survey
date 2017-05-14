package com.steve.housing.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;

import com.steve.housing.R;
import com.steve.housing.models.PropertyMDL;
import com.steve.housing.views.adapters.PropertyListAdapter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class PropertyListActivity extends AppCompatActivity {
    private Realm realm;
    private RecyclerView recyclerView;
    private Menu menu;
    private PropertyListAdapter adapter;
    private RealmResults<PropertyMDL> propertyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


                Intent i = new Intent(getApplication(),PropertyViewPagerActivity.class);
                startActivity(i);
            }
        });

        realm = Realm.getDefaultInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewProperty);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        final RealmResults<PropertyMDL> results = realm.where(PropertyMDL.class).findAllSorted("createdDate");
        final List<PropertyMDL> categoryList = results;

//        CategoryAdapter adapter = new CategoryAdapter(getActivity(),realm.where(PropertyMDL.class).findAllAsync(),true);
//        ryvItems.setAdapter(adapter);
        adapter = new  PropertyListAdapter(this, realm.where(PropertyMDL.class).findAllAsync(),true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

//        TouchHelperCallback touchHelperCallback = new TouchHelperCallback();
//        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
//        touchHelper.attachToRecyclerView(recyclerView);
    }

}
