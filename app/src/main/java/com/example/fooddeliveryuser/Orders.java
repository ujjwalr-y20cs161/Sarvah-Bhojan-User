package com.example.fooddeliveryuser;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import com.example.fooddeliveryuser.databinding.FragmentOrdersBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Orders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Orders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentOrdersBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final static int PLACE_PICKER_REQUEST = 999;

    public Orders() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static Orders newInstance(String param1, String param2) {
        Orders fragment = new Orders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize AutocompleteSupportFragment

// Set the type of place data to return


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(inflater,container,false);
        // Initialize and set up AutocompleteSupportFragment

        binding.previousOrderText.setOnClickListener(v->{
            startActivity(new Intent(getContext(), Rating.class));
        });

        return binding.getRoot();
    }
}