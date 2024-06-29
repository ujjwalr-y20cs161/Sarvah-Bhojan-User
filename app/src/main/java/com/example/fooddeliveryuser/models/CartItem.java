package com.example.fooddeliveryuser.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "CartItemTable")
public class CartItem {
    @NonNull
    @PrimaryKey
    String CartItemId;

    private static int idGen = 10000;

    @ColumnInfo
    String MenuItemId;
    @ColumnInfo
    String RestaurantId;

    @ColumnInfo
    String MenuItemName;

    @ColumnInfo
    Integer count;

    @ColumnInfo
    String MenuItemImageURL;

    @ColumnInfo
    Boolean isVeg;

    @ColumnInfo
    Double Price;

    public CartItem() {
    }

    public CartItem(@NonNull String menuItemId, String restaurantId, String menuItemName, String menuItemImageURL, Boolean isVeg, Double price) {
        CartItemId = idGenerator();
        MenuItemId = menuItemId;
        RestaurantId = restaurantId;
        MenuItemName = menuItemName;
        MenuItemImageURL = menuItemImageURL;
        this.isVeg = isVeg;
        Price = price;
        count = 0;
    }

    public CartItem(@NonNull MenuItem menuItem) {
        CartItemId = idGenerator();
        this.MenuItemId = menuItem.getMenuItemId();
        this.RestaurantId = menuItem.getRestaurantId();
        this.MenuItemName = menuItem.getMenuItemName();
        this.Price = menuItem.getPrice();
        this.isVeg = menuItem.getVeg();
        this.MenuItemImageURL = menuItem.getMenuItemImageURL();
        count = 0;
    }

    private static String idGenerator() {
        return "SBFDCI" + String.valueOf(idGen++);
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

    @NonNull
    public String getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(@NonNull String cartItemId){
        CartItemId = cartItemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}