package com.company.building;

import com.company.Constants;

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
            theaters.add(new ClassRoom(i));
        }
        for (int i = 1; i<=numberOfLabs;i++)
        {
            labs.add(new ClassRoom(i));
        }


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
