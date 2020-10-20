package com.google.luckydrawerapp;

public class CustomDataModel {
    String id;
    String item_name;
    String description;
    String images;
    String count;
    String subscribe_count;

    public CustomDataModel(String id, String item_name, String description, String images, String count, String subscribe_count) {
        this.id = id;
        this.item_name = item_name;
        this.description = description;
        this.images = images;
        this.count = count;
        this.subscribe_count = subscribe_count;
    }

    public CustomDataModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSubscribe_count() {
        return subscribe_count;
    }

    public void setSubscribe_count(String subscribe_count) {
        this.subscribe_count = subscribe_count;
    }
}
