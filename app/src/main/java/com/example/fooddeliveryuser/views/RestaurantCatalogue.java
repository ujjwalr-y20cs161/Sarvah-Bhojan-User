package com.example.fooddeliveryuser.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.adapters.MenuItemAdapter;
import com.example.fooddeliveryuser.databinding.ActivityRestaurantCatalogueBinding;
import com.example.fooddeliveryuser.models.CartItem;
import com.example.fooddeliveryuser.models.MenuItem;
import com.example.fooddeliveryuser.models.Restaurant;
import com.example.fooddeliveryuser.viewmodels.RestaurantCatalogueViewModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantCatalogue extends AppCompatActivity implements MenuItemAdapter.OnItemUpdateListener {

    private ActivityRestaurantCatalogueBinding binding;
    private RestaurantCatalogueViewModel viewModel;


    private MenuItemAdapter menuItemAdapter;

    private static String lastRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantCatalogueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(RestaurantCatalogueViewModel.class);

        binding.goCart.setVisibility(View.GONE);

        if(getIntent().hasExtra("restaurantObj")) {
            viewModel.setRestaurant((Restaurant) getIntent().getSerializableExtra("restaurantObj"));

            binding.restaurantName.setText(viewModel.getRestaurant().getRestaurantName());
            binding.restaurantId.setText(viewModel.getRestaurant().getRestaurantId());
            binding.rating.setText(String.valueOf(viewModel.getRestaurant().getRating()));
            binding.restaurantAddress.setText(viewModel.getRestaurant().getAddressLine() + "," + viewModel.getRestaurant().getCityState());
            binding.restaurantStatus.setText(viewModel.getRestaurant().getStatus());
            binding.restaurantCuisine.setText(viewModel.getRestaurant().getCusine());
            binding.BackButton.setOnClickListener(v->getOnBackPressedDispatcher().onBackPressed());


            binding.menuResult.setLayoutManager(new LinearLayoutManager(this));


            binding.vegSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton selectedRadioButton = findViewById(checkedId);
                    if (selectedRadioButton != null) {
                        String selectedText = selectedRadioButton.getText().toString();
                        switch (selectedText){
                            case "Vegetarian": vegItems();
                            break;
                            case "Non Vegetarian": nonVegItems();
                            break;
                            default:allItems();
                        }
                        // You can perform additional actions here based on the selection
                    }
                }
            });
            allItems();

            viewModel.getCartItemCount().observe(this,count->{
                if(count > 0){
                    binding.goCart.setVisibility(View.VISIBLE);
                    binding.goCartButton.setOnClickListener(v->{
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("fragParameter","Cart");
                        startActivity(intent);
                    });
                }else{
                    binding.goCart.setVisibility(View.GONE);
                }
            });

            viewModel.getLastCartItem().observe(this,cartItem -> {
                if(cartItem!= null){
                    lastRestaurant = cartItem.getRestaurantId();
                }
            });


        }else{
            finish();
        }

    }

    @Override
    public void onItemUpdated(CartItem updatedItem) {
        if(updatedItem == null) return;
        // Handle the updated item here
        // For example, you can log it or update your data source
        Log.d(getClass().getSimpleName(), "Updated Item: " + updatedItem.getMenuItemName()+" - "+updatedItem.getCount());

        if(lastRestaurant!=null) {
            if (lastRestaurant.equals(updatedItem.getRestaurantId())) {
                if (updatedItem.getCount() > 0) {
                    viewModel.insertCartItem(updatedItem);
                } else {
                    viewModel.deleteCartItem(updatedItem);
                }
            } else {
                new AlertDialog.Builder(this) // Use 'this' instead of 'getApplicationContext()'
                        .setTitle("Clear Cart")
                        .setMessage("Clear Cart to add new Items")
                        .setPositiveButton("Clear", (dialog, which) -> {
                            viewModel.clearCart();
                            if (updatedItem.getCount() > 0) {
                                viewModel.insertCartItem(updatedItem);
                            } else {
                                viewModel.deleteCartItem(updatedItem);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        }else{
            if (updatedItem.getCount() > 0) {
                viewModel.insertCartItem(updatedItem);
            } else {
                viewModel.deleteCartItem(updatedItem);
            }
        }
    }

    public void allItems(){
        viewModel.getAllItems().observe(this,menuItems -> {
            List<CartItem> c = new ArrayList<>();
            for(MenuItem m: menuItems){
                c.add(new CartItem(m));
            }
            menuItemAdapter = new MenuItemAdapter(c,getApplication(),this);
            binding.menuResult.setAdapter(menuItemAdapter);
            binding.menuResult.setMinimumHeight(500);
            new Handler().postDelayed(()->{
                binding.foodCatalogueLoad.setVisibility(View.GONE);
                binding.menuResult.setVisibility(View.VISIBLE);
            },2000);

        });
    }

    public void vegItems(){
        viewModel.getVegItems().observe(this,menuItems -> {
            List<CartItem> c = new ArrayList<>();
            for(MenuItem m: menuItems){
                c.add(new CartItem(m));
            }
            menuItemAdapter = new MenuItemAdapter(c,getApplication(),this);
            binding.menuResult.setAdapter(menuItemAdapter);
            binding.menuResult.setMinimumHeight(500);
            new Handler().postDelayed(()->{
                binding.foodCatalogueLoad.setVisibility(View.GONE);
                binding.menuResult.setVisibility(View.VISIBLE);
            },2000);

        });
    }
    public void nonVegItems(){
        viewModel.getNonVegItems().observe(this,menuItems -> {
            List<CartItem> c = new ArrayList<>();
            for(MenuItem m: menuItems){
                c.add(new CartItem(m));
            }
            menuItemAdapter = new MenuItemAdapter(c,getApplication(),this);
            binding.menuResult.setAdapter(menuItemAdapter);
            binding.menuResult.setMinimumHeight(500);
            new Handler().postDelayed(()->{
                binding.foodCatalogueLoad.setVisibility(View.GONE);
                binding.menuResult.setVisibility(View.VISIBLE);
            },2000);

        });
    }


}