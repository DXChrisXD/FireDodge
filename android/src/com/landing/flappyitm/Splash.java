package com.landing.flappyitm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.landing.flappyitm.states.MenuState;
import com.landing.flappyitm.states.PlayState;
import com.landing.flappyitm.utils.AppPreferences;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.GdxTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        Intent intent;

            intent = new Intent(this, AndroidLauncher.class);

        startActivity(intent);
        finish();


    }

}
