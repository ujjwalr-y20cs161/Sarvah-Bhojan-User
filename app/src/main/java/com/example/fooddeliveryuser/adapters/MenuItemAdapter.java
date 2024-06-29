package com.example.fooddeliveryuser.adapters;

import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.models.CartItem;
import com.example.fooddeliveryuser.models.MenuItem;
import com.example.fooddeliveryuser.models.Restaurant;
import com.example.fooddeliveryuser.viewmodels.RestaurantCatalogueViewModel;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder> {

    private List<CartItem> menu;

    private Application application;
    private  MenuItemAdapter.OnItemUpdateListener listener;

    public  MenuItemAdapter(List<CartItem> menuItems, Application application,MenuItemAdapter.OnItemUpdateListener listener){
        this.menu = menuItems;
        this.application = application;
        this.listener = listener;
    }

    public interface OnItemUpdateListener {
        void onItemUpdated(CartItem updatedItem);
    }



    @NonNull
    @Override
    public MenuItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item_card,parent,false);
        return new MenuItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemViewHolder holder, int position) {
        CartItem menuItem = menu.get(position);
        try {
            Picasso.get().load(menuItem.getMenuItemImageURL()).into(holder.imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.menuItemLabel.setText(menuItem.getMenuItemName());
        holder.restaurantId.setText(menuItem.getRestaurantId());
        holder.itemPrice.setText(String.valueOf(menuItem.getPrice()));
        holder.itemCount.setText(String.valueOf(menuItem.getCount()));

        if(menuItem.getVeg()){
            holder.vegMarker.setImageDrawable(AppCompatResources.getDrawable(application.getApplicationContext(),R.drawable.veg_tri));
        }else holder.vegMarker.setImageDrawable(AppCompatResources.getDrawable(application.getApplicationContext(),R.drawable.non_veg));


        holder.add.setOnClickListener(view -> {
            menuItem.setCount(menuItem.getCount()+1);
            notifyItemChanged(position);
            if(listener!=null){
                listener.onItemUpdated(menuItem);
            }
        });
        holder.delete.setOnClickListener(view -> {
            if(menuItem.getCount() > 0){
                menuItem.setCount(menuItem.getCount()-1);
                notifyItemChanged(position);
                if(listener!=null){
                    listener.onItemUpdated(menuItem);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return menu!=null ? menu.size() : 0;
    }

    static class MenuItemViewHolder extends RecyclerView.ViewHolder{

        TextView menuItemLabel,restaurantId,itemPrice,itemCount;
        ShapeableImageView imageView, add,delete;
        ImageView vegMarker;
        public MenuItemViewHolder(@NonNull View itemView){
            super(itemView);
            menuItemLabel = itemView.findViewById(R.id.foodLabel);
            restaurantId = itemView.findViewById(R.id.resautantDetails);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            imageView = itemView.findViewById(R.id.itemImage);
            vegMarker = itemView.findViewById(R.id.vegMarker);
            add = itemView.findViewById(R.id.add);
            delete = itemView.findViewById(R.id.delete);
            itemCount = itemView.findViewById(R.id.counter);

        }
    }

}
