package com.steve.housing.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.steve.housing.R;
import com.steve.housing.models.DistrictMDL;
import com.steve.housing.models.OwnerMDL;
import com.steve.housing.models.RegionMDL;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmSpinnerTrialActivity extends AppCompatActivity {

    Spinner spinnerOwners, spinnerRegions;
    AutoCompleteTextView autoCompleteTextView;
    Realm realm;
    ArrayList<String> regionsData;
    ArrayList<String> ownersData;
    ArrayList<String> districtsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_spinner_trial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewDistricts);
        districtsData = retrieveDistricts();
        ArrayAdapter adapterDistricts = new ArrayAdapter(this, android.R.layout.simple_list_item_1, districtsData);
        autoCompleteTextView.setAdapter(adapterDistricts);
        autoCompleteTextView.setThreshold(1);
        DistrictMDL districtMDL = realm.where(DistrictMDL.class).equalTo("district", autoCompleteTextView.getText().toString()).findFirst();
        spinnerRegions = (Spinner) findViewById(R.id.spinnerRegion);
        spinnerOwners = (Spinner) findViewById(R.id.spinnerOwner);
        ownersData = retrieveOwners();
        ArrayAdapter adapterOwner = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ownersData);
        spinnerOwners.setAdapter(adapterOwner);
        spinnerOwners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RealmSpinnerTrialActivity.this, "" + realm.where(RegionMDL.class).equalTo("region", regionsData.get(position)).findFirst(), Toast.LENGTH_LONG).show();
                OwnerMDL ownerMDL = realm.where(OwnerMDL.class).equalTo("id", ownersData.get(position)).findFirst();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        regionsData = retrieveRegions();


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, regionsData);
        spinnerRegions.setAdapter(adapter);
        spinnerRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RealmSpinnerTrialActivity.this, "" + realm.where(RegionMDL.class).equalTo("region", regionsData.get(position)).findFirst(), Toast.LENGTH_LONG).show();
                RegionMDL regionMDL = realm.where(RegionMDL.class).equalTo("region", regionsData.get(position)).findFirst();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //        sp= (Spinner) findViewById(R.id.sp);
//        //REALM CONFIGURATION
//        RealmConfiguration config=new
// RealmConfiguration.Builder(this).build();
//        realm=Realm.getInstance(config);
//        //retrieve
//        RealmHelper helper=new RealmHelper(realm);
//        spacecrafts=helper.retrieve();
//        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,spacecrafts);
//        sp.setAdapter(adapter);
//        http://camposha.info/source/android-realm-spinner-save-retrieve-fill-save/

    }

    public ArrayList<String> retrieveRegions() {
        ArrayList<String> regions = new ArrayList<>();

        RealmResults<RegionMDL> realmResults = realm.where(RegionMDL.class).findAll();


        for (RegionMDL regionMDL : realmResults) {
            regions.add(regionMDL.getRegion());
        }
        return regions;
    }


    public ArrayList<String> retrieveOwners() {
        ArrayList<String> regions = new ArrayList<>();

        RealmResults<OwnerMDL> realmResults = realm.where(OwnerMDL.class).findAll();


        for (OwnerMDL ownerMDL : realmResults) {
            regions.add(ownerMDL.getId());
        }
        return regions;
    }

    public ArrayList<String> retrieveDistricts() {
        ArrayList<String> regions = new ArrayList<>();

        RealmResults<DistrictMDL> realmResults = realm.where(DistrictMDL.class).findAll();


        for (DistrictMDL districtMDL : realmResults) {
            regions.add(districtMDL.getDistrict());
        }
        return regions;
    }


//    public ArrayList<String> retrieve()
//    {
//        ArrayList<String> spacecraftNames=new ArrayList<>();
//        RealmResults<Spacecraft> spacecrafts=realm.where(Spacecraft.class).findAll();
//        for(Spacecraft s: spacecrafts)
//        {
//            spacecraftNames.add(s.getName());
//        }
//        return spacecraftNames;
//    }

}
