package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.fooddeliveryuser.repositories.AddressRepo;

public class AddressEditorViewModel extends AndroidViewModel {

    private AddressRepo repo;
    public AddressEditorViewModel(@NonNull Application application) {
        super(application);

        repo = new AddressRepo(application);



    }
}
