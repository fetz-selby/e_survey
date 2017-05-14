package com.steve.housing.views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.steve.housing.R;
import com.steve.housing.models.RegionMDL;
import com.steve.housing.models.UserMDL;
import com.steve.housing.utils.Constants;

import io.realm.Realm;

public class HomeActivity extends AppCompatActivity {
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        UserMDL userMDL = realm.where(UserMDL.class).findFirst();

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)

                .addProfiles(
                        new ProfileDrawerItem().withName(userMDL.getFirstName() + ""userMDL.getLastName()).withEmail(userMDL.getEmail()).withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

//Now create your drawer and pass the AccountHeader.Result
        new DrawerBuilder()
                .withAccountHeader(headerResult)
    .build();


    }


}
