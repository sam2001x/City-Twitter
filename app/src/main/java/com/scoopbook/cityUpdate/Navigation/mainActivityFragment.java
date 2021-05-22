package com.scoopbook.cityUpdate.Navigation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.scoopbook.cityUpdate.HelperClass.SliderItems;
import com.scoopbook.cityUpdate.HelperClass.VerticalViewPager;
import com.scoopbook.cityUpdate.HelperClass.ViewPagerAdapter;
import com.scoopbook.cityUpdate.R;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class
mainActivityFragment extends Fragment {

    List<SliderItems> sliderItems = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> newslinks = new ArrayList<>();
    VerticalViewPager verticalViewPager;
    DatabaseReference mRef;
    LottieAnimationView loading;
    TextView fetching;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        verticalViewPager = view.findViewById(R.id.verticalViewPager);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        loading = view.findViewById(R.id.loading);
        fetching = view.findViewById(R.id.fetching);

        String selectedCity = getArguments().getString("selectedCity");


        if (checkInternetConnection() == false) {
            
            MaterialTextView networkConnection = view.findViewById(R.id.internetConnection);
            loading.setVisibility(View.INVISIBLE);
            fetching.setVisibility(View.INVISIBLE);
            networkConnection.setVisibility(View.VISIBLE);

        } else {
            loading.setVisibility(View.VISIBLE);
            fetching.setVisibility(View.VISIBLE);

            mRef = FirebaseDatabase.getInstance().getReference(selectedCity);
            updateData();

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (checkInternetConnection() == true) {
                        updateData();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "No network Connection", Toast.LENGTH_LONG).show();
                    }

                }
            });

            verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    swipeRefreshLayout.setEnabled(position == 0);

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        return view;
    }

    public void updateData() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    //adding data to array List at first position always.
                    images.add(0, ds.child("imagelink").getValue(String.class));
                    titles.add(0, ds.child("title").getValue(String.class));
                    desc.add(0, ds.child("desc").getValue(String.class));
                    newslinks.add(0, ds.child("newslink").getValue(String.class));

                }
                for (int i = 0; i < images.size(); i++) {
                    //adding slider items with the images that are store in images list...
                    sliderItems.add(new SliderItems(images.get(i)));

                }
                loading.setVisibility(View.INVISIBLE);
                fetching.setVisibility(View.INVISIBLE);
                if (getView() != null) {
                    verticalViewPager.setAdapter(new ViewPagerAdapter(getActivity(),
                            sliderItems, titles, desc, images, newslinks, verticalViewPager));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        Boolean isNetworkConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isNetworkConnected == true;
    }

}