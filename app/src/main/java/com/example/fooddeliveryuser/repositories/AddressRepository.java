package com.example.fooddeliveryuser.repositories;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.dao.*;
import com.example.fooddeliveryuser.databases.RoomDB;
import com.example.fooddeliveryuser.models.Address;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddressRepository {
    private AddressDAO addressDAO;
    private LiveData<List<Address>> allAddresses;
    private ExecutorService executorService;

    public AddressRepository(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        addressDAO = db.addressDAO();
        executorService = Executors.newSingleThreadExecutor();

    }

    public LiveData<List<Address>> getAllAddresses() {
        allAddresses = addressDAO.getAllAddress();
        return allAddresses;
    }

    public LiveData<Address> getAddressById(int id) {
        return addressDAO.getAddressById(id);
    }

    public LiveData<List<Address>> getAddressByUserId(String userId) {
        return addressDAO.getAddressByUserId(userId);
    }

    public LiveData<Address> getPrimaryAddress() {
        return addressDAO.getPrimaryAddress();
    }

    @NonNull
    public void insertAddress(Address address) {
        Log.i(this.getClass().getSimpleName(),"Insertion : "+address.getAddressLabel());
        executorService.execute(() -> {
            addressDAO.insertAddress(address);
            if(address.getIsPrimaryAddress()) updatePrimaryAddress(address.getAddressId());
        });
    }

    @NonNull
    public void insertAllAddress(Address... addresses){
        Log.i(this.getClass().getSimpleName(),"Insertion : Multiple");
        executorService.execute(()->{
            addressDAO.insertAll(addresses);
        });
    }

    public void updateAddress(Address address) {
        Log.i(this.getClass().getSimpleName(),"Updated : "+address.getAddressLabel());
        executorService.execute(() ->{
            addressDAO.updateAddress(address);
            if(address.getIsPrimaryAddress())updatePrimaryAddress(address.getAddressId());
        });
    }

    public void deleteAddress(Address address) {
        Log.i(this.getClass().getSimpleName(),"Deleted : "+address.getAddressLabel());
        executorService.execute(() -> addressDAO.deleteAddress(address));
    }

    public void updatePrimaryAddress(int addressId) {
        Log.i(this.getClass().getSimpleName(),"Updated Primary Address : "+addressId);
        executorService.execute(() -> addressDAO.updatePrimaryAddress(addressId));
    }

    public LiveData<Integer> getAddressCount(){
        return addressDAO.getCount();
    }

    public void deleteAllAddress(){
        Log.i(this.getClass().getSimpleName(),"Deleted : All");
        addressDAO.deleteAllAddress();
    }

}
