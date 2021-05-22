package com.scoopbook.cityUpdate;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.scoopbook.cityUpdate.Navigation.CoronaFragment;
import com.scoopbook.cityUpdate.Navigation.mainActivityFragment;
import com.scoopbook.cityUpdate.Navigation.menuFragment;

public class MainActivity extends AppCompatActivity {
    private int currentApiVersion;
    ChipNavigationBar chipNavigationBar;
    String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        selectedCity = sharedPreferences.getString("selectedCity", "");

        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }

//-----------------------------Testing Purpose only--------------------------------------------------------------------------------
        Toast.makeText(this, selectedCity, Toast.LENGTH_SHORT).show();

        //------------------------------------------------------------------------------------------------------

        chipNavigationBar = findViewById(R.id.bottomNavBar);
        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container1, createMainActivityFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.home:
                        fragment = createMainActivityFragment();

                        break;
                    case R.id.menu:
                        fragment = new menuFragment();
                        break;

                    case R.id.corona:
                        fragment = new CoronaFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, fragment).commit();
            }
        });

    }

    mainActivityFragment createMainActivityFragment() {
        mainActivityFragment fragment = new mainActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putString("selectedCity", selectedCity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}

