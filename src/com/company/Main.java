package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        ArrayList<String>[][] list= new ArrayList[2][2];

        for(int i =0 ; i <2 ; i++){


            for(int j=0 ; j<2;j++){
                list[i][j] = new ArrayList<String>();

            }
        }
        ArrayList<String> fuckyou = new ArrayList<String>();
        list[0][0] = fuckyou;
        list[0][0].add("fuckyou");
        list[0][0].add("fuckyou");
        list[0][0].add("fuckyou");
        list[0][0].add("fuckyou");


        System.out.println(list[0][0]);



    }
}
