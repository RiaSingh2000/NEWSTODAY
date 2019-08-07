package com.example.newstoday.Interface;

import com.example.newstoday.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NewsService {

    @GET("https://newsapi.org/v2/top-headlines?language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getHeadlines();

    @GET("https://newsapi.org/v2/everything?sources=buzzfeed&language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getEntertainment();

    @GET("https://newsapi.org/v2/everything?sources=bbc-sport&language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getSports();

    @GET("https://newsapi.org/v2/everything?sources=national-geographic&language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getScience();

    @GET("https://newsapi.org/v2/everything?sources=CNBC&language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getBusiness();

    @GET("https://newsapi.org/v2/everything?sources=medical-news-today&language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getHealth();

    @GET("https://newsapi.org/v2/everything?sources=ars-technica&language=en&pageSize=100&apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getTechnology();

    @GET("https://newsapi.org/v2/everything?apiKey=73681aa1bd98431db60569c8ed05c125")
    Call<News> getSearch(@Query("q") String q);

}
