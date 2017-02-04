package com.codenicely.edusmart.home.view;

import android.content.Context;
import android.support.v4.app.NotificationCompatSideChannelService;
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

    private List<HomeListDataDetails> homeListDataDetailsList = new ArrayList<>();
    private static final int CARD_TYPE_HEADING = 0;
    private static final int CARD_TYPE_NOTICE = 1;
    private static final int CARD_TYPE_ANNOUNCEMENTS = 2;
    private static final int CARD_TYPE_RESOURCES = 3;
    private static final int CARD_TYPE_ASSIGNMENTS = 4;
    private static final int CARD_TYPE_SUBJECT = 5;
    private static final int CARD_TYPE_COURSE = 6;

    private Context context;
    private LayoutInflater layoutInflater;

    public HomeListAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CARD_TYPE_NOTICE) {
            View view = layoutInflater.inflate(R.layout.notice_item, parent, false);
            return new NoticeViewHolder(view);
        } else if (viewType == CARD_TYPE_ANNOUNCEMENTS) {
            View view = layoutInflater.inflate(R.layout.notice_item, parent, false);
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
        } else if (viewType == CARD_TYPE_COURSE) {
            View view = layoutInflater.inflate(R.layout.subject_item, parent, false);
            return new SubjectViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.empty_item, parent, false);
            return new EmptyViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_NOTICE) {
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
        } else if (homeListDataDetailsList.get(position).getCard_type() == CARD_TYPE_COURSE) {
            return CARD_TYPE_COURSE;
        } else {
            return -999;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeListDataDetails homeListDataDetails = homeListDataDetailsList.get(position);
        HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
        homeViewHolder.topic_title.setText(homeListDataDetails.getTitle());
        homeViewHolder.topic_description.setText(homeListDataDetails.getDescription());
        homeListDataDetails = homeListDataDetailsList.get(position);

        if (homeListDataDetails.getCard_type() == CARD_TYPE_HEADING) {
            HeadingViewHolder headingViewHolder = (HeadingViewHolder) holder;

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_RESOURCES) {
            ResourcesViewHolder resourcesViewHolder = (ResourcesViewHolder) holder;

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_NOTICE) {
            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_ASSIGNMENTS) {
            AssignmentsViewHolder assignmentsViewHolder = (AssignmentsViewHolder) holder;

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_SUBJECT) {
            SubjectViewHolder subjectViewHolder = (SubjectViewHolder) holder;

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_ANNOUNCEMENTS) {

            AnnouncementsViewHolder announcementsViewHolder = (AnnouncementsViewHolder) holder;

        } else if (homeListDataDetails.getCard_type() == CARD_TYPE_ANNOUNCEMENTS) {
            CourseViewHolder courseViewHolder = (CourseViewHolder) holder;

        } else {
            HeadingViewHolder headingViewHolder = (HeadingViewHolder) holder;

        }
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

        @BindView(R.id.description)
        TextView topic_description;

        @BindView(R.id.faculty_name)
        TextView faculty_name;

        public HomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class NoticeViewHolder extends RecyclerView.ViewHolder {
        public NoticeViewHolder(View view) {
            super(view);
        }
    }

    private class AnnouncementsViewHolder extends RecyclerView.ViewHolder {
        public AnnouncementsViewHolder(View view) {
            super(view);
        }
    }

    private class ResourcesViewHolder extends RecyclerView.ViewHolder {
        public ResourcesViewHolder(View view) {
            super(view);
        }
    }

    private class HeadingViewHolder extends RecyclerView.ViewHolder {
        public HeadingViewHolder(View view) {
            super(view);
        }
    }

    private class AssignmentsViewHolder extends RecyclerView.ViewHolder {
        public AssignmentsViewHolder(View view) {
            super(view);
        }
    }

    private class SubjectViewHolder extends RecyclerView.ViewHolder {
        public SubjectViewHolder(View view) {
            super(view);
        }
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }

    private class CourseViewHolder extends RecyclerView.ViewHolder {
        public CourseViewHolder(View view) {
            super(view);
        }
    }
}

