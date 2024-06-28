package com.example.fooddeliveryuser.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryuser.databinding.FragmentAccountBinding;
import com.example.fooddeliveryuser.databinding.FragmentHomeBinding;
import com.example.fooddeliveryuser.services.Tokens;
import com.example.fooddeliveryuser.viewmodels.AccountFragmentViewModel;

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

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private AccountFragmentViewModel viewModel;

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
        sharedPreferences = getActivity().getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//         View Model init:

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()))
                .get(AccountFragmentViewModel.class);





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



        binding.LogoutCard.setOnClickListener(v->{
//            Invoke Dialog Box

//         TODO: Log out Logic, Clear database

            new AlertDialog.Builder(getContext())
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to Log out of this Account?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            editor.remove(Tokens.getLogged());
                            editor.remove(Tokens.getKeyUsername());
                            editor.remove(Tokens.getKeyPassword());
                            editor.apply();
                            startActivity(new Intent(getContext(), LoginActivity.class));
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();


        });

        binding.projectInfo.setOnClickListener(v-> {
//            URL loaded into browser
        });


        viewModel.getUser().observe(getViewLifecycleOwner(),user -> {
            if(user!=null){
                binding.UserName.setText(user.getUserName());
                binding.UserId.setText(user.getUserId());
                binding.phoneNumber.setText(user.getPhoneNumber());
                binding.emailDetails.setText(user.getEmailAddress());

                binding.deleteAccountCard.setOnClickListener(v->{
                    new AlertDialog.Builder(getContext())
                            .setTitle("Delete Account")
                            .setMessage("Are you sure you want to delete this Account?")
                            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {


                                    viewModel.deleteUser(user);

                                    editor.remove(Tokens.getLogged());
                                    editor.remove(Tokens.getKeyUsername());
                                    editor.remove(Tokens.getKeyPassword());
                                    editor.remove(Tokens.getKeyUserid());
                                    editor.apply();

                                    startActivity(new Intent(getContext(), LoginActivity.class));
                                    getActivity().finish();
                                }
                            })
                            .setNegativeButton("Don't Delete", null)
                            .show();

                });

            }
        });

        binding.customerSupportCard.setOnClickListener(v->{
            new AlertDialog.Builder(getContext())
                    .setTitle("Customer Support")
                    .setMessage("Would you like to call our Customer Support?")
                    .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:1234567890"));
                            startActivity(intent);

                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

        binding.projectInfo.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://github.com/ujjwalr-y20cs161/Sarvah-Bhojan-User.git"));
            startActivity(intent);
        });


        return binding.getRoot();
    }
}