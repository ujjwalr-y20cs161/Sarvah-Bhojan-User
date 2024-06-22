package com.example.fooddeliveryuser.repositories;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryuser.dao.*;
import com.example.fooddeliveryuser.databases.RoomDB;
import com.example.fooddeliveryuser.models.Address;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  AddressRepo {
    private AddressDAO addressDAO;
    private LiveData<List<Address>> allAddresses;
    private ExecutorService executorService;

    public AddressRepo(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        addressDAO = db.addressDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Address>> getAllAddresses() {
        return addressDAO.getAllAddress();
    }

    public LiveData<Address> getAddressById(int id) {
        return addressDAO.getAddressById(id);
    }

    public LiveData<List<Address>> getAddressByUserId(String userId) {
        return addressDAO.getAddressByUserId(userId);
    }

//    private void reloadAllAddresses() {
//        executorService.execute(() -> {
//            List<Address> addresses = addressDAO.getAllAddress()
//            allAddresses = new LiveData<List<Address>>() {
//                @Override
//                protected void postValue(List<Address> value) {
//                    super.postValue(addresses);
//                }
//            };
//        });
//    }

    public LiveData<Address> getPrimaryAddress() {
        return addressDAO.getPrimaryAddress();
    }

    public void insertAddress(Address address) {
        Log.i("AddressRepo",address.getAddressLabel());
        executorService.execute(() -> {
            addressDAO.insertAddress(address);
//            reloadAllAddresses();
        });
    }

    public void insertAllAddress(Address... addresses){
        executorService.execute(()->{
            addressDAO.insertAll(addresses);
        });
    }

    public void updateAddress(Address address) {
        addressDAO.updateAddress(address);
    }

    public void deleteAddress(Address address) {
        executorService.execute(() -> addressDAO.deleteAddress(address));
    }

    public void updatePrimaryAddress(int addressId) {
        executorService.execute(() -> addressDAO.updatePrimaryAddress(addressId));
    }

    public LiveData<Integer> getAddressCount(){
        return addressDAO.getCount();
    }

    public void deleteAllAddress(){
        addressDAO.deleteAllAddress();
    }



}
