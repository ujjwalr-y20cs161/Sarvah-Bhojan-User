package com.example.fooddeliveryuser.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryuser.databinding.FragmentAccountBinding;
import com.example.fooddeliveryuser.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentAccountBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account.
     */
    // TODO: Rename and change types and number of parameters
    public static Account newInstance(String param1, String param2) {
        Account fragment = new Account();
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
        binding = FragmentAccountBinding.inflate(inflater,container,false);

        binding.editInfo.setOnClickListener(v->{
            startActivity(new Intent(getContext(), UserInfoScreen.class));
        });

        binding.paymentMethodCard.setOnClickListener(v->{
            startActivity(new Intent(getContext(), PaymentMethod.class));
        });

        binding.addressCard.setOnClickListener(v->{
            startActivity(new Intent(getContext(), AddressPicker.class));
        });

        binding.deleteAccountCard.setOnClickListener(v->{

        });

        binding.LogoutCard.setOnClickListener(v->{
//            Invoke Dialog Box

//         TODO: Log out Logic, Clear database


            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        });

        binding.projectInfo.setOnClickListener(v-> {
//            URL loaded into browser
        });

        return binding.getRoot();
    }
}