package com.scoopbook.cityUpdate.Common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.scoopbook.cityUpdate.MainActivity;
import com.scoopbook.cityUpdate.R;

public class ChooseCity extends AppCompatActivity {
    ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        String[] cities = {"All","News","Ahmedabad", "Ajmer", "Akola", "Aligarh", "Allahabad", "Amravati",
                "Amritsar", "Asansol", "Aurangabad", "Bareilly", "Belgaum", "Bengaluru",
                "Bhatpara", "Bhavnagar", "Bhayandar", "Bhilai", "Bhiwandi", "Bhopal",
                "Bhubaneshwar", "Bikaner", "Bilimora", "Borivli", "Chandigarh", "Chennai",
                "Cochin", "Coimbatore", "Cuttack", "Davangere", "Dehra Dun", "Delhi",
                "Dombivli", "Durgapur", "Faridabad", "Gaya", "Ghaziabad", "Gorakhpur", "Gulbarga",
                "Guntur", "Guwahati", "Gwalior", "Haora", "Hubli", "Hyderabad", "Indore", "Jabalpur",
                "Jaipur", "Jalandhar", "Jalgaon", "Jammu", "Jamnagar", "Jamshedpur", "Jodhpur", "Kalyan",
                "Kanpur", "Karol Bagh", "Kolhapur", "Kolkata", "Kota", "Kozhikode", "Kurnool", "Lucknow",
                "Ludhiana", "Madurai", "Malegaon", "Meerut", "Moradabad", "Mumbai", "Mysore", "Nagpur",
                "Nanded", "Nangi", "Nashik", "Nowrangapur", "Patna", "Pimpri", "Pune", "Raipur", "Rajkot",
                "Ramgundam", "Ranchi", "Raurkela", "Saharanpur", "Salem", "Sangli", "Shiliguri",
                "Shivaji Nagar", "Shyamnagar", "Solapur", "Shrinagar", "Surat", "Teni", "Thane",
                "Thiruvananthapuram", "Tiruchirappalli", "TirunelveliAgra", "Udaipur", "Ujjain",
                "Ulhasnagar", "Vadodara", "Varanasi", "Vijayawada", "Visakhapatnam", "Warangal"};

        chipGroup = findViewById(R.id.chipGroup);
        chipGroup.setSingleSelection(true);
        for (String city : cities) {
            Chip chip = new Chip(this);
            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(this, null, 0,
                    R.style.Widget_MaterialComponents_Chip_Choice);
            chip.setChipDrawable(chipDrawable);
            chip.setText(city);
            chipGroup.addView(chip);
        }

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip = chipGroup.findViewById(checkedId);
                if(chip != null) {

                    SharedPreferences preferences = getSharedPreferences("myPref",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("selectedCity",chip.getText().toString());
                    editor.putBoolean("choosedCity",true);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }
}