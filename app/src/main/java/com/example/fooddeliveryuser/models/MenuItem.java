package com.example.fooddeliveryuser.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MenuItemTable")
public class MenuItem {

    @NonNull
    @PrimaryKey
    String MenuItemId;

    @ColumnInfo String RestaurantId;

    @ColumnInfo String MenuItemName;

    @ColumnInfo String MenuItemImageURL;

    @ColumnInfo Boolean isVeg;

    @ColumnInfo Double Price;

    public MenuItem() {
    }

    public MenuItem(@NonNull String menuItemId, String restaurantId, String menuItemName, String menuItemImageURL, Boolean isVeg, Double price) {
        MenuItemId = menuItemId;
        RestaurantId = restaurantId;
        MenuItemName = menuItemName;
        MenuItemImageURL = menuItemImageURL;
        this.isVeg = isVeg;
        Price = price;
    }

    @NonNull
    public String getMenuItemId() {
        return MenuItemId;
    }

    public void setMenuItemId(@NonNull String menuItemId) {
        MenuItemId = menuItemId;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getMenuItemName() {
        return MenuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        MenuItemName = menuItemName;
    }

    public String getMenuItemImageURL() {
        return MenuItemImageURL;
    }

    public void setMenuItemImageURL(String menuItemImageURL) {
        MenuItemImageURL = menuItemImageURL;
    }

    public Boolean getVeg() {
        return isVeg;
    }

    public void setVeg(Boolean veg) {
        isVeg = veg;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
