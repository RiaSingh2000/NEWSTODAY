package com.example.newstoday.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.newstoday.Model.Comments;
import com.example.newstoday.R;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsAdapterViewHolder> {
    Context context;
    List<Comments> commentsList;

    public CommentsAdapter(Context context, List<Comments> commentsList) {
        this.context = context;
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public CommentsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.comments_list,viewGroup,false);
        return new CommentsAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapterViewHolder holder, int i) {
        Comments obj=commentsList.get(i);
        String comment=obj.getComment();
        holder.comment.setText(comment);
        double rate=obj.getRate();
        if(rate!=0) {
            holder.rate.setVisibility(View.VISIBLE);
            holder.rate.setRating((float) rate);
        }

    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class CommentsAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView comment;
        RatingBar rate;
        public CommentsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            comment=itemView.findViewById(R.id.comment);
            rate=itemView.findViewById(R.id.rate);
        }
    }
}
