package com.example.newstoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.newstoday.Common.ISO8601DateParser;
import com.example.newstoday.DetailsActivity;
import com.example.newstoday.Interface.ItemClickListener;
import com.example.newstoday.Model.News;
import com.example.newstoday.R;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.HeadlinesAdapterViewHolder> {
    News news;
    Context context;

    public HeadlinesAdapter(News news, Context context) {
        this.news = news;
        this.context = context;

    }

    @NonNull
    @Override
    public HeadlinesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.headlines_layout,viewGroup,false);
        return new HeadlinesAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final HeadlinesAdapterViewHolder headlinesAdapterViewHolder, int i) {
        final int pos=i;
        if(headlinesAdapterViewHolder.title!=null) {
            headlinesAdapterViewHolder.title.setText(news.getArticles().get(i).getTitle());
            if (news.getArticles().get(i).getUrlToImage()!=null&&!news.getArticles().get(i).getUrlToImage().isEmpty()&&!news.getArticles().get(i).getUrlToImage().equals("null"))
                Picasso.with(context).
                        load(news.getArticles().get(i).getUrlToImage())
                        .into(headlinesAdapterViewHolder.headlineImage);
           else
                headlinesAdapterViewHolder.headlineImage.setImageResource(R.drawable.logo);

           headlinesAdapterViewHolder.share.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(Intent.ACTION_SEND);
                   intent.setType("text/plain");
                   intent.putExtra(Intent.EXTRA_SUBJECT,"Share News Link");
                   intent.putExtra(Intent.EXTRA_TEXT,news.getArticles().get(pos).getUrl());
                   context.startActivity(intent);
               }
           });

            Date date= null;
            try {
                date = ISO8601DateParser.parse(news.getArticles().get(i).getPublishedAt());
                headlinesAdapterViewHolder.time.setReferenceTime(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            headlinesAdapterViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onNewsClick(int position) {
                    Intent intent=new Intent(context, DetailsActivity.class);
                    intent.putExtra("content",news.getArticles().get(position).getContent());
                    intent.putExtra("title",news.getArticles().get(position).getTitle());
                    intent.putExtra("urlToImage",news.getArticles().get(position).getUrlToImage());
                    intent.putExtra("desc",news.getArticles().get(position).getDescription());
                    intent.putExtra("url",news.getArticles().get(position).getUrl());
                    intent.putExtra("author",news.getArticles().get(position).getAuthor());
                    context.startActivity(intent);
                }
            });




        }



    }

    @Override
    public int getItemCount() {
        return news.getArticles().size();
    }

    public class HeadlinesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        CircleImageView headlineImage;
        RelativeTimeTextView time;
        ItemClickListener listener;
        Button share;

        public HeadlinesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            headlineImage=itemView.findViewById(R.id.headline_image);
            time=itemView.findViewById(R.id.time);
            share=itemView.findViewById(R.id.share);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onNewsClick(getAdapterPosition());

        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.listener = itemClickListener;
        }
    }
    }

