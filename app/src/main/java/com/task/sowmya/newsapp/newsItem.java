package com.task.sowmya.newsapp;

/**
 * Created by DELL on 29-07-2017.
 */

public class newsItem {
    private String newsTitle;
    private String author;
    private String newsDesc;
    private String published;
    private String url;
    private String imageId;

    public newsItem(String author, String newsTitle, String newsDesc, String url, String imageId,String published) {
        this.author = author;
        this.newsTitle = newsTitle;
        this.newsDesc = newsDesc;
        this.url = url;
        this.published = published;
        this.imageId = imageId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublished() {
        return published;
    }



    public String getUrl() {
        return url;
    }

    public String getImageId() {
        return imageId;
    }
}
