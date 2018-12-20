package com.company;

import java.util.List;

public class Teacher {
    String name;
    List<Subject> subjects;
    int classesPerDay;
    int daysPerWeek;
    int maximumNumberOfLectures;
    int currentNumberOflectures; // number of lectures for this teacher per week
    int maximumNumberOfDays;
    int currentNumberOfDays;
    boolean [] occupiedDays; // the days that this teacher gives lectures in
    boolean[][] available;

    public Teacher(boolean[][] available,int maximumNumberOfDays,int maximumNumberOfLectures){
        currentNumberOflectures = 0;
        currentNumberOfDays = 0;
        this.maximumNumberOfDays = maximumNumberOfDays;
        this.maximumNumberOfLectures = maximumNumberOfLectures;
        this.available = new boolean[daysPerWeek][classesPerDay];
        for (int i = 0;i < daysPerWeek;i++)
        {
            for ( int j = 0; j<classesPerDay;j++)
            {
                this.available[i][j] = available[i][j];
            }
        }
        occupiedDays = new boolean[daysPerWeek];
    }
    public boolean assignToPeriod(int dayNumber , int periodNumber){
        if(available[dayNumber][periodNumber] == false)
        {
            if(occupiedDays[dayNumber] == false)
            {
                currentNumberOfDays++;
                occupiedDays[dayNumber] = true;

            }
            currentNumberOflectures++;
            return true;
        }
        return false;
    }
}
