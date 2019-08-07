package com.example.newstoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {
    Button send,rate;
    EditText comm;
    RatingBar bar;
    double r;
    String c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        comm=findViewById(R.id.comments);

        send=findViewById(R.id.send);
        rate=findViewById(R.id.rate);
        bar=findViewById(R.id.bar);

        rate.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(CommentsActivity.this, r+"\n"+c, Toast.LENGTH_SHORT).show();
                bar.setRating(0);
                bar.setVisibility(View.INVISIBLE);
                comm.setText("");

            }
        });
    }

    void uploadRatings(){
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
            protected Map<String,String> getParams() throws AuthFailureError {
                String rate=Double.toString(r);
                Map<String,String>params=new HashMap<>();
                params.put("comments",c);
                params.put("rate",rate);
                return params;

            }
        };
        RequestQueue queue= Volley.newRequestQueue(CommentsActivity.this);
        queue.add(request);
    }


}
