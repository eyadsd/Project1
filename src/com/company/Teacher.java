package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {
    public enum Availablity{
        unavailable,
        available,
        preffered;


    }
    String name;
    List<Subject> subjects;
    int classesPerDay = Constants.CLASSES_PER_day;
    int daysPerWeek=Constants.DAYS_PER_WEEK;
    int maximumNumberOfLectures;
    int currentNumberOflectures; // number of lectures for this teacher per week
    int maximumNumberOfDays;
    int currentNumberOfDays;
    boolean [] occupiedDays; // the days that this teacher gives lectures in
    Availablity[][] availablity;
    boolean[][] occupation; //which periods is the teacher assigned to

    public Teacher(String name, Availablity[][] availabilty, int maximumNumberOfDays, int maximumNumberOfLectures, ArrayList<Subject> subjects){
        this.name =name;
        this.subjects = subjects;
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
    public Teacher(Teacher teacher){

        this.name=teacher.name;
        this.currentNumberOfDays=teacher.currentNumberOfDays;
        this.currentNumberOflectures=teacher.currentNumberOflectures;
        this.maximumNumberOfDays=teacher.maximumNumberOfDays;
        this.maximumNumberOfLectures=teacher.maximumNumberOfLectures;
        this.subjects=new ArrayList<Subject>();
        for(Subject subject: teacher.subjects){
            this.subjects.add(new Subject(subject));
        }
        this.availablity = new Availablity[daysPerWeek][classesPerDay];
        for (int i = 0;i < daysPerWeek;i++)
        {
            for ( int j = 0; j<classesPerDay;j++)
            {
                this.availablity[i][j] = teacher.availablity[i][j];
            }
        }
        this.occupiedDays = new boolean[daysPerWeek];
        for(int i =0; i <daysPerWeek; i++){

            this.occupiedDays[i]=teacher.occupiedDays[i];
        }
    }



    public String getName() {
        return name;
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

    @Override
    public boolean equals(Object obj) {
        Teacher teacher = (Teacher)obj;
        if(teacher.getName().equals(this.name)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", currentNumberOflectures=" + currentNumberOflectures +
                ", currentNumberOfDays=" + currentNumberOfDays +
                '}';
    }
}
