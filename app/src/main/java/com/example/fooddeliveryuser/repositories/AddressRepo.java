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

public class  AddressRepo {
    private AddressDAO addressDAO;
    private LiveData<List<Address>> allAddresses;
    private ExecutorService executorService;

    public AddressRepo(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        addressDAO = db.addressDAO();
        executorService = Executors.newSingleThreadExecutor();

//        Address address1 = new Address(
//                "User101",
//                "Home",
//                "123 Main St",
//                "New York, NY",
//                "10001",
//                "John Doe",
//                "555-1234",
//                true
//        );
//
//        // Example 2
//        Address address2 = new Address(
//                "User102",
//                "Work",
//                "456 Elm St",
//                "Los Angeles, CA",
//                "90001",
//                "Jane Smith",
//                "555-5678",
//                false
//        );
//
//        // Example 3
//        Address address3 = new Address(
//                "User103",
//                "Gym",
//                "789 Maple Ave",
//                "Chicago, IL",
//                "60601",
//                "Alice Johnson",
//                "555-9012",
//                true
//        );
//
//        // Example 4
//        Address address4 = new Address(
//                "User104",
//                "Friend's House",
//                "101 Oak St",
//                "Houston, TX",
//                "77001",
//                "Bob Brown",
//                "555-3456",
//                false
//        );
//
//        insertAllAddress(address1,address2,address3,address4);

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

    public LiveData<Address> getPrimaryAddress() {
        return addressDAO.getPrimaryAddress();
    }

    @NonNull
    public void insertAddress(Address address) {
        Log.i("AddressRepo",address.getAddressLabel());
//        address.setColor(ColorRandom.randomColor());
        executorService.execute(() -> {
            addressDAO.insertAddress(address);
        });
    }

    @NonNull
    public void insertAllAddress(Address... addresses){
//        for (Address a:addresses) {
//            a.setColor(ColorRandom.randomColor());
//        }
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
