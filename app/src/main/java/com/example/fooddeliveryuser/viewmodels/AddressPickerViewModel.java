package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.repositories.AddressRepo;

import java.util.List;

public class AddressPickerViewModel extends AndroidViewModel {

    private AddressRepo addressRepo;
    private LiveData<Address> primaryAddress;
    private LiveData<List<Address>> allAddresses;

    public AddressPickerViewModel(@NonNull Application application) {
        super(application);
        addressRepo = new AddressRepo(application);
        primaryAddress = addressRepo.getPrimaryAddress();
        allAddresses = addressRepo.getAllAddresses();
    }

    public LiveData<Address> getPrimaryAddress() {
        return primaryAddress;
    }

    public LiveData<List<Address>> getAllAddresses(){
        return allAddresses;
    }
}
