package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddeliveryuser.models.CartItem;
import com.example.fooddeliveryuser.repositories.CartItemRepository;

import java.util.List;

public class CartFragmentViewModel extends AndroidViewModel {

    private CartItemRepository cartItemRepository;
    public CartFragmentViewModel(@NonNull Application application) {
        super(application);
        cartItemRepository = new CartItemRepository(application);
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

    public LiveData<List<CartItem>> getAllCartItems(){
        return  cartItemRepository.getAllCartItems();
    }

    public void clearCart(){
        cartItemRepository.deleteAllCartItems();
    }

}
