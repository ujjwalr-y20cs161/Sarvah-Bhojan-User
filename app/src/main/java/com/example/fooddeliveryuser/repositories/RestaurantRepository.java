package com.example.fooddeliveryuser.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.dao.RestaurantDAO;
import com.example.fooddeliveryuser.databases.RoomDB;
import com.example.fooddeliveryuser.models.Restaurant;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestaurantRepository {
    private RestaurantDAO restaurantDAO;
    private ExecutorService executorService;

    private static boolean alreadyInsert = false;

    public RestaurantRepository(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        restaurantDAO = db.restaurantDAO();
        executorService = Executors.newFixedThreadPool(2);
//        (String restaurantName, String addressLine, Double latitude, Double longtitude, String cityState, String pincode, String status, Double rating, String cusines)
        if(!alreadyInsert){
            insertRestaurant(new Restaurant("Delhi Highway","Beside National Highway",
                    0.0,0.0,"Ongole,AP","523001","Open",4.3,"North Indian"));
            insertRestaurant(new Restaurant("Zebu Robo","Trunk Road",
                    0.0,0.0,"Ongole,AP","523001","Open",3.2,"Chinese"));
            insertRestaurant(new Restaurant("Punjab Dhaba","Opposite National Highway ",
                    0.0,0.0,"Ongole,AP","523001","Open",3.7,"Punjabi"));
            insertRestaurant(new Restaurant("KB East","Kurnool Road",
                    0.0,0.0,"Ongole,AP","523001","Open",4.8,"Italian"));
            insertRestaurant(new Restaurant("Ramya Fast Foods","Kurnool Road",
                    0.0,0.0,"Ongole,AP","523001","Open",4.1,"Andhra"));
            insertRestaurant(new Restaurant("Dolphin Bar","Kurnool Road",
                    0.0,0.0,"Ongole,AP","523001","Open",3.9,"Tandoor"));
            alreadyInsert = true;
        }
    }

    public void insertRestaurant(Restaurant restaurant) {
        Log.i(getClass().getSimpleName(),"Insertion : "+restaurant.getRestaurantId()+" - "+restaurant.getRestaurantName());
        executorService.execute(() -> restaurantDAO.insertRestaurant(restaurant));
    }

    public void insertAllRestaurants(List<Restaurant> restaurants) {
        executorService.execute(() -> restaurantDAO.insertAllRestaurant(restaurants));
    }

    public void updateRestaurant(Restaurant restaurant) {
        Log.i(getClass().getSimpleName(),"Updation : "+restaurant.getRestaurantId()+" - "+restaurant.getRestaurantName());
        executorService.execute(() -> restaurantDAO.updateRestaurant(restaurant));
    }

    public void deleteRestaurant(Restaurant restaurant) {
        Log.i(getClass().getSimpleName(),"Deletion : "+restaurant.getRestaurantId()+" - "+restaurant.getRestaurantName());
        executorService.execute(() -> restaurantDAO.deleteRestaurant(restaurant));
    }

    public LiveData<Restaurant> getRestaurantById(String id) {
        Log.i(getClass().getSimpleName(),"getResById : "+id);
        return restaurantDAO.getRestaurantById(id);
    }

    public LiveData<List<Restaurant>> getNearbyRestaurants(String pin) {
        Log.i(getClass().getSimpleName(),"getResByPin : "+pin);
        return restaurantDAO.getNearByRestaurant(pin);
    }

    public LiveData<List<Restaurant>> getRestaurantsByName(String resName) {
        Log.i(getClass().getSimpleName(),"getResByName : "+resName);
        return restaurantDAO.getRestaurantByName("%"+resName+"%");
    }

    public LiveData<List<Restaurant>> getNearbyRestaurantsByRate(String pin, Double rate) {
        Log.i(getClass().getSimpleName(),"getResByPin&Rate : "+pin+" -  "+rate);
        return restaurantDAO.getNearByRestaurantsByRate(pin, rate);
//        return restaurantDAO.getAllRestaurant();
    }

    public LiveData<List<Restaurant>> getOpenedRestaurants(String state) {
        Log.i(getClass().getSimpleName(),"getOpened :");
        return restaurantDAO.getOpenedRestaurants("open");
    }

}
