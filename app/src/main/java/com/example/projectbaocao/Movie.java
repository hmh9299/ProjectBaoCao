package com.example.projectbaocao;

import android.net.Uri;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String name;
    private String type;
    private String description;
    private String date;
    private int review;

    public Movie(int id, String name, String description, String date, int review) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.review = review;
    }

    public Movie(int id, String name, String type, String description, String date, int review) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
        this.review = review;
    }

    public Movie(String name, String type, String description, String date, int review) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.date = date;
        this.review = review;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
