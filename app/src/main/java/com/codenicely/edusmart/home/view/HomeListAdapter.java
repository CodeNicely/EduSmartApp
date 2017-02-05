package com.codenicely.edusmart.home.view;

import android.content.Context;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codenicely.edusmart.R;
import com.codenicely.edusmart.home.model.data.HomeListDataDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by ramya on 4/2/17.
 */

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeListDataDetails> homeListDataDetailsList = new ArrayList<>();
    private static final int CARD_TYPE_HEADING = 0;
    private static final int CARD_TYPE_NOTICE = 4;
    private static final int CARD_TYPE_ANNOUNCEMENTS = 3;
    private static final int CARD_TYPE_RESOURCES = 5;
    private static final int CARD_TYPE_ASSIGNMENTS = 2;
    private static final int CARD_TYPE_SUBJECT = 1;
    private static final int CARD_TYPE_SYLLABUS = 6;

    private Context context;
    private LayoutInflater layoutInflater;

    public HomeListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CARD_TYPE_NOTICE || viewType == CARD_TYPE_SYLLABUS) {
            View view = layoutInflater.inflate(R.layout.notice_item, parent, false);
            return new NoticeViewHolder(view);
        } else if (viewType == CARD_TYPE_ANNOUNCEMENTS) {
            View view = layoutInflater.inflate(R.layout.announcement_item, parent, false);
            return new AnnouncementsViewHolder(view);
        } else if (viewType == CARD_TYPE_RESOURCES) {
            View view = layoutInflater.inflate(R.layout.resources_item, parent, false);
            return new ResourcesViewHolder(view);
        } else if (viewType == CARD_TYPE_HEADING) {
            View view = layoutInflater.inflate(R.layout.heading_item, parent, false);
            return new HeadingViewHolder(view);
        } else if (viewType == CARD_TYPE_ASSIGNMENTS) {
            View view = layoutInflater.inflate(R.layout.assignments_item, parent, false);
            return new AssignmentsViewHolder(view);
        } else if (viewType == CARD_TYPE_SUBJECT) {
            View view = layoutInflater.inflate(R.layout.subject_item, parent, false);
            return new SubjectViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.empty_item, parent, false);
            return new EmptyViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_NOTICE ||
                homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_SYLLABUS) {
            return CARD_TYPE_NOTICE;
        } else if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_ANNOUNCEMENTS) {
            return CARD_TYPE_ANNOUNCEMENTS;
        } else if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_RESOURCES) {
            return CARD_TYPE_RESOURCES;
        } else if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_HEADING) {
            return CARD_TYPE_HEADING;
        } else if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_ASSIGNMENTS) {
            return CARD_TYPE_ASSIGNMENTS;
        } else if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_SUBJECT) {
            return CARD_TYPE_SUBJECT;
        } else {
            return -999;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeListDataDetails homeListDataDetails = homeListDataDetailsList.get(position);

        if (homeListDataDetails.getCard_type() == CARD_TYPE_HEADING) {
            HeadingViewHolder headingViewHolder = (HeadingViewHolder) holder;
            headingViewHolder.heading_title.setText(homeListDataDetails.getTitle());

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_RESOURCES) {
            ResourcesViewHolder resourcesViewHolder = (ResourcesViewHolder) holder;
            resourcesViewHolder.resource_title.setText(homeListDataDetails.getTitle());
            resourcesViewHolder.resource_description.setText(homeListDataDetails.getDescription());
            resourcesViewHolder.resource_author.setText(homeListDataDetails.getTeacher_name());
            resourcesViewHolder.resource_timestamp.setText(homeListDataDetails.getTimestamp());
            resourcesViewHolder.resource_logo.setImageResource(R.drawable.ic_subject_purple_600_24dp);

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_NOTICE || homeListDataDetails.getCard_type() == CARD_TYPE_SYLLABUS) {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;
            noticeViewHolder.notice_title.setText(homeListDataDetails.getTitle());
            noticeViewHolder.notice_description.setText(homeListDataDetails.getDescription());
            noticeViewHolder.notice_author.setText(homeListDataDetails.getTeacher_name());
            noticeViewHolder.notice_timestamp.setText(homeListDataDetails.getTimestamp());
            noticeViewHolder.notice_logo.setImageResource(R.drawable.ic_subject_purple_600_24dp);

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_ASSIGNMENTS) {
            AssignmentsViewHolder assignmentsViewHolder = (AssignmentsViewHolder) holder;
            assignmentsViewHolder.assignment_title.setText(homeListDataDetails.getTitle());
            assignmentsViewHolder.assignment_description.setText(homeListDataDetails.getDescription());
            assignmentsViewHolder.assignment_author.setText(homeListDataDetails.getTeacher_name());
            assignmentsViewHolder.assignment_timestamp.setText(homeListDataDetails.getTimestamp());
            assignmentsViewHolder.assignment_logo.setImageResource(R.drawable.ic_subject_purple_600_24dp);

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_SUBJECT) {
            SubjectViewHolder subjectViewHolder = (SubjectViewHolder) holder;
            subjectViewHolder.subject_title.setText(homeListDataDetails.getSubject_name());
            subjectViewHolder.subject_description.setText(homeListDataDetails.getDescription());
            subjectViewHolder.subject_author.setText(homeListDataDetails.getTeacher_name());
            subjectViewHolder.student_count.setText(homeListDataDetails.getCount());
            subjectViewHolder.subject_timestamp.setText(homeListDataDetails.getTimestamp());
            subjectViewHolder.subject_logo.setImageResource(R.drawable.ic_subject_purple_600_24dp);


        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_ANNOUNCEMENTS) {
            AnnouncementsViewHolder announcementsViewHolder = (AnnouncementsViewHolder) holder;
            announcementsViewHolder.announcement_title.setText(homeListDataDetails.getTitle());
            announcementsViewHolder.announcement_description.setText(homeListDataDetails.getDescription());
            announcementsViewHolder.announcing_author.setText(homeListDataDetails.getTeacher_name());
            announcementsViewHolder.announcement_timestamp.setText(homeListDataDetails.getTimestamp());
            announcementsViewHolder.announcement_logo.setImageResource(R.drawable.ic_subject_purple_600_24dp);

        } else {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "Items Received" + String.valueOf(homeListDataDetailsList.size()));
        return homeListDataDetailsList.size();

    }


    public void setHomeListDataDetailsList(List<HomeListDataDetails> homeListDataDetailsList) {
        this.homeListDataDetailsList = homeListDataDetailsList;
    }


    public class NoticeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notice_logo)
        ImageView notice_logo;
        @BindView(R.id.notice_title)
        TextView notice_title;
        @BindView(R.id.notice_author)
        TextView notice_author;
        @BindView(R.id.notice_description)
        TextView notice_description;
        @BindView(R.id.notice_timestamp)
        TextView notice_timestamp;

        public NoticeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class AnnouncementsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.announcement_logo)
        ImageView announcement_logo;
        @BindView(R.id.announcement_title)
        TextView announcement_title;
        @BindView(R.id.announcement_description)
        TextView announcement_description;
        @BindView(R.id.announcing_author)
        TextView announcing_author;
        @BindView(R.id.announcement_timestamp)
        TextView announcement_timestamp;

        public AnnouncementsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class ResourcesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.resource_logo)
        ImageView resource_logo;
        @BindView(R.id.resource_title)
        TextView resource_title;
        @BindView(R.id.resource_description)
        TextView resource_description;
        @BindView(R.id.resource_timestamp)
        TextView resource_timestamp;
        @BindView(R.id.resource_author)
        TextView resource_author;

        public ResourcesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class HeadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.heading_title)
        TextView heading_title;

        public HeadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class AssignmentsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.assignment_logo)
        ImageView assignment_logo;
        @BindView(R.id.assignmenta_title)
        TextView assignment_title;
        @BindView(R.id.assignment_description)
        TextView assignment_description;
        @BindView(R.id.assignment_timestamp)
        TextView assignment_timestamp;
        @BindView(R.id.assignment_author)
        TextView assignment_author;

        public AssignmentsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subject_logo)
        ImageView subject_logo;
        @BindView(R.id.subject_title)
        TextView subject_title;
        @BindView(R.id.subject_description)
        TextView subject_description;
        @BindView(R.id.faculty_name)
        TextView subject_author;
        @BindView(R.id.student_count)
        TextView student_count;
        @BindView(R.id.subject_timestamp)
        TextView subject_timestamp;

        public SubjectViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}

