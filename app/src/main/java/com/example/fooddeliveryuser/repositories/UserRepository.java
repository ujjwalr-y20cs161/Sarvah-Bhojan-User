package com.example.fooddeliveryuser.repositories;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.fooddeliveryuser.dao.UserDAO;
import com.example.fooddeliveryuser.databases.RoomDB;
import com.example.fooddeliveryuser.models.User;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private UserDAO userDAO;

    private ExecutorService executorService;

    public UserRepository(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        userDAO = db.userDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<User>> getAllUsers() {
        Log.i(getClass().getSimpleName(),"GetUsers");
            return userDAO.getAllUsers();
    }

    public LiveData<User> getUserById(String id) {
        Log.i(getClass().getSimpleName(),"GetUser by ID : "+id);
        return userDAO.getUserById(id);
    }

    public LiveData<User> getUserByName(String name) {
        Log.i(getClass().getSimpleName(),"GetUser by Name : "+name);
        return userDAO.getUserByName(name);
    }

    public LiveData<User> getUserByPhoneNumber(String phoneNumber) {
        Log.i(getClass().getSimpleName(),"GetUser by PhoneNumber : "+phoneNumber);
        return userDAO.getUserByPhoneNumber(phoneNumber);
    }

    public LiveData<User> getUserByEmail(String email) {
        Log.i(getClass().getSimpleName(),"GetUser by Email : "+email);
        return userDAO.getUserByEmail(email);
    }

    public void insertUser(User user) {
        Log.i(getClass().getSimpleName(),"InsertUser : "+user.getUserId());
        executorService.execute(() -> userDAO.insertUser(user));
    }

    @NonNull
    public void deleteUser(String  userId,String userName) {
        Log.i(getClass().getSimpleName(),"delete :" + userId);
        executorService.execute(() ->{
            User user = userDAO.getUserById(userId).getValue();
            if(user == null) user = userDAO.getUserByName(userName).getValue();
            if(user != null) userDAO.deleteUser(user);
            else{
                Log.e(getClass().getSimpleName(),"Unable to Fetch and Delete User : "+userId+" - "+userName);
            }
        });
    }

    public void deleteUser(User user) {
        Log.i(getClass().getSimpleName(), "delete :" + user.getUserId());
        executorService.execute(() -> {
            if (user != null) userDAO.deleteUser(user);
            else {
                Log.e(getClass().getSimpleName(), "Unable to Fetch and Delete User");
            }
        });
    }

    public void updateUser(User user) {
        Log.i(getClass().getSimpleName(),"Updated :"+user.getUserId());
        executorService.execute(() -> userDAO.updateUser(user));
    }
}

