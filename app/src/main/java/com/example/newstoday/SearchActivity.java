package com.example.newstoday;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.TextView;

import com.example.newstoday.Adapter.HeadlinesAdapter;
import com.example.newstoday.Adapter.SearchResultAdapter;
import com.example.newstoday.Common.Client;
import com.example.newstoday.Model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
   SearchView searchView;
   SearchResultAdapter searchResultAdapter;
   RecyclerView searchResults;
   RecyclerView.LayoutManager layoutManager;
   TextView textView,text;
   ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView=findViewById(R.id.search);
        searchResults=findViewById(R.id.searchResults);
        layoutManager=new LinearLayoutManager(this);
        searchResults.setLayoutManager(layoutManager);
        textView=findViewById(R.id.msg);
        text=findViewById(R.id.searchTextView);
        actionBar=getSupportActionBar();
       actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA726")));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()) {
                    textView.setVisibility(View.INVISIBLE);
                    text.setVisibility(View.VISIBLE);
                    Call<News> call = Client.getNewsService().getSearch(newText);
                    call.enqueue(new Callback<News>() {
                        @Override
                        public void onResponse(Call<News> call, Response<News> response) {
                            searchResultAdapter = new SearchResultAdapter(response.body(), SearchActivity.this);
                            searchResultAdapter.notifyDataSetChanged();
                            searchResults.setAdapter(searchResultAdapter);
                        }


                        @Override
                        public void onFailure(Call<News> call, Throwable t) {

                        }
                    });
                }
                return false;
            }

        });

    }
    }

