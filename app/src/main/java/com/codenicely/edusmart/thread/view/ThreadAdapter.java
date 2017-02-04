package com.codenicely.edusmart.thread.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.thread.model.data.ThreadData;
import com.codenicely.edusmart.thread.model.data.ThreadDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ThreadDetails> threadDataList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    ThreadAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setData(List<ThreadDetails> threadDetailsList) {

        this.threadDataList = threadDetailsList;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.thread_item, parent, false);

        return new ThreadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ThreadDetails threadDetails = threadDataList.get(position);
        ThreadViewHolder threadViewHolder = (ThreadViewHolder) holder;
        threadViewHolder.created.setText(threadDetails.getCreated());
        threadViewHolder.description.setText(threadDetails.getThread_description());
        threadViewHolder.title.setText(threadDetails.getThread_name());
        threadViewHolder.modified.setText(threadDetails.getThread_description());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ThreadViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.created)
        TextView created;

        @BindView(R.id.modified)
        TextView modified;

        public ThreadViewHolder(View view) {
            super(view);
            ButterKnife.bind(view);
        }
    }

}
