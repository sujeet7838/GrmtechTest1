package com.example.sujeet.grmtechtest1.ratrofit;

import com.example.sujeet.grmtechtest1.models.UserListModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sujeet on 11/29/2017.
 */

public interface RestService {
    /*10. RecommendedBooks*/
    @GET(ApiUrls.BASE_URL)
    Call<UserListModel> getuserList();
}
