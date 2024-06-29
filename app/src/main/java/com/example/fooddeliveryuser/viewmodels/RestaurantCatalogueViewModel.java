package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryuser.models.CartItem;
import com.example.fooddeliveryuser.models.MenuItem;
import com.example.fooddeliveryuser.models.Restaurant;
import com.example.fooddeliveryuser.repositories.CartItemRepository;
import com.example.fooddeliveryuser.repositories.MenuItemRepository;

import java.util.ArrayList;
import java.util.List;

public class RestaurantCatalogueViewModel extends AndroidViewModel {

    private Restaurant restaurant;

    private MenuItemRepository menuItemRepository;
    private CartItemRepository cartItemRepository;
    public RestaurantCatalogueViewModel(@NonNull Application application) {
        super(application);
        menuItemRepository = new MenuItemRepository(application);
        cartItemRepository = new CartItemRepository(application);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LiveData<List<MenuItem>> getAllItems() {
        return menuItemRepository.getAllMenuItems(restaurant.getRestaurantId());
    }


    public LiveData<List<MenuItem>> getVegItems(){
        return menuItemRepository.getVegMenuItems(restaurant.getRestaurantId());
    }

    public LiveData<List<MenuItem>> getNonVegItems(){
        return menuItemRepository.getNonVegMenuItems(restaurant.getRestaurantId());
    }

    public void insertCartItem(CartItem cartItem){
        Log.i(getClass().getSimpleName(),"Received for Insertion : cartItem "+cartItem.getCartItemId());
        cartItemRepository.insertCartItem(cartItem);
    }

    public void deleteCartItem(CartItem cartItem){
        Log.i(getClass().getSimpleName(),"Received for Deletion: cartItem "+cartItem.getCartItemId());
        cartItemRepository.deleteCartItem(cartItem);
    }

    public LiveData<Integer> getCartItemCount(){
        return cartItemRepository.getCartItemCount();
    }
    public LiveData<CartItem> getLastCartItem(){
        return cartItemRepository.getLastCartItem();
    }

    public void clearCart(){
        cartItemRepository.deleteAllCartItems();
    }


}
