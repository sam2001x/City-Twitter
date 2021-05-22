package com.scoopbook.cityUpdate.HelperClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.scoopbook.cityUpdate.Navigation.Facts;
import com.scoopbook.cityUpdate.Navigation.Foods;
import com.scoopbook.cityUpdate.Navigation.Lifestyle;
import com.scoopbook.cityUpdate.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

public class ViewPagerAdapter2 extends PagerAdapter {

    private final Context context;
    private LayoutInflater layoutInflater;
    private final Integer[] images = {R.drawable.boss, R.drawable.pooh, R.drawable.minion};
    private final String[] exploreHeadings = {"LIFESTYLE","FAMOUS FOOD","FACTS"};
    private final int[] backgroundColor = {Color.rgb(0, 115, 207), Color.rgb(255, 223, 0), Color.rgb(255, 255, 254),};

    public ViewPagerAdapter2(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.explore_item, null);
        MaterialCardView exploreCardView = view.findViewById(R.id.explore_itemCardView);
        MaterialTextView exploreHeading = view.findViewById(R.id.explore_itemHeading);
        MaterialTextView exploreHeading2 = view.findViewById(R.id.explore_itemHeading2);
        ImageView imageView = view.findViewById(R.id.explore_imageView);
        imageView.setImageResource(images[position]);
        exploreCardView.setCardBackgroundColor(backgroundColor[position]);
        exploreHeading.setText(exploreHeadings[position]);

        if (position == 0) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Lifestyle.class);
                    context.startActivity(intent);
                }
            });
        } else if (position == 1) {
            imageView.setBackgroundResource(0);
            exploreCardView.setStrokeColor(Color.rgb(0,255,255));
            exploreHeading.setTextSize(27);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Foods.class);
                    context.startActivity(intent);

                }
            });
        }else if (position==2){
            imageView.setBackgroundResource(0);
            exploreHeading2.setVisibility(View.VISIBLE);
            exploreHeading.setText("Do You");
            exploreHeading.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            exploreHeading2.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            exploreHeading2.setText("Know ?");
            exploreHeading.setTextColor(Color.rgb(0,0,0));
            exploreHeading2.setTextColor(Color.rgb(0,0,0));
            exploreCardView.setStrokeColor(Color.rgb(255,116,23));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Facts.class);
                            context.startActivity(intent);
                }
            });


        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}