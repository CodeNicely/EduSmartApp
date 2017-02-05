package com.codenicely.edusmart.message.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.image_loader.GlideImageLoader;
import com.codenicely.edusmart.helper.image_loader.ImageLoader;
import com.codenicely.edusmart.message.model.data.MessageData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by meghal on 3/2/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_OWNER = 1;
    private static final int VIEW_TYPE_OTHER = 2;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<MessageData> messageDataList = new ArrayList<>();
    private ImageLoader imageLoader;

    MessageAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        imageLoader=new GlideImageLoader(context);

    }

    void updateList(List<MessageData> messageDataList) {

/*
        messageDataList.addAll(this.messageDataList);
        this.messageDataList = messageDataList;
*/

        this.messageDataList.addAll(messageDataList);
//        this.messageDataList=messageDataList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_OWNER) {
            View ownerView = layoutInflater.inflate(R.layout.message_owner_item, parent, false);
            return new OwnerViewHolder(ownerView);
        } else {
            View otherView = layoutInflater.inflate(R.layout.message_others_item, parent, false);
            return new OtherViewHolder(otherView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageData messageData = messageDataList.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_OWNER) {
            OwnerViewHolder ownerViewHolder = (OwnerViewHolder) holder;
            ownerViewHolder.message.setText(messageData.getBody());
            ownerViewHolder.timestamp.setText(messageData.getCreated());
            imageLoader.loadImage(messageData.getImage(),ownerViewHolder.imageView,new ProgressBar(context));


        } else {
            OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
            otherViewHolder.message.setText(messageData.getBody());
            otherViewHolder.timestamp.setText(messageData.getCreated());
            otherViewHolder.senderName.setText(messageData.getUser_id());
            imageLoader.loadImage(messageData.getImage(),otherViewHolder.imageView,new ProgressBar(context));

        }

    }

    @Override
    public int getItemViewType(int position) {

        if (messageDataList.get(position).isOwner()) {
            return VIEW_TYPE_OWNER;
        } else {
            return VIEW_TYPE_OTHER;
        }


    }

    @Override
    public int getItemCount() {

        if (messageDataList.size() == 0) {
            return 0;
        } else {
            return messageDataList.size();
        }
    }

    public int getLastMessageId() {

        if (messageDataList.size() == 0) {
            return 0;
        } else {

            return messageDataList.get(messageDataList.size() - 1).getMessage_id();
        }
    }

    public class OwnerViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.message)
        TextView message;

        @BindView(R.id.timestamp)
        TextView timestamp;

        @BindView(R.id.image)
        ImageView imageView;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    public class OtherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message)
        TextView message;

        @BindView(R.id.timestamp)
        TextView timestamp;

        @BindView(R.id.sender_name)
        TextView senderName;

        @BindView(R.id.image)
        ImageView imageView;

        public OtherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
