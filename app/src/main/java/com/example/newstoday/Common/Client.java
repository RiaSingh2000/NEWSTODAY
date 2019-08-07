package com.example.newstoday.Common;

import com.example.newstoday.Interface.NewsService;
import com.example.newstoday.Remote.RetrofitClient;

public class Client {
    public static NewsService getNewsService(){
        //Creating client for the retrofit object using the NewsService interface
        NewsService client= RetrofitClient.getRetrofitClient().create(NewsService.class);
        return client;
    }
}
