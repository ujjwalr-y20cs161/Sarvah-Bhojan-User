package com.example.fooddeliveryuser.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.fooddeliveryuser.databases.ColorRandom;

import java.io.Serializable;

@Entity(tableName = "AddressTable")
public class Address implements Serializable {

    private static int addressGen = 1000;
    @PrimaryKey
    @ColumnInfo(name = "AddressId") int AddressId;

    @ColumnInfo(name = "UserId") String UserId;

    @ColumnInfo(name = "AddressLabel") String AddressLabel;

    @ColumnInfo(name = "Latitude") Double Latitude;

    @ColumnInfo(name = "Longitude") Double Longitude;

    @ColumnInfo(name = "AddressLine") String AddressLine;

    @ColumnInfo(name = "City_State") String CityState;

    @ColumnInfo(name = "Pincode") String Pincode;

    @ColumnInfo(name = "ReceiverName") String ReceiverName;

    @ColumnInfo(name = "ReceiverPhoneNumber") String ReceiverPhoneNumber;

    @ColumnInfo(name = "isPrimaryAddress") Boolean isPrimaryAddress;

    private String color;

    public Address() {
    }

    public Address(String userId, String addressLabel, String doorNoStreet, String cityState, String pincode, String receiverName, String receiverPhoneNumber,Boolean isPrim) {
        AddressId = getAddressGen();
        UserId =userId;
        AddressLabel = addressLabel;
        AddressLine = doorNoStreet;
        CityState = cityState;
        Pincode = pincode;
        ReceiverName = receiverName;
        ReceiverPhoneNumber = receiverPhoneNumber;
        isPrimaryAddress = isPrim;
        Latitude = 0.0;
        Longitude = 0.0;
        color = ColorRandom.randomColor();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLocation(Double Lat, Double Long){
        setLatitude(Lat);
        setLongitude(Long);
    }

    private static int getAddressGen(){
        return addressGen++;
    }


    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAddressLabel() {
        return AddressLabel;
    }

    public void setAddressLabel(String addressLabel) {
        AddressLabel = addressLabel;
    }

    public String getAddressLine() {
        return AddressLine;
    }

    public void setAddressLine(String doorNoStreet) {
        AddressLine = doorNoStreet;
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

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public String getReceiverPhoneNumber() {
        return ReceiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        ReceiverPhoneNumber = receiverPhoneNumber;
    }

    public Boolean getIsPrimaryAddress() {
        return isPrimaryAddress;
    }

    public void setIsPrimaryAddress(Boolean selected) {
        this.isPrimaryAddress= selected;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

}
