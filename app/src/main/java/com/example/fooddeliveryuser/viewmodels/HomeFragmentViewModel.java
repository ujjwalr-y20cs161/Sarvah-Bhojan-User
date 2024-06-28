package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.models.Restaurant;
import com.example.fooddeliveryuser.repositories.AddressRepository;
import com.example.fooddeliveryuser.repositories.RestaurantRepository;

import java.util.List;


public class HomeFragmentViewModel extends AndroidViewModel {

    private AddressRepository addressRepository;
    private RestaurantRepository restaurantRepository;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        addressRepository = new AddressRepository(application);
        restaurantRepository = new RestaurantRepository(application);

    }

    public LiveData<Address> getCurrentAddress(){
        return addressRepository.getPrimaryAddress();
    }

    public LiveData<List<Restaurant>> getPopRestaurant(Address address){
        return restaurantRepository.getNearbyRestaurantsByRate(address.getPincode(),3.0);
    }

    public LiveData<List<Restaurant>> searchRestaurant(String RestaurantName){
        return restaurantRepository.getRestaurantsByName(RestaurantName);
    }



}
