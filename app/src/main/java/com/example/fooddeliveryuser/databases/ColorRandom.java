package com.example.fooddeliveryuser.databases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColorRandom {

    private static List<String> colorRandom = new ArrayList<>(Arrays.asList(new String[]{"#32DBC6", "#141E61", "#FFD31D", "#F21170", "#40A798", "#FF2442"}));

    public static String randomColor(){
        Random random = new Random();
        return colorRandom.get(random.nextInt(colorRandom.size()));
    }

    public static void addColor(String color){
        colorRandom.add(color);
    }


}
