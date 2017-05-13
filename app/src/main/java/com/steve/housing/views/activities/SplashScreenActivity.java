package com.steve.housing.views.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.steve.housing.R;
import com.steve.housing.utils.Constants;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences prefs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE);


        if(prefs.getBoolean(Constants.prefsLogin,Constants.prefBooleanDefault)){
            startActivity(new Intent(this,HomeActivity.class));
        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }

    }

}
