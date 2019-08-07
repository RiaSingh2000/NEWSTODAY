package com.example.newstoday.Model;

class Source {
    String id,name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class Articles {

    String author,title,description,url,urlToImage,publishedAt,content;
    Source source;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public Source getSource() {
        return source;
    }
}


