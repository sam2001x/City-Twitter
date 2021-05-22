package com.scoopbook.cityUpdate.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.scoopbook.cityUpdate.MainActivity;
import com.scoopbook.cityUpdate.R;
import com.google.android.material.textview.MaterialTextView;

public class SplashScreen extends AppCompatActivity {

    public static int splash_timer = 3000;
    ImageView BgImage;
    LottieAnimationView lottieAnimationView;
    SharedPreferences onBoardingScreen;
    MaterialTextView heading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_splash_screen);
        BgImage = findViewById(R.id.bg_Image);
        heading = findViewById(R.id.splashscreen_heading);
        lottieAnimationView = findViewById(R.id.lottie);
        MaterialTextView versionName = findViewById(R.id.powerdByLine);

        TextPaint paint = heading.getPaint();
        float width = paint.measureText("City Update");

        Shader textShader = new LinearGradient(0, 0, width, heading.getTextSize(),
                new int[]{Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),},
                null, Shader.TileMode.CLAMP);
        heading.getPaint().setShader(textShader);


        lottieAnimationView.animate().translationY(1700).setDuration(1000).setStartDelay(2000);
        heading.animate().translationX(1700).setDuration(1000).setStartDelay(2000);
        versionName.animate().translationX(1700).setDuration(1000).setStartDelay(2000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("OnBoardingScreen", MODE_PRIVATE);
                SharedPreferences chooseCityScreen = getSharedPreferences("myPref", MODE_PRIVATE);
                boolean chooseingCity = chooseCityScreen.getBoolean("choosedCity", false);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);
                Intent intent;

                if (isFirstTime) {
                    intent = new Intent(getApplicationContext(), OnBoarding.class);
                } else {

                    if (chooseingCity) {
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                    } else
                        intent = new Intent(getApplicationContext(), ChooseCity.class);
                }
                startActivity(intent);
                finish();

            }
        }, splash_timer);


    }


}

