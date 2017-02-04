package com.codenicely.edusmart.welcome_screen.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.image_loader.GlideImageLoader;
import com.codenicely.edusmart.helper.image_loader.ImageLoader;
import com.codenicely.edusmart.welcome_screen.model.data.WelcomePageDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramya on 10/10/16.
 */

public class WelcomeViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<WelcomePageDetails> welcomePageDetailsList = new ArrayList<>();
    private ImageLoader imageLoader;

    public WelcomeViewPagerAdapter(Context context) {
        this.context = context;
        imageLoader = new GlideImageLoader(context);

    }

    public void setImageList(List<WelcomePageDetails> welcomePageDetailsList) {
        this.welcomePageDetailsList = welcomePageDetailsList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.welcome_view_pager_item, container, false);
        container.addView(view);
        WelcomePageDetails welcomePageDetails = welcomePageDetailsList.get(position);
        TextView textView = (TextView) view.findViewById(R.id.tv1);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        ProgressBar imageProgressBar=(ProgressBar)view.findViewById(R.id.imageProgressBar);

        textView.setText(welcomePageDetails.getQuote());
        imageLoader.loadImage(welcomePageDetails.getImage(),imageView,imageProgressBar);
      
        return view;
    }


    @Override
    public int getCount() {
        return welcomePageDetailsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
