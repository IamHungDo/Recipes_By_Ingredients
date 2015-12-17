package com.example.hungdo.myrecipe.Dish.SQLite;

/**
 * Created by HungDo on 11/24/15.
 */
public class RecipeData {

    //private variables
    int id;
    String title;
    String publisher;
    String thumbnail;

    // Empty constructor
    public RecipeData() {

    }

    // constructor
    public RecipeData(int id, String title, String publisher, String thumbnail) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
    }

    // constructor
    public RecipeData(String title, String publisher, String thumbnail) {
        this.title = title;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
    }

    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    // getting title
    public String getTitle() {
        return this.title;
    }

    // setting title
    public void setTitle(String title) {
        this.title = title;
    }

    // getting phone number
    public String getPublisher() {
        return this.publisher;
    }

    // setting phone number
    public void setPublisher(String pub) {
        this.publisher = pub;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    // setting title
    public void setThumbnail(String tn) {
        this.thumbnail = tn;
    }
}

