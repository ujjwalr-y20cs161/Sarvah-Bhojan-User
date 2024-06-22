package com.example.fooddeliveryuser.adapters;

import android.content.Context;
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
    private OnItemClickListener onItemClickListener;

    public AddressPickerAdapter(List<Address> addressList,OnItemClickListener onItemClickListener) {
        this.addressList = addressList;
        this.onItemClickListener = onItemClickListener;
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
        Address address = addressList.get(position);
        holder.addressLabel.setText(address.getAddressLabel());
        holder.addressDetails.setText(address.getAddressLine() + ", " + address.getCityState() + ", " + address.getPincode());
        holder.addressImage.setBackgroundColor(Color.parseColor(ColorRandom.randomColor()));
        holder.editButton.setOnClickListener(v -> onItemClickListener.onEditClick(address));
    }

    @Override
    public int getItemCount() {
        if(addressList!=null) return addressList.size();
        return 0;
    }


    public void setAddressList(List<Address> addressList) {
            if(addressList != null) {
                this.addressList = addressList;
                notifyDataSetChanged();
            }
    }

    public interface OnItemClickListener {
        void onEditClick(Address address);
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        public TextView addressLabel;
        public TextView addressDetails;
        public ShapeableImageView addressImage;
        public Button editButton;

        public AddressViewHolder(View view) {
            super(view);
            addressLabel = view.findViewById(R.id.addressLabel);
            addressDetails = view.findViewById(R.id.addressDetails);
            addressImage = view.findViewById(R.id.addressImage);
            editButton = view.findViewById(R.id.edit);
        }
    }
}
