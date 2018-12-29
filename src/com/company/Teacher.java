package com.company;

import java.util.List;

public class Teacher {
    public enum Availablity{
        unavailable,
        available,
        preffered
    }
    String name;
    List<Subject> subjects;
    int classesPerDay;
    int daysPerWeek;
    int maximumNumberOfLectures;
    int currentNumberOflectures; // number of lectures for this teacher per week
    int maximumNumberOfDays;
    int currentNumberOfDays;
    boolean [] occupiedDays; // the days that this teacher gives lectures in
    Availablity[][] availablity;
    boolean[][] occupation; //which periods is the teacher assigned to

    public Teacher(Availablity[][] availabilty,int maximumNumberOfDays,int maximumNumberOfLectures){
        currentNumberOflectures = 0;
        currentNumberOfDays = 0;
        this.maximumNumberOfDays = maximumNumberOfDays;
        this.maximumNumberOfLectures = maximumNumberOfLectures;
        this.availablity = new Availablity[daysPerWeek][classesPerDay];
        for (int i = 0;i < daysPerWeek;i++)
        {
            for ( int j = 0; j<classesPerDay;j++)
            {
                this.availablity[i][j] = availabilty[i][j];
            }
        }
        occupiedDays = new boolean[daysPerWeek];
        occupation = new boolean[daysPerWeek][classesPerDay];
    }



    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        Teacher teacher = (Teacher)obj;
        if(teacher.getName().equals(this.name)){
            return true;
        }
        return false;
    }
    public boolean isAvailable(int day, int period)
    {
        if(this.availablity[day][period] == Availablity.unavailable)
            return false;
        return true;
    }
    public boolean teachesSubject(Subject subject)
    {
        return subjects.contains(subject);
    }
    public void assignToPeriod(int dayNumber , int periodNumber){
        if(occupiedDays[dayNumber] == false)
         {
             currentNumberOfDays++;
             occupiedDays[dayNumber] = true;

         }
         occupation[dayNumber][periodNumber] = true;
         currentNumberOflectures++;

        }
    public boolean lecturesExceeded()
    {
        if(currentNumberOflectures >=maximumNumberOfLectures)
            return true;
        return false;
    }
    public int getCurrentNumberOfDays()
    {
        return this.currentNumberOfDays;
    }
    public boolean daysExceeded()
    {
        if (currentNumberOfDays > maximumNumberOfDays)
            return true;
        return false;
    }
}
