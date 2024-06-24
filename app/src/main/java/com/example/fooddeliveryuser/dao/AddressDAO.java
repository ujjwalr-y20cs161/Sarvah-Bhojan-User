package com.example.fooddeliveryuser.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.fooddeliveryuser.models.Address;

import java.util.List;

@Dao
public interface AddressDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddress(Address address);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Address... addresses);

    @Update
    void updateAddress(Address address);

    @Delete
    void deleteAddress(Address address);

    @Query("DELETE FROM AddressTable")
    void deleteAllAddress();

    @Query("SELECT COUNT(*) FROM AddressTable")
    LiveData<Integer> getCount();

    @Query("SELECT * FROM AddressTable")
    LiveData<List<Address>> getAllAddress();

    @Query("SELECT * FROM AddressTable WHERE AddressId = :id")
    LiveData<Address> getAddressById(int id);

    @Query("SELECT * FROM AddressTable WHERE UserId = :id")
    LiveData<List<Address>> getAddressByUserId(String id);

    @Query("SELECT * FROM AddressTable WHERE isPrimaryAddress = TRUE")
    LiveData<Address> getPrimaryAddress();

    @Query("UPDATE AddressTable SET isPrimaryAddress = 0 WHERE AddressId <> :addressId")
    void unsetAllPrimaryAddressesExcept(int addressId);

    @Query("UPDATE AddressTable SET isPrimaryAddress = 1 WHERE AddressId = :addressId")
    void setPrimaryAddress(int addressId);

    @Transaction
    default void updatePrimaryAddress(int addressId) {
        unsetAllPrimaryAddressesExcept(addressId);
        setPrimaryAddress(addressId);
    }
}
