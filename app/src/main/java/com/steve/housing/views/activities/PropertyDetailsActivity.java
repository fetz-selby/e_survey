package com.steve.housing.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.steve.housing.R;

public class PropertyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        sp= (Spinner) findViewById(R.id.sp);
//        //REALM CONFIGURATION
//        RealmConfiguration config=new RealmConfiguration.Builder(this).build();
//        realm=Realm.getInstance(config);
//        //retrieve
//        RealmHelper helper=new RealmHelper(realm);
//        spacecrafts=helper.retrieve();
//        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,spacecrafts);
//        sp.setAdapter(adapter);
    }

}
