package com.example.newstoday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.support.v7.widget.SearchView ;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.newstoday.Adapter.HeadlinesAdapter;
import com.example.newstoday.Common.AboutDialog;
import com.example.newstoday.Common.Client;
import com.example.newstoday.Interface.ItemClickListener;
import com.example.newstoday.Model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView headlines;
    HeadlinesAdapter headlinesAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button all, enter, sports, science, technology, business, health;
    TextView textView;
    Toolbar toolbar;
    HorizontalScrollView horizontalScrollView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(MainActivity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.show();


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        headlines = findViewById(R.id.headlines);
        headlines.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        headlines.setLayoutManager(layoutManager);
        horizontalScrollView=findViewById(R.id.horizontal);


        //Buttons
        all = findViewById(R.id.all);
        enter = findViewById(R.id.entertainment);
        sports = findViewById(R.id.sports);
        science = findViewById(R.id.science);
        technology = findViewById(R.id.technology);
        business = findViewById(R.id.business);
        health = findViewById(R.id.health);


        textView = findViewById(R.id.textview);
        textView.setText("Top Headlines");


        //Button actions
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                // Toast.makeText(MainActivity.this, "all", Toast.LENGTH_SHORT).show();
                textView.setText("Top Headlines");
                Call<News> call = Client.getNewsService().getHeadlines();
                all.setBackgroundResource(R.drawable.button_background_onclick);
                enter.setBackgroundResource(R.drawable.button_background_default);
                sports.setBackgroundResource(R.drawable.button_background_default);
                business.setBackgroundResource(R.drawable.button_background_default);
                technology.setBackgroundResource(R.drawable.button_background_default);
                science.setBackgroundResource(R.drawable.button_background_default);
                health.setBackgroundResource(R.drawable.button_background_default);


                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(MainActivity.this, "Entertainment", Toast.LENGTH_SHORT).show();
                dialog.show();
                all.setBackgroundResource(R.drawable.button_background_default);
                enter.setBackgroundResource(R.drawable.button_background_onclick);
                sports.setBackgroundResource(R.drawable.button_background_default);
                business.setBackgroundResource(R.drawable.button_background_default);
                technology.setBackgroundResource(R.drawable.button_background_default);
                science.setBackgroundResource(R.drawable.button_background_default);
                health.setBackgroundResource(R.drawable.button_background_default);

                textView.setText("Entertainment");
                Call<News> call = Client.getNewsService().getEntertainment();
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {

                    }
                });

            }
        });


        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                // Toast.makeText(MainActivity.this, "all", Toast.LENGTH_SHORT).show();
                all.setBackgroundResource(R.drawable.button_background_default);
                enter.setBackgroundResource(R.drawable.button_background_default);
                sports.setBackgroundResource(R.drawable.button_background_onclick);
                business.setBackgroundResource(R.drawable.button_background_default);
                technology.setBackgroundResource(R.drawable.button_background_default);
                science.setBackgroundResource(R.drawable.button_background_default);
                health.setBackgroundResource(R.drawable.button_background_default);

                textView.setText("Sports");
                Call<News> call = Client.getNewsService().getSports();

                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "all", Toast.LENGTH_SHORT).show();
                dialog.show();
                all.setBackgroundResource(R.drawable.button_background_default);
                enter.setBackgroundResource(R.drawable.button_background_default);
                sports.setBackgroundResource(R.drawable.button_background_default);
                business.setBackgroundResource(R.drawable.button_background_default);
                technology.setBackgroundResource(R.drawable.button_background_default);
                science.setBackgroundResource(R.drawable.button_background_onclick);
                health.setBackgroundResource(R.drawable.button_background_default);

                textView.setText("Science");
                Call<News> call = Client.getNewsService().getScience();

                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "all", Toast.LENGTH_SHORT).show();
                dialog.show();
                all.setBackgroundResource(R.drawable.button_background_default);
                enter.setBackgroundResource(R.drawable.button_background_default);
                sports.setBackgroundResource(R.drawable.button_background_default);
                business.setBackgroundResource(R.drawable.button_background_onclick);
                technology.setBackgroundResource(R.drawable.button_background_default);
                science.setBackgroundResource(R.drawable.button_background_default);
                health.setBackgroundResource(R.drawable.button_background_default);
                textView.setText("Business");
                Call<News> call = Client.getNewsService().getBusiness();

                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "all", Toast.LENGTH_SHORT).show();
                dialog.show();
                all.setBackgroundResource(R.drawable.button_background_default);
                enter.setBackgroundResource(R.drawable.button_background_default);
                sports.setBackgroundResource(R.drawable.button_background_default);
                business.setBackgroundResource(R.drawable.button_background_default);
                technology.setBackgroundResource(R.drawable.button_background_default);
                science.setBackgroundResource(R.drawable.button_background_onclick);
                health.setBackgroundResource(R.drawable.button_background_default);
                textView.setText("Health");
                Call<News> call = Client.getNewsService().getHealth();

                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "all", Toast.LENGTH_SHORT).show();
                dialog.show();
                all.setBackgroundResource(R.drawable.button_background_default);
                enter.setBackgroundResource(R.drawable.button_background_default);
                sports.setBackgroundResource(R.drawable.button_background_default);
                business.setBackgroundResource(R.drawable.button_background_default);
                technology.setBackgroundResource(R.drawable.button_background_onclick);
                science.setBackgroundResource(R.drawable.button_background_default);
                health.setBackgroundResource(R.drawable.button_background_default);
                textView.setText("Technology");
                Call<News> call = Client.getNewsService().getTechnology();

                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        dialog.cancel();
                        headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                        headlinesAdapter.notifyDataSetChanged();
                        headlines.setAdapter(headlinesAdapter);
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


/*
        //Retrofit builder
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create());

        //Retrofit object
        Retrofit retrofit=builder.build();

        //Creating client using the interface NewsService
        NewsService client=retrofit.create(NewsService.class);
*/

        Call<News> call = Client.getNewsService().getHeadlines();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                dialog.cancel();
                headlinesAdapter = new HeadlinesAdapter(response.body(), MainActivity.this);
                headlinesAdapter.notifyDataSetChanged();
                headlines.setAdapter(headlinesAdapter);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error:(", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.search1:
               Intent intent=new Intent(MainActivity.this,SearchActivity.class);
               startActivity(intent);
                return true;

            case R.id.about:
                AboutDialog aboutDialog=new AboutDialog();
                aboutDialog.show(getSupportFragmentManager(),"About Us!!");

                return true;

            case R.id.rate:
                //Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,RatingActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
