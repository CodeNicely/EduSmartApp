package com.codenicely.edusmart.thread.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.Keys;
import com.codenicely.edusmart.home.view.HomeActivity;
import com.codenicely.edusmart.message.view.MessageFragment;
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

    public void setData(List<ThreadDetails> threadDataList) {

        this.threadDataList = threadDataList;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.thread_item, parent, false);

        return new ThreadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ThreadDetails threadDetails = threadDataList.get(position);
        ThreadViewHolder threadViewHolder = (ThreadViewHolder) holder;
        threadViewHolder.created.setText(threadDetails.getCreated());
        threadViewHolder.description.setText(threadDetails.getDescription());
        threadViewHolder.title.setText(threadDetails.getTitle());
        threadViewHolder.author.setText(threadDetails.getAuthor());

        threadViewHolder.threadCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof HomeActivity) {

                    MessageFragment messageFragment = new MessageFragment();
                    Bundle args = new Bundle();
                    args.putInt(Keys.KEY_THREAD_ID, threadDataList.get(position).getThread_id());
                    messageFragment.setArguments(args);
                    ((HomeActivity) context).addFragment(messageFragment, "MessageFragment");

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return threadDataList.size();
    }

    public class ThreadViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.timestamp)
        TextView created;

        @BindView(R.id.author)
        TextView author;

        @BindView(R.id.card)
        CardView threadCard;

        public ThreadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
