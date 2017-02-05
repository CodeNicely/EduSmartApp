package com.codenicely.edusmart.message.view;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codenicely.edusmart.R;
import com.codenicely.edusmart.helper.MyApplication;
import com.codenicely.edusmart.helper.MyFirebaseService;
import com.codenicely.edusmart.message.model.RetrofitMessageProvider;
import com.codenicely.edusmart.message.model.data.MessageListData;
import com.codenicely.edusmart.message.presenter.MessagePresenter;
import com.codenicely.edusmart.message.presenter.MessagePresenterImpl;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends Activity implements MessageView {

    private static final String TAG = "MessageActivity" ;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.message)
    TextView message;

    @BindView(R.id.send)
    Button send;

    private boolean CAMERA_REQUEST = false;
    private boolean GALLERY_REQUEST = false;
    private static final int CAMERA_REQUEST_ID = 100;
    private final int GALLERY_REQUEST_ID = 1;


    private MessageAdapter messageAdapter;
    private MessagePresenter messagePresenter;
    private String userId;
    private File image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            userId = getIntent().getStringExtra("userId");
            Toast.makeText(this, userId, Toast.LENGTH_SHORT).show();
        }

        initialize();

        registerReceiver(myReceiver, new IntentFilter(MyFirebaseService.INTENT_FILTER));
    }

    private void initialize() {
        messageAdapter = new MessageAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);
//        linearLayoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);
        messagePresenter = new MessagePresenterImpl(this, new RetrofitMessageProvider(this));
//        messagePresenter.requestMessages(userId, MyApplication.getFcm(), -9999);
//        getMessage();
        /*send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {messagePresenter.sendMessage(userId, message.getText().toString());
                message.setText("");

            }
        });
*/
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoader(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateMessageList(final MessageListData messageListData) {
//        Collections.reverse(messageListData.getMessage_list());
        messageAdapter.updateList(messageListData.getMessage_list());
        messageAdapter.notifyItemRangeInserted(messageAdapter.getItemCount(), messageListData.getMessage_list().size());
//        messageAdapter.notifyDataSetChanged();

//        messageAdapter.notifyDataSetChanged();
//        recyclerView.scrollToPosition(messageAdapter.getItemCount()+1);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());

            }
        });


    }

    @Override
    public void getMessage() {

//        messagePresenter.requestMessages(userId, MyApplication.getFcm(), messageAdapter.getLastMessageId());
    }


    @Override
    public boolean checkPermissionForCamera() {


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;


    }

    @Override
    public boolean checkPermissionForGallery() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;

    }

    @Override
    public boolean requestCameraPermission() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        CAMERA_REQUEST = true;

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        CAMERA_REQUEST = false;

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {


                    }
                }).check();


   /*     Dexter.checkPermissions(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {


                if (multiplePermissionsReport.areAllPermissionsGranted()) {

                    CAMERA_REQUEST = true;
                } else {
                    CAMERA_REQUEST = false;
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
*/

        return CAMERA_REQUEST;
    }

    @Override
    public boolean requestGalleryPermission() {

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        GALLERY_REQUEST = true;

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        GALLERY_REQUEST = false;

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {


                    }
                }).check();


   /*     Dexter.checkPermission(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {*//* ... *//*

                GALLERY_REQUEST = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {*//* ... *//*

                GALLERY_REQUEST = false;
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {*//* ... *//*}
        }, Manifest.permission.READ_EXTERNAL_STORAGE);
*/

        return GALLERY_REQUEST;
    }

    @Override
    public void showCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
        Log.i(TAG, image.getPath());

        if (intent.resolveActivity(this.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAMERA_REQUEST_ID);
        }

    }

    @Override
    public void showGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_ID);


    }


    public void fileFromPath(String filePath) {

        image = new File(filePath);
        Log.i(TAG, "fileFromPath method : " + image.getPath());

    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            getMessage();
        }
    };

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}