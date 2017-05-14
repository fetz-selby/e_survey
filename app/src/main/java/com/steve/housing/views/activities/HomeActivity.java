package com.steve.housing.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.steve.housing.R;
import com.steve.housing.models.UserMDL;

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
//        UserMDL userMDL = realm.where(UserMDL.class).findFirst();
//        Log.d("Track", userMDL.toString());

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)

                .addProfiles(
                        new ProfileDrawerItem().withName("mklmlmlm" )
                                .withEmail("dfsdfs@gmail.com")
                                .withIcon(getResources().getDrawable(R.drawable.profile))
                                .withTextColor(getResources().getColor(R.color.md_blue_200))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        profile.getEmail();
                        profile.getName();
                        return true;
                    }
                })
                .build();

//Now create your drawer and pass the AccountHeader.Result
        new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
//                .addDrawerItems(
//                        item1,
//                        new DividerDrawerItem(),
//                        item2,
//                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings)
//                )
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//                        // do something with the clicked item :D
//                    }
//                })
                .build();


    }


}
