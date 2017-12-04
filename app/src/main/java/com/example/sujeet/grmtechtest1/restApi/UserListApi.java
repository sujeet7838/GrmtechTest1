package com.example.sujeet.grmtechtest1.restApi;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.sujeet.grmtechtest1.models.UserListModel;
import com.example.sujeet.grmtechtest1.ratrofit.RestAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sujeet on 11/29/2017.
 */
public class UserListApi implements Callback<UserListModel> {
    private static final String TAG = UserListApi.class.getSimpleName();

    private OnUserListApiListener mOnUserListApiListener;

    private ProgressDialog mProgressDialog;

    private Call<UserListModel> mCall;

    public UserListApi(OnUserListApiListener onUserListApiListener) {
        this.mOnUserListApiListener = onUserListApiListener;
    }

    public UserListApi connect() {
        mCall = RestAdapter.getAdapter()
                .getuserList();
        mCall.enqueue(this);
        return this;
    }

    public void showLoadingDialog(Context context, boolean isCancellable) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(isCancellable);
        mProgressDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
    }

    @Override
    public void onResponse(Call<UserListModel> call, Response<UserListModel> response) {
        if (response.isSuccessful()) {
            UserListModel data = response.body();
           mOnUserListApiListener.onUserListApiSuccess(data);
           /* if (data.isError()) {
                mOnUserListApiListener.onUserListApiFailure(data);
            } else {
                mOnUserListApiListener.onUserListApiSuccess(data);
            }*/
        } else {
            try {
                mOnUserListApiListener.onUserListApiException(response.errorBody().string());
            } catch (IOException e) {
                //StandardUtil.printError(TAG, e.getMessage(), e);
            }
        }
        dismissLoadingDialog();
    }

    @Override
    public void onFailure(Call<UserListModel> call, Throwable t) {
        //StandardUtil.printError(TAG, t.getMessage(), t);
        mOnUserListApiListener.onUserListApiException(t.getMessage());
        dismissLoadingDialog();
    }

    public interface OnUserListApiListener {
        void onUserListApiSuccess(UserListModel data);

        void onUserListApiFailure(UserListModel data);

        void onUserListApiException(String message);
    }
}
