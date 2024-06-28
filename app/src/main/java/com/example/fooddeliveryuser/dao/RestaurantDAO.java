package com.example.fooddeliveryuser.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fooddeliveryuser.models.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRestaurant(Restaurant restaurant);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllRestaurant(List<Restaurant>  restaurants);

    @Update
    public void updateRestaurant(Restaurant restaurant);

    @Delete
    public void deleteRestaurant(Restaurant restaurant);

    @Query("SELECT * FROM RESTAURANTTABLE WHERE RestaurantId = :id")
    public LiveData<Restaurant> getRestaurantById(String id);

    @Query("SELECT * FROM RestaurantTable WHERE Pincode = :pin")
    public LiveData<List<Restaurant>> getNearByRestaurant(String pin);

    @Query("SELECT * FROM RestaurantTable WHERE RestaurantName Like :resName")
    public  LiveData<List<Restaurant>> getRestaurantByName(String resName);

    @Query("SELECT * FROM RestaurantTable WHERE Pincode = :pin and Rating > :rate")
    public  LiveData<List<Restaurant>> getNearByRestaurantsByRate(String pin,Double rate);

    @Query("SELECT * FROM RestaurantTable WHERE Status = :state")
    public LiveData<List<Restaurant>> getOpenedRestaurants(String state);

    @Query("SELECT * FROM RESTAURANTTABLE")
    public LiveData<List<Restaurant>> getAllRestaurant();



}
