package com.example.fooddeliveryuser.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.adapters.CartAdapter;
import com.example.fooddeliveryuser.databinding.FragmentCartBinding;
import com.example.fooddeliveryuser.models.CartItem;
import com.example.fooddeliveryuser.viewmodels.CartFragmentViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartAdapter.OnItemUpdateListener {


    private CartFragmentViewModel viewModel;
    private FragmentCartBinding binding;

    private CartAdapter cartAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false);
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        viewModel = new ViewModelProvider(getActivity(),ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(CartFragmentViewModel.class);


        binding.clearCartButton.setOnClickListener(v->{
            new AlertDialog.Builder(getContext()).setTitle("Clear Cart").setMessage("Would You Like to clear?")
                    .setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            viewModel.clearCart();
                            binding.cartResult.setVisibility(View.GONE);
                        }
                    }).setNegativeButton("Cancel",null).show();
        });


        binding.billLoader.setVisibility(View.GONE);

        viewModel.getAllCartItems().observe(getViewLifecycleOwner(),cartItems -> {
            if(cartItems!=null && !cartItems.isEmpty()){
                Double price = 0.0;
                for(CartItem c: cartItems){
                    price+=c.getPrice()*c.getCount();
                }
                binding.priceLayout.setVisibility(View.VISIBLE);
                binding.cartResult.setLayoutManager(new LinearLayoutManager(getContext()));
                cartAdapter = new CartAdapter(cartItems,getActivity().getApplication(),this);
                binding.cartResult.setAdapter(cartAdapter);
                binding.orderTotalPrice.setText(String.valueOf(price));
                new Handler().postDelayed(()->{
                    binding.orderTotalLoad.setVisibility(View.GONE);
                    binding.orderTotalPrice.setVisibility(View.VISIBLE);
                    binding.cartLoad.setVisibility(View.GONE);
                    binding.cartResult.setVisibility(View.VISIBLE);
                },2000);
            }else{
                binding.cartLoad.setVisibility(View.GONE);
                binding.noItemsCartLayout.setVisibility(View.VISIBLE);
                binding.priceLayout.setVisibility(View.GONE);
            }
        });

        viewModel.getCartItemCount().observe(getViewLifecycleOwner(),count->{
            if(count <= 0){
                binding.cartResult.setAdapter(new CartAdapter());
                binding.cartResult.setVisibility(View.GONE);
                binding.clearCartButton.setEnabled(false);
            }else{
                binding.clearCartButton.setEnabled(true);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onItemUpdated(CartItem updatedItem) {
        if(updatedItem == null) return;
        // Handle the updated item here
    // For example, you can log it or update your data source
        Log.d(getClass().getSimpleName(), "Updated Item: " + updatedItem.getMenuItemName()+" - "+updatedItem.getCount());

        if (updatedItem.getCount() > 0) {
            viewModel.insertCartItem(updatedItem);
        } else {
            viewModel.deleteCartItem(updatedItem);
        }

    }

}