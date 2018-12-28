package com.company;

import com.company.building.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Schedule {


    private class Lecture {
        Subject subject;
        Teacher teacher;
        StudentGroup division;


        public Lecture(Subject subject, Teacher teacher, StudentGroup division) {
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


        public StudentGroup getDivision() {
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


    int classesPerDay = Constants.CLASSES_PER_day; // number of classes per day
    int daysPerWeek = Constants.DAYS_PER_WEEK; // number of school days per week

    int numberOfStudents;
    int numberOfBranches;
    int numberOfSections;

    ArrayList<Subject> subjects;

    ArrayList<Branch> branches;

    Building building;
    Set<Teacher> teachers;
    ArrayList<Lecture>[][] schedule = new ArrayList[daysPerWeek][classesPerDay];


    public Schedule(Building building, int numberOfStudents,ArrayList<Subject> subjects) {
        this.subjects = subjects;
        this.building = building;
        this.numberOfStudents = numberOfStudents;
        this.numberOfBranches = (int)Math.ceil(numberOfStudents/building.getTheaterSize());
        this.numberOfSections = (int)Math.ceil(numberOfStudents/building.getLabSize()); //temporary variable
        int numberOfSectionsInBranch = (int)Math.ceil(numberOfSections/numberOfBranches);
        this.numberOfSections = numberOfSectionsInBranch * numberOfBranches;//real number of sections

        for(int i=1 ; i <=this.numberOfBranches ; i++){
            ArrayList<Section> sections = new ArrayList<Section>();

            for(int j = 1; j <= numberOfSectionsInBranch; j++)
            {
                sections.add(new Section(i,subjects));
            }
            branches.add(new Branch(i,subjects,sections));
        }

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