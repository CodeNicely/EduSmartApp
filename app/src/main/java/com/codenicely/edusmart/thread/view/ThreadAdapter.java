package com.codenicely.edusmart.thread.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.codenicely.edusmart.thread.model.data.ThreadData;
import com.codenicely.edusmart.thread.model.data.ThreadDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meghal on 4/2/17.
 */

public class ThreadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ThreadDetails> threadDataList = new ArrayList<>();
    private Context context;

    ThreadAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ThreadDetails> threadDetailsList) {

        this.threadDataList = threadDetailsList;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
