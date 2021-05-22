package com.scoopbook.cityUpdate.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.scoopbook.cityUpdate.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CoronaFragment extends Fragment {
    DatabaseReference mRef;
    MaterialTextView totalCases;
    MaterialTextView recoveredCases;
    MaterialTextView activeCases;
    MaterialTextView deathCases;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_corona, container, false);

        RelativeLayout relativeLayout1 = view.findViewById(R.id.relativeLayout_1);
        MaterialCardView materialCardView = view.findViewById(R.id.card_view1);
        totalCases = view.findViewById(R.id.corona_totalCases);
        recoveredCases = view.findViewById(R.id.corona_recovered);
        activeCases = view.findViewById(R.id.corona_activeCases);
        deathCases = view.findViewById(R.id.corona_deaths);
        Spinner spinner = view.findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(getActivity(),
                        R.array.placesInSpinner,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    mRef = FirebaseDatabase.getInstance().getReference("World");
                    updateCurrentData();
                }
                else if (i == 1) {

                    mRef = FirebaseDatabase.getInstance().getReference("India");
                    updateCurrentData();

                } else if (i == 2) {
                    mRef = FirebaseDatabase.getInstance().getReference("Rajasthan");
                    updateCurrentData();

                } else {

                    mRef = FirebaseDatabase.getInstance().getReference("Udaipur");
                    updateCurrentData();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        materialCardView.setBackgroundResource(R.drawable.cardview_shape);
        relativeLayout1.setBackgroundResource(R.drawable.cardview_shape2);


        return view;

    }

    public void updateCurrentData() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    totalCases.setText(ds.child("totalCases").getValue(String.class));
                    recoveredCases.setText(ds.child("recovered").getValue(String.class));
                    activeCases.setText(ds.child("active").getValue(String.class));
                    deathCases.setText(ds.child("deaths").getValue(String.class));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}