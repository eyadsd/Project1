package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


       StudentGroup branch = new Section(1,new ArrayList<Subject>());
       StudentGroup branch2 = new Section(1,new ArrayList<Subject>());


       if(branch.equals(branch2))
       {
           System.out.print("hello motherfucker");

       }

       else
       {
           System.out.print("iiiiiiiiiiiiiii");
       }


    }
}
