package com.example.fooddeliveryuser.databases;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.fooddeliveryuser.dao.AddressDAO;
import com.example.fooddeliveryuser.dao.UserDAO;
import com.example.fooddeliveryuser.models.Address;
import com.example.fooddeliveryuser.models.User;

@Database(entities = {Address.class, User.class},version = 1,exportSchema = false)
public  abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;

    private static String databaseName = "SBFD";

    public synchronized static RoomDB getInstance(Context context){
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class,databaseName).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        Log.i("Room DB","Room DB instance running");
        return database;
    }


    public abstract AddressDAO addressDAO();
    public abstract UserDAO userDAO();

}
