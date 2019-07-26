package com.example.referralcode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListingFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listing_layout, container, false);
        init(rootView);
        getApiCall();
        return rootView;
    }

    private void init(View rootView) {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkInterface networkInterface = retrofit.create(NetworkInterface.class);
        Call<ListingModel> call = networkInterface.getProductList();
        call.enqueue(new Callback<ListingModel>() {
            @Override
            public void onResponse(Call<ListingModel> call, Response<ListingModel> response) {
                if (response.isSuccessful()) {
                    ListingModel listingModel = response.body();
                    ListingAdapter listingAdapter = new ListingAdapter(listingModel.getItems(),listingModel.share_message,(AppCompatActivity)getActivity());
                    recyclerView.setAdapter(listingAdapter);
                }
            }

            @Override
            public void onFailure(Call<ListingModel> call, Throwable t) {

            }
        });
    }
}
