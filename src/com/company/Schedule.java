package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Schedule {


    private class Lecture {
        Subject subject;
        Teacher teacher;
        Students division;


        public Lecture(Subject subject, Teacher teacher, Students division) {
            this.teacher = teacher;
            this.subject = subject;
            this.division = division;

        }

        public Subject getSubject() {
            return subject;
        }


        public Teacher getTeacher() {
            return teacher;
        }


        public Students getDivision() {
            return division;
        }


        public boolean isConflicted(Lecture lecture) {

            if (this.teacher.equals(lecture.getTeacher())) {
                return true;
            }
            if (this.division.doesIntersect(lecture.getDivision())) {
                return true;
            }

            return false;

        }
    }


    int classesPerDay = 4; // number of classes per day
    int daysPerWeek = 5; // number of school days per week

    int numberOfStudents;
    int numberOfBranches;
    int numberOfSections;

    List<Subject> subjects;

    Branch[] Branches;
    Section[] sections;

    Building building;
    Set<Teacher> teachers;
    ArrayList<Lecture>[][] schedule = new ArrayList[daysPerWeek][classesPerDay];

    public Schedule() {
        this.numberOfStudents = numberOfStudents;
        this.numberOfBranches = numberOfBranches;
        this.numberOfSections = numberOfSections;
        this.Branches = new Branch[numberOfBranches];
        this.sections = new Section[numberOfSections];

    }

    private boolean isFeasible(Lecture lecture, int period, int day) {
        ArrayList<Lecture> lectures = schedule[day][period];

        for (Lecture lect : lectures) {
            if (lect.isConflicted(lecture)) {

                return false;
            }

        }

    return true;
    }
}