package com.steve.housing.views.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.steve.housing.R;
import com.steve.housing.models.DistrictMDL;
import com.steve.housing.models.UserMDL;
import com.steve.housing.utils.Constants;

import io.realm.Realm;
import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {
    SharedPreferences prefs;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        RealmResults<UserMDL> userMDLs = realm.where(UserMDL.class).findAll();
        Log.d("Track", userMDLs.toString());
        UserMDL userMDL = realm.where(UserMDL.class).findFirst();
        DistrictMDL districtMDL = realm.where(DistrictMDL.class).equalTo("id",userMDL.getDistrictMDL()).findFirst();
        String fullName = userMDL.getFirstName() + " " + userMDL.getLastName();
        String email = userMDL.getEmail();
        String district = districtMDL.getDistrict();

        // Create the AccountHeader


        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);


        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.logout);
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(getResources().getDrawable(R.drawable.splash))

//                .addProfiles(
//                        new ProfileDrawerItem().withName("mklmlmlm")
//                                .withEmail("dfsdfs@gmail.com")
//                                .withIcon(getResources().getDrawable(R.drawable.profile))
//                                .withTextColor(getResources().getColor(R.color.md_blue_200))
//                )
                .addProfiles(
                        new ProfileDrawerItem().withName(fullName)
                                .withEmail(district)
                                .withIcon(getResources()
                                        .getDrawable(R.drawable.profile))

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
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem()

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                signOut();
//                            } else if (drawerItem.getIdentifier() == 2) {
//                                intent = new Intent(DrawerActivity.this, ActionBarActivity.class);
//                            }
//                            else if (drawerItem.getIdentifier() == 20) {
//                                intent = new LibsBuilder()
//                                        .withFields(R.string.class.getFields())
//                                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
//                                        .intent(DrawerActivity.this);
//                            }
                                if (intent != null) {
                                    HomeActivity.this.startActivity(intent);
                                }
                            }

                            return false;
                        }

                        return true;
                    }
                })
                .build();
//

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater Inflater = getMenuInflater();
//        Inflater.inflate(R.menu.menu_home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    private void signOut() {
        final Context context = HomeActivity.this;
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        dialog.setTitle("Confirm Logout");
        dialog.setMessage("Are you sure you want to logout, you will not be able to enter data unless you login again.");

        dialog.setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                prefs.edit().clear().commit();

                                Toast.makeText(getApplicationContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);

                                return;
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                return;
                            }
                        });
        AlertDialog alert = dialog.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(HomeActivity.this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                return;
            }
        }).show();


    }

}
