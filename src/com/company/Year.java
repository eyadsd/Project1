package com.company;

import java.util.List;

public class Year {
    int numberOfStudents;
    List<Subject> subjects ;
    int numberOfDivisions;
    int numberOfSections;
    Branch[] divisions;
    Section[] sections;
    // i will assume that the number of divisions and sections is given by the constructor but might change this later
    public Year(int numberOfStudents, List<Subject> subjects,int numberOfDivisions,int numberOfSections ){
        this.numberOfStudents = numberOfStudents;
        this.numberOfDivisions = numberOfDivisions;
        this.numberOfSections = numberOfSections;
        this.divisions = new Branch[numberOfDivisions];
        this.sections = new Section[numberOfSections];

    }
}
