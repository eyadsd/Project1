package com.company;

import com.company.building.Building;
import com.company.building.ClassRoom;

import java.util.*;

public class Schedule {


    private class Lecture {
        Subject subject;
        Teacher teacher;
        StudentGroup division;
        ClassRoom classRoom;


        public Lecture(Subject subject, Teacher teacher, StudentGroup division, ClassRoom classRoom) {
            this.teacher = teacher;
            this.subject = subject;
            this.division = division;
            this.classRoom = classRoom;

        }

        public ClassRoom getClassRoom() {
            return classRoom;
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


        public boolean isConflictedTeacher(Teacher teacher) {

            if (this.teacher.equals(teacher)) {
                return true;
            }


            return false;

        }

        public boolean isConflictedStudents(StudentGroup studentGroup) {
            if(this.division.doesIntersect(studentGroup)){
                return true;
            }
            return false;
        }
        public boolean isConflictedClassroom(ClassRoom classRoom) {

            if(this.classRoom.equals(classRoom)){
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


    public Schedule(Building building, int numberOfStudents,ArrayList<Subject> subjects,Set<Teacher> teachers) {


        for(int i =0 ; i <daysPerWeek ; i++){


            for(int j=0 ; j<classesPerDay;j++){
                schedule[i][j] = new ArrayList<Lecture>();

            }
        }

        this.teachers = new HashSet<Teacher>(teachers);
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


    public Schedule(Schedule schedule){
        //TODO add deepcopying
    }

    public ArrayList<Schedule> getPossibleNextMoves(){

        for(int i=0 ; i <daysPerWeek; i++){

            for(int j=0 ; j <classesPerDay ;j++){

                for(Teacher teacher:teachers){
                    if(!canAddTeacher(teacher))

                    for()

                }


            }

        }



    }

    private boolean canAddTeacher(Teacher teacher, int period, int day) {
        ArrayList<Lecture> lectures = schedule[day][period];

        for (Lecture lect : lectures) {
            if (lect.isConflictedTeacher(teacher)) {

                return false;
            }

        }

    return true;
    }
}