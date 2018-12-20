package com.company;

import java.util.List;

public class Schedule {
    int classesPerDay = 4; // number of classes per day
    int daysPerWeek = 5; // number of school days per week
    Building building;
    List<Year> years;
    Period[][] schedule = new Period[daysPerWeek][classesPerDay];
    public Schedule(){

    }
}
