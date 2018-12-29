package com.company.building;

import com.company.Constants;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Building {


    int numberOfTheaters;
    int numberOfLabs;

    int theaterSize;
    int labSize;

    List<ClassRoom> labs;
    List<ClassRoom> theaters;

    public Building()
    {
        numberOfTheaters = Constants.NUMBER_OF_THEATERS;
        numberOfLabs = Constants.NUMBER_OF_LABS;
        theaterSize = Constants.THEATER_SIZE;
        labSize = Constants.LAB_SIZE;
        labs = new ArrayList<ClassRoom>();
        theaters = new ArrayList<ClassRoom>();
        for (int i = 1; i<=numberOfTheaters;i++)
        {

            theaters.add(new ClassRoom(i, ClassroomType.theater));
        }
        for (int i = 1; i<=numberOfLabs;i++)
        {
            labs.add(new ClassRoom(i,ClassroomType.lab));
        }


    }
    public Building(Building building){
        this.numberOfTheaters=building.numberOfTheaters;
        this.numberOfLabs=building.numberOfLabs;
        this.theaterSize=building.theaterSize;
        this.labSize=building.labSize;
        this.labs = new ArrayList<ClassRoom>();
        this.theaters = new ArrayList<ClassRoom>();
        for(ClassRoom classRoom:building.labs){
            this.labs.add(new ClassRoom(classRoom));
        }
        for(ClassRoom classRoom:building.theaters){
            this.theaters.add(new ClassRoom(classRoom));
        }



    }
    public ArrayList<ClassRoom> allClassRooms()
    {
        ArrayList<ClassRoom> all = new ArrayList<ClassRoom>();
        all.addAll(theaters);
        all.addAll(labs);
        return all;
    }

    public int getNumberOfTheaters() {
        return numberOfTheaters;
    }

    public int getNumberOfLabs() {
        return numberOfLabs;
    }

    public int getTheaterSize() {
        return theaterSize;
    }

    public int getLabSize() {
        return labSize;
    }



}
