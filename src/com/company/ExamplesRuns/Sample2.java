package com.company.ExamplesRuns;

import com.company.*;
import com.company.building.Building;

import java.util.ArrayList;

public class Sample2 {
    public static void main(String args[])
    {
        Constants.CLASSES_PER_day = 4;
        Constants.DAYS_PER_WEEK = 5;
        Constants.NUMBER_OF_LABS = 4;
        Constants.NUMBER_OF_THEATERS = 2;

        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("physics", ClassType.theoretical));
        subjects.add(new Subject("physics",ClassType.practical));
        subjects.add(new Subject("Math",ClassType.theoretical));
        subjects.add(new Subject("Math",ClassType.practical));
        subjects.add(new Subject("English",ClassType.theoretical));
        subjects.add(new Subject("English",ClassType.practical));
        //subjects.add(new Subject("Chemistry",ClassType.theoretical));
        //subjects.add(new Subject("Chemistry",ClassType.practical));



        ArrayList<Teacher> teachers = new ArrayList<>();


        Teacher.Availablity[][] availablities = { {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available} ,
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available},
                {Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available,Teacher.Availablity.available}};


        ArrayList<Subject> subjects1= new ArrayList<>();
        subjects1.add(subjects.get(0));
        subjects1.add(subjects.get(1));
        subjects1.add(subjects.get(2));
        subjects1.add(subjects.get(3));
        subjects1.add(subjects.get(4));
        subjects1.add(subjects.get(5));
        //subjects1.add(subjects.get(6));
        //subjects1.add(subjects.get(7));






        teachers.add(new Teacher("steven",availablities,2,3,subjects1));
        teachers.add(new Teacher("Ben",availablities,2,3,subjects1));
        teachers.add(new Teacher("walter",availablities,2,3,subjects1));



        Building building = new Building();

        Schedule schedule = new Schedule(building,100,subjects,teachers);

        ArrayList<Schedule> schedules = schedule.getPossibleNextMoves();

        AStar astar = new AStar();


        astar.Search(schedule);

    }
}
