package com.giri.fragments.ui.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

class   ImageAdapter extends PagerAdapter {
    Context context;
    int[] images;
    public ImageAdapter(Context context, int[] images) {
        this.context=context;
        this.images=images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((ImageView)object);
    }

    @NonNull
    @Override
    public ImageView instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView imageView= new ImageView(context);
        imageView.setImageDrawable(context.getDrawable(images[position]));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ((ViewPager) container).addView(imageView);

        return imageView;

    }
}
