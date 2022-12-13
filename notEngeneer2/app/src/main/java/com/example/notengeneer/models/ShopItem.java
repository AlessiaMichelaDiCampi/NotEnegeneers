package com.example.notengeneer.models;

import android.media.Image;

public class ShopItem {
    public int id;

    public String description;
    public String longDescription;

    public Image[] photo;

    public String publisher;

    public float price;
    public float quantity;

    public ShopItem(int id, String description, String longDescription, Image[] photo, String publisher, float price, float quantity) {
        this.id = id;
        this.description = description;
        this.longDescription = longDescription;
        this.photo = photo;
        this.publisher = publisher;
        this.price = price;
        this.quantity = quantity;
    }
}
