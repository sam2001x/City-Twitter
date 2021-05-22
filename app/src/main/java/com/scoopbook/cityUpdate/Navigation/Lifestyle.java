package com.scoopbook.cityUpdate.Navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.scoopbook.cityUpdate.HelperClass.SliderItems;
import com.scoopbook.cityUpdate.HelperClass.VerticalViewPager;
import com.scoopbook.cityUpdate.HelperClass.ViewPagerAdapter;
import com.scoopbook.cityUpdate.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Lifestyle extends AppCompatActivity {
    List<SliderItems> sliderItems = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> newslink = new ArrayList<>();

    VerticalViewPager verticalViewPager;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_lifestyle);
        verticalViewPager = findViewById(R.id.lifestyleViewPager);


        mRef = FirebaseDatabase.getInstance().getReference("Lifestyle");
        updateData();

    }

    public void updateData() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    //adding data to array List at first position always.
                    images.add(ds.child("imagelink").getValue(String.class));
                    titles.add(ds.child("title").getValue(String.class));
                    desc.add(ds.child("desc").getValue(String.class));
                }
                for (int i = 0; i < images.size(); i++) {
                    //adding slider items with the images that are store in images list...
                    sliderItems.add(new SliderItems(images.get(i)));

                }

                verticalViewPager.setAdapter(new ViewPagerAdapter(Lifestyle.this,
                        sliderItems, titles, desc, images, newslink, verticalViewPager));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}