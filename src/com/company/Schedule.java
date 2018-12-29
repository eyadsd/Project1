package com.company;

import com.company.building.Building;
import com.company.building.ClassRoom;
import com.company.building.ClassroomType;

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
        public ClassType getSubjectType()
        {
            return subject.getType();
        }

    }

    int classesPerDay = Constants.CLASSES_PER_day; // number of classes per day
    int daysPerWeek = Constants.DAYS_PER_WEEK; // number of school days per week

    int numberOfStudents;
    int numberOfBranches;
    int numberOfSections;

    ArrayList<Subject> subjects;
    ArrayList<StudentGroup> studentGroups;
    ArrayList<Branch> branches;

    Building building;
    ArrayList<Teacher> teachers;
    ArrayList<Lecture>[][] schedule = new ArrayList[daysPerWeek][classesPerDay];


    public Schedule(Building building, int numberOfStudents,ArrayList<Subject> subjects,ArrayList<Teacher> teachers) {


        for(int i =0 ; i <daysPerWeek ; i++){


            for(int j=0 ; j<classesPerDay;j++){
                schedule[i][j] = new ArrayList<Lecture>();

            }
        }

        this.teachers = teachers;
        this.subjects = subjects;
        this.building = building;
        this.numberOfStudents = numberOfStudents;
        this.numberOfBranches = (int)Math.ceil(numberOfStudents/building.getTheaterSize());
        this.numberOfSections = (int)Math.ceil(numberOfStudents/building.getLabSize()); //temporary variable
        int numberOfSectionsInBranch = (int)Math.ceil(numberOfSections/numberOfBranches);
        this.numberOfSections = numberOfSectionsInBranch * numberOfBranches;//real number of sections
        this.studentGroups = new ArrayList<StudentGroup>();

        for(int i=1 ; i <=this.numberOfBranches ; i++){
            ArrayList<Section> sections = new ArrayList<Section>();
            for(int j = 1; j <= numberOfSectionsInBranch; j++)
            {
                Section section = new Section(i,subjects);
                sections.add(section);
                studentGroups.add(section);
            }
            Branch branch = new Branch(i,subjects,sections);
            studentGroups.add(branch);
            branches.add(branch);

        }





    }


    public Schedule(Schedule schedule){
        //TODO add deepcopying
    }
    private void addLecture(Lecture lecture,int day,int period){

        int teacherIndex = teachers.indexOf(lecture.getTeacher());
        Teacher teacher = teachers.get(teacherIndex);
        teacher.assignToPeriod(day,period);

        int studentsIndex = teachers.indexOf(lecture.getDivision());
        StudentGroup studentGroup = studentGroups.get(studentsIndex);
        studentGroup.removeSubject(lecture.getSubject());

        int subjectIndex = subjects.indexOf(lecture.getSubject());
        Subject subject = subjects.get(subjectIndex);

        int classIndex = building.allClassRooms().indexOf(lecture.getClassRoom());
        ClassRoom classRoom = building.allClassRooms().get(classIndex);

        Lecture lecture1 = new Lecture(subject,teacher,studentGroup,classRoom);

        schedule[day][period].add(lecture1);




    }

    public ArrayList<Schedule> getPossibleNextMoves(){

        for(int i=0 ; i <daysPerWeek; i++){

            for(int j=0 ; j <classesPerDay ;j++){

                for(StudentGroup studentGroup: studentGroups){
                    if(!canAddStudents(studentGroup,i,j))
                        continue;

                    for(Subject subject : studentGroup.getAllSubjects())
                    {
                        for(Teacher teacher: teachers)
                        {
                            if(!teacher.isAvailable(i,j)||!canAddTeacher(teacher,i,j)||!teacher.teachesSubject(subject))
                                continue;

                            for(ClassRoom classroom: building.allClassRooms())
                            {
                                if(!canAddClassroom(classroom,i,j) || !matchSubjectClassroom(subject,classroom))
                                    continue;



                            }

                        }
                    }
                }



            }

        }

        return new ArrayList<>();

    }
    private boolean canAddStudents(StudentGroup studentGroup, int day , int period)
    {
        ArrayList<Lecture> lectures = schedule[day][period];

        for (Lecture lect : lectures) {
            if (lect.isConflictedStudents(studentGroup)) {

                return false;
            }

        }

        return true;
    }


    private boolean canAddTeacher(Teacher teacher, int day, int period) {
        ArrayList<Lecture> lectures = schedule[day][period];

        for (Lecture lect : lectures) {
            if (lect.isConflictedTeacher(teacher)) {

                return false;
            }

        }

    return true;
    }
    private boolean canAddClassroom(ClassRoom classroom, int day, int period)
    {
        ArrayList<Lecture> lectures = schedule[day][period];

        for (Lecture lect : lectures) {
            if (lect.isConflictedClassroom(classroom)) {

                return false;
            }

        }
        return true;
    }
    boolean matchSubjectClassroom(Subject subject, ClassRoom classroom)
    {
        if((subject.getType() == ClassType.theoretical && classroom.getType() == ClassroomType.theater)
                ||(subject.getType() == ClassType.practical && classroom.getType() == ClassroomType.lab))
            return true;
        return false;
    }

    private int teacherPreferences()
    {
        int weight = 0;
        for(Teacher teacher : teachers)
        {
            //3rd pereference
            if(teacher.lecturesExceeded())
            {
                weight += 1;
            }
            //4thrd preference
            if(teacher.daysExceeded())
            {
                weight+=1;
            }
            //2nd pereference
            for (int i = 0;i<daysPerWeek;i++)
            {
                for(int j = 0 ; j<classesPerDay;j++)
                {
                    if(teacher.occupation[daysPerWeek][classesPerDay] == true &&
                            teacher.availablity[daysPerWeek][classesPerDay] == Teacher.Availablity.preffered)
                    {
                        weight +=1;
                    }
                }
            }
        }
        return weight;
    }

    public int practicalLectureDistribution()
    {
        int weight = 0;
        int count = 0;
        for(int i = 0;i<daysPerWeek;i++)
        {
            if(hasPractical(i))
            {
                count +=1;
            }
        }
        if(count>1)
        {
            weight = count;
        }
        return weight;
    }
   

    public boolean hasPractical(int day)
    {
        for(int j = 0; j<classesPerDay;j++)
        {
            for(Lecture lecture : schedule[daysPerWeek][classesPerDay])
            {
                if(lecture.getSubjectType() == ClassType.practical)
                {
                    return true;
                }
            }

        }
        return false;
    }

}