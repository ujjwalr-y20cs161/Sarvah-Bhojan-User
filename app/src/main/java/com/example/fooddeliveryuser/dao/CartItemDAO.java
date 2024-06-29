package com.example.fooddeliveryuser.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fooddeliveryuser.models.CartItem;

import java.util.List;

@Dao
public interface CartItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCartItem(CartItem cartItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCartItems(List<CartItem> cartItems);

    @Update
    public void updateCartItem(CartItem cartItem);

    @Delete
    public void deleteCartItem(CartItem cartItem);

    @Query("Select * From CartItemTable")
    public LiveData<List<CartItem>> getAllCartItems();

    @Query("Select * From CartItemTable Limit 1")
    public LiveData<CartItem> getLastCartItem();

    @Query("Select * From CartItemTable Where count > 0")
    public LiveData<List<CartItem>> getAllValidCartItems();

    @Query("Select Count(*) From CartItemTable Where count > 0")
    public LiveData<Integer> getCartItemCount();

    @Query("Delete From CartItemTable")
    public void deleteAllCartItems();


}
