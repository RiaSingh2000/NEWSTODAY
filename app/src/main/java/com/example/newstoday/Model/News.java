package com.example.newstoday.Model;

import java.util.List;

public class News {
    String status;
    List<Articles> articles;
    int totalResults;

    public String getStatus() {
        return status;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
