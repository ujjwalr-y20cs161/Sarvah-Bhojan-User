package com.example.fooddeliveryuser.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.models.Restaurant;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private RestaurantAdapter.OnItemClickListener listener;

    public RestaurantAdapter(List<Restaurant> resList) {
        this.restaurants = resList;
    }

    public interface OnItemClickListener {
        void onEditClick(Restaurant restaurant);
    }

    public void setOnItemClickListener(RestaurantAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.RestaurantViewHolder holder, int position) {
        Restaurant currentRestaurant = restaurants.get(position);
        holder.RestaurantName.setText(currentRestaurant.getRestaurantName());
        holder.RestaurantAddress.setText(currentRestaurant.getAddressLine() + "," + currentRestaurant.getCityState());
        holder.RestaurantDistance.setText(currentRestaurant.getCusine());
        holder.RestaurantRate.setText(String.valueOf(currentRestaurant.getRating()));
        try {
            Picasso.get().load(currentRestaurant.getImageURL()).into(holder.RestaurantImage);
        }catch (Exception e){
            e.printStackTrace();
        }

        // Set up the edit button click listener
        holder.RestaurantCard.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(currentRestaurant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private TextView RestaurantName;
        private TextView RestaurantAddress;

        private TextView RestaurantDistance, RestaurantRate;
        private LinearLayout RestaurantCard;
        private ShapeableImageView RestaurantImage;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            RestaurantName = itemView.findViewById(R.id.restaurantName);
            RestaurantAddress = itemView.findViewById(R.id.restaurantAddress);
            RestaurantCard = itemView.findViewById(R.id.resCard);
            RestaurantImage = itemView.findViewById(R.id.restaurantImage);
            RestaurantDistance = itemView.findViewById(R.id.restaurantDistance);
            RestaurantRate = itemView.findViewById(R.id.restaurantRating);

        }
    }
}
