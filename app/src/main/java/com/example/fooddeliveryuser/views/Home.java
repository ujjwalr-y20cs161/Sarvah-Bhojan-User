package com.example.fooddeliveryuser.views;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.services.Tokens;
import com.example.fooddeliveryuser.viewmodels.HomeFragmentViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryuser.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    FragmentHomeBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ShimmerFrameLayout shimmerFrameLayout;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private HomeFragmentViewModel viewModel;


    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        View Model initiations
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(HomeFragmentViewModel.class);



        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.address_marker);





        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.addressMarkerIcon.setImageDrawable(drawable);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_load);
        shimmerFrameLayout.startShimmer();

        binding.userGreet.setText("Hey "+sharedPreferences.getString(Tokens.getKeyUsername(),"User")+"!");

        binding.AdddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddressPicker.class));
            }
        });

        viewModel.getCurrentAddress().observe(getViewLifecycleOwner(),address -> {
                if(address != null){
                    binding.addressLabel.setText(address.getAddressLabel());
                    binding.addressDetails.setText(address.getAddressLine()+" "+address.getCityState());
                }
        });


        binding.popRestaurantLabel.setOnClickListener(v->{
            startActivity(new Intent(getContext(), RestaurantCatalogue.class));
        });

        binding.addressLabel.setOnClickListener(v->{
            startActivity(new Intent(getContext(),AddressPicker.class));
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.shimmerLoad.setVisibility(View.GONE);
                binding.results.setVisibility(View.VISIBLE);
            }
        },5000);


        return binding.getRoot();
    }
}