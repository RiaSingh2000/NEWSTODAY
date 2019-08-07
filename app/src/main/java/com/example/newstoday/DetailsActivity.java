package com.example.newstoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    DiagonalLayout diagonalLayout;
    KenBurnsView kbv;
    TextView author,title,desc;
    String url;
    Button fullArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        diagonalLayout=findViewById(R.id.diagonal_layout);
        kbv=findViewById(R.id.kbv);
        author=findViewById(R.id.author);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        fullArticle=findViewById(R.id.full_article);

        url=getIntent().getStringExtra("url");

        author.setText(getIntent().getStringExtra("author"));
        title.setText(getIntent().getStringExtra("title"));
        desc.setText(getIntent().getStringExtra("desc"));


       if((getIntent().getStringExtra("urlToImage")!=null))

        Picasso.with(DetailsActivity.this)
                .load(getIntent().getStringExtra("urlToImage"))
                .into(kbv);

      else
           kbv.setImageResource(R.drawable.orange3);

        fullArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailsActivity.this,FullArticleActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });







    }
}
