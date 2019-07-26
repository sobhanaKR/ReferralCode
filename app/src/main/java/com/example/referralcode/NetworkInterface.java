package com.example.referralcode;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkInterface {


    @GET("/v2/5d2d6f432e00005d00c57cd6")
    Call<ListingModel> getProductList();
}
