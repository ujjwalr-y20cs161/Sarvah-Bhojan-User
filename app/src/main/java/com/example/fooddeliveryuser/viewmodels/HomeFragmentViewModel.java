package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.repositories.AddressRepository;


public class HomeFragmentViewModel extends AndroidViewModel {

    private AddressRepository addressRepository;
    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        addressRepository = new AddressRepository(application);


    }

    public LiveData<Address> getCurrentAddress(){
        return addressRepository.getPrimaryAddress();
    }

}
