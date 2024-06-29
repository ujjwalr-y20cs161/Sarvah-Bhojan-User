package com.example.fooddeliveryuser.adapters;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.models.CartItem;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cart;

    private Application application;
    private  CartAdapter.OnItemUpdateListener listener;

    public interface OnItemUpdateListener {
        void onItemUpdated(CartItem updatedItem);
    }
    public CartAdapter() {
    }

    public CartAdapter(List<CartItem> menu, Application application, OnItemUpdateListener listener) {
        this.cart = menu;
        this.application = application;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_cart,parent,false);
        return new CartAdapter.CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cart.get(position);
        try {
            Picasso.get().load(cartItem.getMenuItemImageURL()).into(holder.imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.menuItemLabel.setText(cartItem.getMenuItemName());
        holder.restaurantId.setText(cartItem.getRestaurantId());
        holder.itemPrice.setText(String.valueOf(cartItem.getPrice()*cartItem.getCount()));
        holder.itemCount.setText(String.valueOf(cartItem.getCount()));

        if(cartItem.getVeg()){
            holder.vegMarker.setImageDrawable(AppCompatResources.getDrawable(application.getApplicationContext(),R.drawable.veg_tri));
        }else holder.vegMarker.setImageDrawable(AppCompatResources.getDrawable(application.getApplicationContext(),R.drawable.non_veg));


        holder.add.setOnClickListener(view -> {
            cartItem.setCount(cartItem.getCount()+1);
            notifyItemChanged(position);
            if(listener!=null){
                listener.onItemUpdated(cartItem);
            }
        });
        holder.delete.setOnClickListener(view -> {
            if(cartItem.getCount() > 0){
                cartItem.setCount(cartItem.getCount()-1);
                notifyItemChanged(position);
                if(listener!=null){
                    listener.onItemUpdated(cartItem);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return cart!=null ? cart.size() : 0;
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView menuItemLabel,restaurantId,itemPrice,itemCount;
        ShapeableImageView imageView, add,delete;
        ImageView vegMarker;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemLabel = itemView.findViewById(R.id.foodLabel);
            restaurantId = itemView.findViewById(R.id.resautantDetails);
            itemPrice = itemView.findViewById(R.id.totalPrice);
            imageView = itemView.findViewById(R.id.itemImage);
            vegMarker = itemView.findViewById(R.id.vegMarker);
            add = itemView.findViewById(R.id.addMore);
            delete = itemView.findViewById(R.id.subtractMore);
            itemCount = itemView.findViewById(R.id.itemCount);
        }
    }
}
