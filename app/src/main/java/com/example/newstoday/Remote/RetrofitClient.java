package com.example.newstoday.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getRetrofitClient(){
        //Retrofit builder
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create());
        //Retrofit object
        Retrofit retrofit=builder.build();
        //Returning the retrofit object
        return retrofit;
    }
}
