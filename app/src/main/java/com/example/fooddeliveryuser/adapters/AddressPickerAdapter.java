package com.example.fooddeliveryuser.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.databases.ColorRandom;
import com.example.fooddeliveryuser.models.Address;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class AddressPickerAdapter extends RecyclerView.Adapter<AddressPickerAdapter.AddressViewHolder> {

    private List<Address> addressList;
    private OnItemClickListener listener;

    public AddressPickerAdapter(List<Address> addressList) {
        this.addressList = addressList;
    }

    public interface OnItemClickListener {
        void onEditClick(Address address);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_card, parent, false);
        return new AddressViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address currentAddress = addressList.get(position);
        holder.addressLabel.setText(currentAddress.getAddressLabel());
        holder.addressDetails.setText(currentAddress.getAddressLine() + ", " + currentAddress.getCityState() + " - " + currentAddress.getPincode());
        try {
            holder.addressImage.setBackgroundColor(Color.parseColor(currentAddress.getColor()));
        }catch (Exception e){
            e.printStackTrace();
        }

        // Set up the edit button click listener
        holder.editButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(currentAddress);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList != null ? addressList.size() : 0;
    }

    static class AddressViewHolder extends RecyclerView.ViewHolder {
        private TextView addressLabel;
        private TextView addressDetails;
        private Button editButton;
        private ShapeableImageView addressImage;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            addressLabel = itemView.findViewById(R.id.addressLabel);
            addressDetails = itemView.findViewById(R.id.addressDetails);
            editButton = itemView.findViewById(R.id.edit);
            addressImage = itemView.findViewById(R.id.addressImage);
        }
    }
}
