package com.example.fooddeliveryuser.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.dao.MenuItemDAO;
import com.example.fooddeliveryuser.databases.RoomDB;
import com.example.fooddeliveryuser.models.MenuItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuItemRepository {

    private MenuItemDAO menuItemDAO;
    private ExecutorService executor;

    private boolean isSet = false;

    public MenuItemRepository(Application application) {
        RoomDB db = RoomDB.getInstance(application);
        menuItemDAO = db.menuItemDAO();

        executor = Executors.newFixedThreadPool(2);

        if(!isSet){
            insertMenuItem(new MenuItem("rmi001","SBFDR101","Biriyani","https://glebekitchen.com/wp-content/uploads/2019/12/chickenbiryanibowltop.jpg",
                    false,300.0));
            insertMenuItem(new MenuItem("rmi002","SBFDR101","Double Ka meeta","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTh0-AhpCUb07B6aqSDaOZgbFDO3O0MQ8jz6A&s",
                    true,210.0));
            insertMenuItem(new MenuItem("rmi003","SBFDR103","Fry Piece Biriyani","https://glebekitchen.com/wp-content/uploads/2019/12/chickenbiryanibowltop.jpg",
                    false,300.0));
            insertMenuItem(new MenuItem("rmi004","SBFDR102","Gulab Jamun","https://c2.staticflickr.com/6/5559/30562381266_8994c463dd_z.jpg",
                    true,210.0));
        }
    }

    public void insertMenuItem(MenuItem menuItem){
        Log.i(getClass().getSimpleName(),"Insertion : "+menuItem.getMenuItemName());
        executor.execute(()->{
            menuItemDAO.insertMenuItem(menuItem);
        });
    }

    public void insertMenuItems(List<MenuItem> menuItems){
        Log.i(getClass().getSimpleName(),"Insertion : MenuItems");
        executor.execute(()->{
            menuItemDAO.insertMenuItems(menuItems);
        });
    }

    public LiveData<List<MenuItem>> getAllMenuItems(String resId){
        Log.i(getClass().getSimpleName(),"GetALL : MenuItems");
        return menuItemDAO.getAllMenuItems(resId);
    }

    public LiveData<List<MenuItem>> getVegMenuItems(String resId){
        Log.i(getClass().getSimpleName(),"GetVEG : MenuItems");
        return  menuItemDAO.getCategorizeItems(resId,true);
    }

    public LiveData<List<MenuItem>> getNonVegMenuItems(String resId){
        Log.i(getClass().getSimpleName(),"GetNON-VEG: MenuItems");
        return  menuItemDAO.getCategorizeItems(resId,false);
    }

}
