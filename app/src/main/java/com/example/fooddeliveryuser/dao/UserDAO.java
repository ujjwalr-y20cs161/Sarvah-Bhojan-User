package com.example.fooddeliveryuser.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fooddeliveryuser.models.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM USERTABLE")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM USERTABLE WHERE UserId = :id")
    LiveData<User> getUserById(String id);

    @Query("SELECT * FROM USERTABLE WHERE UserName = :name")
    LiveData<User> getUserByName(String name);

    @Query("SELECT * FROM USERTABLE WHERE Email = :email")
    LiveData<User> getUserByEmail(String email);

    @Query("SELECT * FROM USERTABLE WHERE PhoneNumber= :phoneNumber")
    LiveData<User> getUserByPhoneNumber(String phoneNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);
}

