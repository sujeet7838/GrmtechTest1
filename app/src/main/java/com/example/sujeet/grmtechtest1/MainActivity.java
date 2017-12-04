package com.example.sujeet.grmtechtest1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.sujeet.grmtechtest1.adapter.UserListRecyclerAdapter;
import com.example.sujeet.grmtechtest1.models.UserListModel;
import com.example.sujeet.grmtechtest1.restApi.UserListApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListApi.OnUserListApiListener{
    private RecyclerView recyclerView;

    private UserListModel userListModel;
    UserListRecyclerAdapter mListAdapter;
    private UserListApi mUserlistApi;
    ProgressDialog progressDialog;

    private List<UserListModel.DataBean> arr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserlistApi = new UserListApi(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        setRecyclerview();
        //Hello world example in android
    }

    private void setRecyclerview() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        userListModel = new UserListModel();
        arr = new ArrayList<UserListModel.DataBean>();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onUserListApiSuccess(UserListModel data) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
        arr = data.getData();
        mListAdapter = new UserListRecyclerAdapter(getApplicationContext(), arr);
        recyclerView.setAdapter(mListAdapter);

    }

    @Override
    public void onUserListApiFailure(UserListModel data) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUserListApiException(String message) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
