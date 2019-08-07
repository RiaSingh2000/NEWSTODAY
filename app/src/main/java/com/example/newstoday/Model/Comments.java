package com.example.newstoday.Model;

public class Comments {
    String comment;
    double rate;

    public Comments(String comment, double rate) {
        this.comment = comment;
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public double getRate() {
        return rate;
    }
}
