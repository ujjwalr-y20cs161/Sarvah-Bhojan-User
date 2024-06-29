package com.example.fooddeliveryuser.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.dao.CartItemDAO;
import com.example.fooddeliveryuser.dao.MenuItemDAO;
import com.example.fooddeliveryuser.databases.RoomDB;
import com.example.fooddeliveryuser.models.CartItem;
import com.example.fooddeliveryuser.models.MenuItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartItemRepository {

    private CartItemDAO cartItemDAO;
    private ExecutorService executor;

    private static boolean onStartUp = true;

    public CartItemRepository(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        cartItemDAO = db.cartItemDAO();
        executor = Executors.newFixedThreadPool(2);
        if(onStartUp){
            deleteAllCartItems();
            onStartUp = false;
        }
    }

    public void insertCartItem(CartItem menuItem){
        Log.i(getClass().getSimpleName(),"Insertion : "+menuItem.getCartItemId());
        executor.execute(()->{
            cartItemDAO.insertCartItem(menuItem);
        });
    }

    public void insertCartItems(List<CartItem> menuItems){
        Log.i(getClass().getSimpleName(),"Insertion : CartItems");
        executor.execute(()->{
            cartItemDAO.insertCartItems(menuItems);
        });
    }

    public void deleteCartItem(CartItem cartItem){
        Log.i(getClass().getSimpleName(),"Deletion : "+cartItem.getCartItemId());
        executor.execute(()->{
            cartItemDAO.deleteCartItem(cartItem);
        });
    }


    public LiveData<List<CartItem>> getAllCartItems(){
        return cartItemDAO.getAllValidCartItems();
    }

    public LiveData<Integer> getCartItemCount(){
        return cartItemDAO.getCartItemCount();
    }

    public void deleteAllCartItems(){
        Log.i(getClass().getSimpleName(),"Deletion : All CartItems");
        executor.execute(()->{
            cartItemDAO.deleteAllCartItems();
        });
    }

    public LiveData<CartItem> getLastCartItem(){
        return cartItemDAO.getLastCartItem();
    }

}
