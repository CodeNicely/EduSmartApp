package com.codenicely.edusmart.details;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.edusmart.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.details_title)
    TextView details_title;
    @BindView(R.id.details_description)
    TextView details_description;
    @BindView(R.id.details_deadline)
    TextView details_deadline;
    @BindView(R.id.details_timestamp)
    TextView details_timestamp;
    @BindView(R.id.file_type_icon)
    ImageView file_type_icon;
    private String deadline;
    private String description;
    private String timestamp;
    private String title;
    private String file_url;
    private int file_type;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ItemDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemDetailsFragment newInstance(String param1, String param2) {
        ItemDetailsFragment fragment = new ItemDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_item_details_fragement, container, false);
        ButterKnife.bind(this,view);
        Button download_file=(Button) view.findViewById(R.id.file_download);
        download_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        deadline=getArguments().getString("deadline");
        description=getArguments().getString("description");
        timestamp=getArguments().getString("timestamp");
        file_url=getArguments().getString("file_url");
        title=getArguments().getString("title");
        file_type=getArguments().getInt("file_type");

         if(file_type==0)
        {
            file_type_icon.setImageResource(R.drawable.ic_add_a_photo_light_blue_700_24dp);
        }
        else if(file_type==1)
        {
            file_type_icon.setImageResource(R.drawable.ic_subject_purple_600_24dp);
        }
        else if(file_type==2)
        {
            file_type_icon.setImageResource(R.drawable.ic_menu_gallery);
        }
        else if(file_type==3)
        {
            file_type_icon.setImageResource(R.drawable.ic_menu_slideshow);
        }
        else
        {
            file_type_icon.setImageResource(R.drawable.ic_add_a_photo_light_blue_700_24dp);
        }
        return view;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
