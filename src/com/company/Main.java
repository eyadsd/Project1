package com.company;

import com.company.building.Building;
import de.vandermeer.asciitable.AsciiTable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        Constants.CLASSES_PER_day = 2;
        Constants.DAYS_PER_WEEK = 2;
        Constants.NUMBER_OF_LABS = 2;
        Constants.NUMBER_OF_THEATERS = 1;

        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("physics",ClassType.theoretical));
        subjects.add(new Subject("physics",ClassType.practical));
//        subjects.add(new Subject("Math",ClassType.theoretical));
//        subjects.add(new Subject("Math",ClassType.practical));
//        subjects.add(new Subject("English",ClassType.theoretical));
//        subjects.add(new Subject("English",ClassType.practical));
//        subjects.add(new Subject("Chemistry",ClassType.theoretical));
//        subjects.add(new Subject("Chemistry",ClassType.practical));



        ArrayList<Teacher> teachers = new ArrayList<>();


        Teacher.Availablity[][] availablities = { {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available} ,
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available}};


        ArrayList<Subject> subjects1= new ArrayList<>();
        subjects1.add(subjects.get(0));
        subjects1.add(subjects.get(1));
        // subjects1.add(subjects.get(2));
        //subjects1.add(subjects.get(3));




        teachers.add(new Teacher("steven",availablities,2,3,subjects1));
        teachers.add(new Teacher("Ben",availablities,2,3,subjects1));
        //teachers.add(new Teacher("walter",availablities,2,3,subjects1));



        Building building = new Building();

        Schedule schedule = new Schedule(building,100,subjects,teachers);

        ArrayList<Schedule> schedules = schedule.getPossibleNextMoves();







        AStar astar = new AStar();


        astar.Search(schedule);




    }

}
