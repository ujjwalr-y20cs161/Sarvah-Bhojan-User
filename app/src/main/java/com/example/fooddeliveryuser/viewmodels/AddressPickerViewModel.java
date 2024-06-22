package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.repositories.AddressRepo;
import com.example.fooddeliveryuser.views.AddressPicker;

import java.util.ArrayList;
import java.util.List;

public class AddressPickerViewModel extends AndroidViewModel {

    private AddressRepo addressRepo;
    private MutableLiveData<List<Address>> addressCatalogue;

    private MutableLiveData<Integer> addressCount;


    public AddressPickerViewModel(Application application) {
        super(application);
        addressRepo = new AddressRepo(application);
        addressCatalogue = new MutableLiveData<>();
        addressCount = new MutableLiveData<>(0);

        addressRepo.getAddressCount().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                addressCount.setValue(integer);
            }
        });


        // Adding sample addresses
        Address a = new Address(1,"user101", "Home", "1-230", "OGL", "51234", "mr.X", "0123456789");
        Address b = new Address(2,"user101", "Office", "9-230", "OGL", "51234", "mr.Y", "0112233445");
        Address c = new Address(3,"user101", "Guest Home", "110-230", "OGL", "51234", "mr.Z", "0112663445");
        Address d = new Address(3,"user102", "Holiday Home", "12-30", "NYC", "12345", "mr.A", "0123344556");
        Address e = new Address(4,"user103", "Vacation Home", "11-230", "LAX", "51234", "mr.B", "0133344556");
        Address f = new Address(5,"user104", "Temporary Home", "21-230", "CHI", "21456", "mr.C", "0143344556");

        addressRepo.insertAddress(a);
        updateAddressList();
    }

    public void updateAddressList() {
        addressRepo.getAllAddresses().observeForever(new Observer<List<Address>>() {
            @Override
            public void onChanged(List<Address> newAddresses) {
                Log.i("AVM","addressRepo.getAllAddresses() - Change Observed");

                if (addressRepo.getAllAddresses() != null) {
                    List<Address> currentAddresses = addressCatalogue.getValue();
                    currentAddresses = newAddresses;
//                    for(Address a : currentAddresses){
//                        Log.i("AVM",a.getAddressLabel());
//                    }
                    addressCatalogue.setValue(currentAddresses);
                }
            }
        });
    }


    public MutableLiveData<List<Address>> getAllAddress(){
        return addressCatalogue;
    }

    public LiveData<Address> getPrimaryAddress() {
        return addressRepo.getPrimaryAddress();
    }


    public MutableLiveData<Integer> getAddressCount(){
        return addressCount;
    }

    public void deleteAll(){
        addressRepo.deleteAllAddress();
    }

}
