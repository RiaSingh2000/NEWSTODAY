package com.example.newstoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultAdapterViewHolder> {
   News news ;
   Context context;

    public SearchResultAdapter(News news, Context context) {
        this.news = news;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchResultAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.headlines_layout,viewGroup,false);
        return new SearchResultAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapterViewHolder searchResultAdapterViewHolder, int i) {
        if(searchResultAdapterViewHolder.title!=null) {
            searchResultAdapterViewHolder.title.setText(news.getArticles().get(i).getTitle());
            if (news.getArticles().get(i).getUrlToImage()!=null&&!news.getArticles().get(i).getUrlToImage().isEmpty()&&!news.getArticles().get(i).getUrlToImage().equals("null"))
                Picasso.with(context).
                        load(news.getArticles().get(i).getUrlToImage())
                        .into(searchResultAdapterViewHolder.headlineImage);
            else
                searchResultAdapterViewHolder.headlineImage.setImageResource(R.drawable.logo);

            Date date= null;
            try {
                date = ISO8601DateParser.parse(news.getArticles().get(i).getPublishedAt());
                searchResultAdapterViewHolder.time.setReferenceTime(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            searchResultAdapterViewHolder.setItemClickListener(new ItemClickListener() {
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

    public class SearchResultAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        CircleImageView headlineImage;
        RelativeTimeTextView time;
        ItemClickListener listener;
        public SearchResultAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            headlineImage=itemView.findViewById(R.id.headline_image);
            time=itemView.findViewById(R.id.time);
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
