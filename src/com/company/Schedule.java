package com.company;

import java.util.List;
import java.util.Set;

public class Schedule {
    int classesPerDay = 4; // number of classes per day
    int daysPerWeek = 5; // number of school days per week

    int numberOfStudents;
    int numberOfBranches;
    int numberOfSections;

    List<Subject> subjects ;

    Branch[] Branches;
    Section[] sections;

    Building building;
    Set<Teacher> teachers;
    Period[][] schedule = new Period[daysPerWeek][classesPerDay];


    public Schedule(){
        this.numberOfStudents = numberOfStudents;
        this.numberOfBranches = numberOfBranches;
        this.numberOfSections = numberOfSections;
        this.Branches = new Branch[numberOfBranches];
        this.sections = new Section[numberOfSections];

    }
}
