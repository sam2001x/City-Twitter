package com.scoopbook.cityUpdate.HelperClass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

import com.scoopbook.cityUpdate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    FloatingActionButton floatingActionButton;
    LottieAnimationView lottieAnimationView2;


    public SliderAdapter(Context context) {
        this.context = context;
    }

    int[] images = {
           R.raw.news,
           R.raw.covid,
           R.raw.letsstart
    };
    int[] headings = {
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
    };
    int[] descriptions = {
            R.string.first_slide_disc,
            R.string.second_slide_disc,
            R.string.third_slide_disc,
    };
    int[] backgroundColor ={
            Color.rgb(255,255,255),
            Color.rgb(186,83,222),
            Color.rgb(244,105,169),

    };
    int[] dots ={
            R.drawable.tab1,
            R.drawable.tab2,
            R.drawable.tab3,

    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);

        lottieAnimationView2 = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView description = view.findViewById(R.id.slider_disc);
        ImageView sliderDots = view.findViewById(R.id.slider_dots);
        ConstraintLayout constraintLayout = view.findViewById(R.id.slider_layout);
        floatingActionButton = view.findViewById(R.id.slider_floatingBtn);

//        imageView.setImageResource(images[position]);
        lottieAnimationView2.setAnimation(images[position]);
        heading.setText(headings[position]);
        description.setText(descriptions[position]);
        constraintLayout.setBackgroundColor(backgroundColor[position]);
        sliderDots.setImageResource(dots[position]);

        if(position != 2){
            floatingActionButton.setVisibility(View.INVISIBLE);
        }else {
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
