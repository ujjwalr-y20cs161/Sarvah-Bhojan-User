package com.example.fooddeliveryuser;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Drawable drawable = AppCompatResources.getDrawable(getContext(),R.drawable.address_marker);






        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.addressMarkerIcon.setImageDrawable(drawable);
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        shimmerFrameLayout = view.findViewById(R.id.shimmer_load);
        shimmerFrameLayout.startShimmer();

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