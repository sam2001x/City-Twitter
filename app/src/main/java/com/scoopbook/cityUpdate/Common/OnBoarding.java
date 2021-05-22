package com.scoopbook.cityUpdate.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.scoopbook.cityUpdate.HelperClass.SliderAdapter;
import com.scoopbook.cityUpdate.R;


public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

    }
    public void callFloatingBtnClicked(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("OnBoardingScreen", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstTime", false);
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), ChooseCity.class);
        startActivity(intent);
        finish();
    }

}



