package com.example.fooddeliveryuser.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "RestaurantTable")
public class Restaurant implements Serializable {

    private static int idGen = 100;
    @NonNull
    @PrimaryKey
    String RestaurantId;

    @ColumnInfo String RestaurantName;

    @ColumnInfo String AddressLine;

    @ColumnInfo Double Latitude;

    @ColumnInfo Double Longtitude;

    @ColumnInfo String CityState;

    @ColumnInfo String Pincode;

    @ColumnInfo String Status;

    @ColumnInfo Double Rating;

    @ColumnInfo String Cusine;

    @ColumnInfo String ImageURL;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, String addressLine, Double latitude, Double longtitude, String cityState, String pincode, String status, Double rating, String cusines) {
        RestaurantId = idGenerator();
        RestaurantName = restaurantName;
        AddressLine = addressLine;
        Latitude = latitude;
        Longtitude = longtitude;
        CityState = cityState;
        Pincode = pincode;
        Status = status;
        Rating = rating;
        Cusine = cusines;
        ImageURL = "https://t4.ftcdn.net/jpg/02/75/70/03/360_F_275700347_09reCCwb7JBxTKiYQXsyri4riMKaHbj8.jpg";

    }

    private static String idGenerator(){
        return "SBFDR" + String.valueOf(++idGen);
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getAddressLine() {
        return AddressLine;
    }

    public void setAddressLine(String addressLine) {
        AddressLine = addressLine;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(Double longtitude) {
        Longtitude = longtitude;
    }

    public String getCityState() {
        return CityState;
    }

    public void setCityState(String cityState) {
        CityState = cityState;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }

    public String getCusine() {
        return Cusine;
    }

    public void setCusine(String cusines) {
        Cusine  = cusines;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
