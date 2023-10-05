package com.example.myapiaplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static ApiInterface getClient() {
        Retrofit retrofit = new Retrofit.Builder()
               // .baseUrl("http://np.assignmart.com/")
                .baseUrl("http://wavedev.vibsugar.com/potatodovelopment.asmx/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiService = retrofit.create(ApiInterface.class);
        return apiService;
    }
}
