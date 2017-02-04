package com.codenicely.edusmart.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.home.model.data.HomeListDataDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeListDataDetails> homeListDataDetailsList= new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    public HomeListAdapter(Context context)
    {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.home_list_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HomeListDataDetails homeListDataDetails=homeListDataDetailsList.get(position);
        HomeViewHolder homeViewHolder= (HomeViewHolder) holder;
        homeViewHolder.topic_title.setText(homeListDataDetails.getTopic_name());
        homeViewHolder.topic_description.setText(homeListDataDetails.getTopic_description());


    }

    @Override
    public int getItemCount() {
        return homeListDataDetailsList.size();

    }
    public List<HomeListDataDetails> getHomeListDataDetailsList() {
        return homeListDataDetailsList;
    }

    public void setHomeListDataDetailsList(List<HomeListDataDetails> homeListDataDetailsList) {
        this.homeListDataDetailsList = homeListDataDetailsList;
    }
    class HomeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topic_title)
        TextView topic_title;
        @BindView(R.id.topic_description)
        TextView topic_description;
        @BindView(R.id.student_count)
        int student_number;
        @BindView(R.id.faculty_name)
        TextView faculty_name;
        public HomeViewHolder(View itemView) {
            super(itemView);
        }
    }

}
