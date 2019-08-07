package com.example.newstoday;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newstoday.Adapter.CommentsAdapter;
import com.example.newstoday.Model.Comments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView RView;
    CommentsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    /*Button send,rate;
    EditText comm;
    RatingBar bar;*/
    Button add;



    List<Comments> list;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA726")));
        RView=findViewById(R.id.list);
        RView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(RatingActivity.this);
        RView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
       DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(RatingActivity.this,DividerItemDecoration.VERTICAL);
       dividerItemDecoration.setDrawable(getDrawable(R.drawable.divider));
       RView.addItemDecoration(dividerItemDecoration);

       add=findViewById(R.id.add);


        /*comm=findViewById(R.id.comments);

        send=findViewById(R.id.send);
        rate=findViewById(R.id.rate);
        bar=findViewById(R.id.bar);*/

        /*rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 r=bar.getRating();
                 c=comm.getText().toString();
                 uploadRatings();
                 loadRatings();
                Toast.makeText(RatingActivity.this, r+"\n"+c, Toast.LENGTH_SHORT).show();
                bar.setRating(0);
                bar.setVisibility(View.INVISIBLE);
                comm.setText("");

            }
        });*/
        loadRatings();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RatingActivity.this,CommentsActivity.class));
            }
        });
    }

    void loadRatings(){
        String url="https://riasingh2000.000webhostapp.com/news/getPost.php";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                            //    Toast.makeText(RatingActivity.this, response+"Entered", Toast.LENGTH_LONG).show();
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String comment=jsonObject.getString("comment");
                                String  r=jsonObject.getString("rate");
                                double rate=Double.parseDouble(r);
                                Comments obj=new Comments(comment,rate);
                                list.add(obj);
                            }
                            adapter=new CommentsAdapter(RatingActivity.this,list);
                            RView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RatingActivity.this, "Error occurred :(", Toast.LENGTH_SHORT).show();

                    }
                });
        RequestQueue queue=Volley.newRequestQueue(RatingActivity.this);
        queue.add(request);

    }

    /*void uploadRatings(){
        String url="https://riasingh2000.000webhostapp.com/news/Post.php";
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            protected Map<String,String> getParams() throws AuthFailureError{
                String rate=Double.toString(r);
                Map<String,String>params=new HashMap<>();
                params.put("comments",c);
                params.put("rate",rate);
                return params;

        }
        };
        RequestQueue queue=Volley.newRequestQueue(RatingActivity.this);
        queue.add(request);
    }*/


}
