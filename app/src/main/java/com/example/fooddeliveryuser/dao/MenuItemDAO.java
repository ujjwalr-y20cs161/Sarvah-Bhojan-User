package com.example.fooddeliveryuser.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fooddeliveryuser.models.MenuItem;

import java.util.List;

@Dao
public interface MenuItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMenuItem(MenuItem menuItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMenuItems(List<MenuItem> menuItems);

    @Delete
    public void deleteMenuItem(MenuItem menuItem);

    @Query("Select * From MenuItemTable Where RestaurantId = :resId")
    public LiveData<List<MenuItem>> getAllMenuItems(String resId);

    @Query("Select * From MenuItemTable Where RestaurantId = :resId and isVeg = :veg ")
    public LiveData<List<MenuItem>> getCategorizeItems(String resId,boolean veg);


}
