package com.codenicely.edusmart.thread.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.SharedPrefs;
import com.codenicely.edusmart.home.view.HomeActivity;
import com.codenicely.edusmart.message.view.MessageFragment;
import com.codenicely.edusmart.thread.model.RetrofitThreadProvider;
import com.codenicely.edusmart.thread.model.data.ThreadData;
import com.codenicely.edusmart.thread.presenter.ThreadPresenter;
import com.codenicely.edusmart.thread.presenter.ThreadPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThreadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThreadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThreadFragment extends Fragment implements ThreadView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private int access_level;

    public ThreadFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.add_thread)
    FloatingActionButton add_thread;

    private ThreadAdapter threadAdapter;
    private ThreadPresenter threadPresenter;
    private SharedPrefs sharedPrefs;


    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThreadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThreadFragment newInstance(String param1, String param2) {
        ThreadFragment fragment = new ThreadFragment();
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
        View view = inflater.inflate(R.layout.fragment_thread, container, false);
        ButterKnife.bind(this, view);
        threadAdapter = new ThreadAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(threadAdapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Thread");
        progressDialog.setMessage("Please Wait. . .");

        add_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog = new AlertDialog.Builder(getContext()).create();
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                final RadioGroup access_groups;
                final EditText title;
                final EditText description;
                Button create_group;
                final View alertLayout = layoutInflater.inflate(R.layout.add_thread_layout, new LinearLayout(getContext()));
                alertDialog.setView(alertLayout);
                alertDialog.setCancelable(true);
                access_groups = (RadioGroup) alertLayout.findViewById(R.id.radio_group);
                title = (EditText) alertLayout.findViewById(R.id.title);
                description = (EditText) alertLayout.findViewById(R.id.description);
                create_group = (Button) alertLayout.findViewById(R.id.create);
                final RadioButton radioButton1 = (RadioButton) alertLayout.findViewById(R.id.access_level1);
                RadioButton radioButton2 = (RadioButton) alertLayout.findViewById(R.id.access_level2);
                RadioButton radioButton3 = (RadioButton) alertLayout.findViewById(R.id.access_level3);

                access_groups.check(R.id.access_level1);

                alertDialog.show();
                access_groups.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.access_level1:
                                access_level = 0;
                                break;
                            case R.id.access_level2:
                                access_level = 1;
                                break;
                            case R.id.access_level3:
                                access_level = 2;
                                break;
                            default:
                                break;
                        }
                    }
                });
                create_group.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        threadPresenter.createThread(sharedPrefs.getAccessToken(),
                                title.getText().toString(),
                                description.getText().toString(), access_level);

                    }
                });
            }
        });

        threadPresenter = new ThreadPresenterImpl(this, new RetrofitThreadProvider());
        sharedPrefs = new SharedPrefs(getContext());
        threadPresenter.requestThreads(sharedPrefs.getAccessToken());

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoader(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showDialog(boolean show) {

        if (show) {
            progressDialog.show();
        } else {
            alertDialog.dismiss();
            progressDialog.dismiss();

        }

    }

    @Override
    public void setData(ThreadData threadData) {

        threadAdapter.setData(threadData.getData_list());
        threadAdapter.notifyDataSetChanged();

    }

    @Override
    public void reloadThreads() {
        threadPresenter.requestThreads(sharedPrefs.getAccessToken());
    }

    public void openThread(int thread_id) {

        ((HomeActivity) getActivity()).setFragment(new MessageFragment(),"MessageFragment");

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
