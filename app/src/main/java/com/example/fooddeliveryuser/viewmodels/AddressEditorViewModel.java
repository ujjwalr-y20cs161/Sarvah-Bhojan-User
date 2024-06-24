package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.repositories.AddressRepository;

public class AddressEditorViewModel extends AndroidViewModel {
    private final AddressRepository addressRepo;
    private final MutableLiveData<Address> referenceAddress = new MutableLiveData<>();

    public AddressEditorViewModel(@NonNull Application application) {
        super(application);
        addressRepo = new AddressRepository(application);
    }

    public LiveData<Address> getReferenceAddress(int addressId) {
        Log.i(this.getClass().getSimpleName(),"Querying for :"+String.valueOf(addressId));
        return addressRepo.getAddressById(addressId);
    }

    public void setReferenceAddress(Address address) {
        referenceAddress.setValue(address);
    }

    public void saveAddress(Address address) {
        addressRepo.insertAddress(address);
        Log.i(this.getClass().getSimpleName(),"Insertion : "+String.valueOf(address.getAddressId())+" "+address.getAddressLabel());
    }

    public void updateAddress(Address address) {
        addressRepo.updateAddress(address);
        if (address.getIsPrimaryAddress()) {
            addressRepo.updatePrimaryAddress(address.getAddressId());
        }
    }

    public void deleteAddress(Address address){
        addressRepo.deleteAddress(address);
    }
}
