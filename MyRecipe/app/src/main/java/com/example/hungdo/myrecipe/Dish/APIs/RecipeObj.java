package com.example.hungdo.myrecipe.Dish.APIs;

import java.io.Serializable;

public class RecipeObj implements Serializable {
    private String title, thumbnailUrl, publisher, recipe_id, label, sourceUrl, itemName, brandName, calories, totalFat, ingredients;


    public RecipeObj() {
    }

    public RecipeObj(String name, String thumbnailUrl, int social_rank, String recipe_id, String publisher, String f2fURL, String label, String sourceUrl
            , String item, String brand, String calories, String fat, String ingredients) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.recipe_id = recipe_id;
        this.publisher = publisher;
        this.label = label;
        this.sourceUrl = sourceUrl;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }


    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brand) {
        this.brandName = brand;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(String fat) {
        this.totalFat = fat;
    }

}
