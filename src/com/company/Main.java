package com.company;

import com.company.building.Building;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {



    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("physics",ClassType.theoretical));
        subjects.add(new Subject("physics",ClassType.practical));
        subjects.add(new Subject("Math",ClassType.theoretical));
        subjects.add(new Subject("Math",ClassType.practical));
        subjects.add(new Subject("English",ClassType.theoretical));
        subjects.add(new Subject("English",ClassType.practical));
        subjects.add(new Subject("Chemistry",ClassType.theoretical));
        subjects.add(new Subject("Chemistry",ClassType.practical));



        ArrayList<Teacher> teachers = new ArrayList<>();


        Teacher.Availablity[][] availablities = { {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available} ,
        {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available}};


        ArrayList<Subject> subjects1= new ArrayList<>();
        subjects1.add(subjects.get(0));
        teachers.add(new Teacher("steven",availablities,3,6,subjects1));


        Building building = new Building();

        Schedule schedule = new Schedule(building,400,subjects,teachers);

        ArrayList<Schedule> schedules = schedule.getPossibleNextMoves();

        System.out.print(schedules.get(0));
        System.out.print(schedules.get(1));
        System.out.print(schedules.get(2));

      /*  ArrayList<Schedule> schedules2= schedules.get(0).getPossibleNextMoves();

        System.out.print(schedules2.size());*/







    }
}
