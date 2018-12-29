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
        public Lecture(Lecture lecture){
            this.teacher = new Teacher(lecture.teacher);
            if(lecture.division instanceof Section){

                this.division= new Section((Section) lecture.division);
            }
            else {
                this.division = new Branch((Branch)lecture.division);

            }

            this.subject = new Subject(lecture.subject);
            this.classRoom = new ClassRoom(lecture.classRoom);

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


        public boolean isTheoritical(){

            if(subject.getType()==ClassType.theoretical){
                return true;
            }
            return false;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Lecture lecture = (Lecture) o;
            return Objects.equals(subject, lecture.subject) &&
                    Objects.equals(teacher, lecture.teacher) &&
                    Objects.equals(division, lecture.division) &&
                    Objects.equals(classRoom, lecture.classRoom);
        }

        @Override
        public int hashCode() {
            return Objects.hash(subject, teacher, division, classRoom);
        }

        @Override
        public String toString() {
            return "L(" +
                    "S=" + subject +
                    ", T=" + teacher +
                    ", D=" + division +
                    ", C=" + classRoom +
                    ')';
        }


    }

    int classesPerDay = Constants.CLASSES_PER_day; // number of classes per day
    int daysPerWeek = Constants.DAYS_PER_WEEK; // number of school days per week

    int numberOfStudents;
    int numberOfBranches;
    int numberOfSections;

    int weight=0;

    ArrayList<Subject> subjects;
    ArrayList<StudentGroup> studentGroups;
    ArrayList<Branch> branches ;



    Building building;
    ArrayList<Teacher> teachers;

    ArrayList<Lecture>[][] schedule ;


    public Schedule(Building building, int numberOfStudents,ArrayList<Subject> subjects,ArrayList<Teacher> teachers) {



        branches =new ArrayList<>();
        schedule = new ArrayList[daysPerWeek][classesPerDay];

        for(int i =0 ; i <daysPerWeek ; i++){


            for(int j=0 ; j<classesPerDay;j++){
                schedule[i][j] = new ArrayList<Lecture>();

            }
        }

        this.teachers = teachers;
        this.subjects = subjects;
        this.building = building;

        this.numberOfStudents = numberOfStudents;
        this.numberOfBranches = (int)Math.ceil((float)numberOfStudents/(float)building.getTheaterSize());
        this.numberOfSections = (int)Math.ceil((float)numberOfStudents/(float)building.getLabSize()); //temporary variable
        int numberOfSectionsInBranch = (int)Math.ceil((float)numberOfSections/(float)numberOfBranches);
        this.numberOfSections = numberOfSectionsInBranch * numberOfBranches;//real number of sections
        this.studentGroups = new ArrayList<StudentGroup>();

        int l = 1;

        for(int i=1 ; i <=this.numberOfBranches ; i++){
            ArrayList<Section> sections = new ArrayList<Section>();
            for(int j = 1; j <= numberOfSectionsInBranch; j++)
            {
                Section section = new Section(l,subjects);
                sections.add(section);
                studentGroups.add(section);
                l++;
            }
            Branch branch = new Branch(i,subjects,sections);
            studentGroups.add(branch);
            branches.add(branch);

        }
        int garbage = 0;





    }



    public Schedule(Schedule schedule){

        this.building = new Building(schedule.building);

        this.teachers = new ArrayList<>();
        for(Teacher teacher: schedule.teachers){
            this.teachers.add(new Teacher(teacher));
        }
        this.subjects = new ArrayList<>();
        for(Subject subject : schedule.subjects){
            this.subjects.add(new Subject(subject));
        }

        this.numberOfSections=schedule.numberOfSections;
        this.numberOfBranches=schedule.numberOfBranches;
        this.numberOfStudents=schedule.numberOfStudents;

        this.studentGroups = new ArrayList<>();

        for(StudentGroup studentGroup : schedule.studentGroups){
            if(studentGroup instanceof Section){

                this.studentGroups.add(new Section((Section) studentGroup));
            }
            else{
                this.studentGroups.add(new Branch((Branch)studentGroup));

            }
        }

        this.branches = new ArrayList<>();

        for(Branch branch : schedule.branches){

            this.branches.add(new Branch(branch));
        }

        this.schedule = new ArrayList[daysPerWeek][classesPerDay];

        for(int i =0 ; i <daysPerWeek ; i++){


            for(int j=0 ; j<classesPerDay;j++){
                this.schedule[i][j] = new ArrayList<>();
                for(Lecture lecture : schedule.schedule[i][j]){
                    this.schedule[i][j].add(new Lecture(lecture));
                }

            }
        }
        this.weight=schedule.weight;








    }

    private void addLecture(Lecture lecture,int day,int period){

        int teacherIndex = teachers.indexOf(lecture.getTeacher());
        Teacher teacher = teachers.get(teacherIndex);
        teacher.assignToPeriod(day,period);

        int studentsIndex = studentGroups.indexOf(lecture.getDivision());
        StudentGroup studentGroup = studentGroups.get(studentsIndex);
        studentGroup.removeSubject(lecture.getSubject());

        int subjectIndex = subjects.indexOf(lecture.getSubject());
        Subject subject = subjects.get(subjectIndex);

        int classIndex = building.allClassRooms().indexOf(lecture.getClassRoom());
        ClassRoom classRoom = building.allClassRooms().get(classIndex);

        Lecture lecture1 = new Lecture(subject,teacher,studentGroup,classRoom);

        schedule[day][period].add(lecture1);

        calculateNewWeight(lecture1,day,period);




    }

    public ArrayList<Schedule> getPossibleNextMoves(){
        ArrayList<Schedule> schedules = new ArrayList<>();

        for(int i=0 ; i <daysPerWeek; i++){

            for(int j=0 ; j <classesPerDay ;j++){

                for(StudentGroup studentGroup: studentGroups){
                    if(!canAddStudents(studentGroup,i,j))
                        continue;

                    for(Subject subject : studentGroup.getAllSubjects())
                    {
                        for(Teacher teacher: teachers)
                        {
                            if(!teacher.isAvailable(i,j) ||!canAddTeacher(teacher,i,j)||!teacher.teachesSubject(subject) )
                                continue;

                            for(ClassRoom classroom: building.allClassRooms())
                            {
                                if(!canAddClassroom(classroom,i,j) || !matchSubjectClassroom(subject,classroom) )
                                    continue;


                                Lecture lecture = new Lecture(subject,teacher,studentGroup,classroom);
                                Schedule schedule = new Schedule(this);
                                schedule.addLecture(lecture,i,j);
                                schedules.add(schedule);

                            }

                        }
                    }
                }



            }

        }

        return schedules;

    }

    private void calculateNewWeight(Lecture lecture,int day,int period){

        int weight1_T=1;
        int weight1_S =1;
        int weight2=1;
        int weight3 = 1;
        int weight4 =1;
        int weight5 =1;
        int weight6 = 1;
        int weight7 =1;

        Teacher teacher = lecture.getTeacher();

        if(doesCreateSpacesbetweenlectures_Students(lecture,day,period)){
            this.weight+=weight1_S;

        }
        if(doesCreateSpacesbetweenlectures_Teachers(lecture,day,period)){
            this.weight+=weight1_T;

        }

        if(!teacher.isPreferredPeriod(day,period)){
            this.weight+=weight2;
        }

        if(teacher.lecturesExceeded()) {
            this.weight += weight3;
        }
        if(teacher.daysExceeded()) {
            this.weight += weight4;

        }



        if(DoesExceedThreeLectures(lecture.getDivision(),day)){
            this.weight+=weight5;
        }


        if (isPracticalAfterTheoritical(lecture,day,period)){

            this.weight+=weight6;
        }

        if(!lecture.isTheoritical()){
            if(isMoreThanOnePracticalLecture(lecture,day,period)){

                this.weight+=weight7;
            }
        }




    }


    private boolean doesCreateSpacesbetweenlectures_Students(Lecture lecture,int day, int period){



        int l=0;
        for(int i=period -1; i >=0 ; i--){
            ArrayList<Lecture> lectures = schedule[day][i];


            for(Lecture lecture1 : lectures){
                if(lecture1.isConflictedStudents(lecture.getDivision() ) && l>0){
                     return true;

                }

            }
            l++;
        }




        return false;

    }

    private boolean doesCreateSpacesbetweenlectures_Teachers(Lecture lecture,int day, int period){



        int l=0;
        for(int i=period -1; i >=0 ; i--){
            ArrayList<Lecture> lectures = schedule[day][i];


            for(Lecture lecture1 : lectures){
                if(lecture1.isConflictedTeacher(lecture.getTeacher() ) && l>0){
                    return true;

                }

            }
            l++;
        }




        return false;

    }



    private boolean isPracticalAfterTheoritical(Lecture lecture,int day , int period){

        if(lecture.isTheoritical()){

            for(int i=period -1; i >=0 ; i--){
                ArrayList<Lecture> lectures = schedule[day][i];

                for(Lecture lecture1 : lectures){
                    if(!lecture1.isTheoritical() && lecture1.isConflictedStudents(lecture.getDivision())){
                        return false;
                    }
                }
            }




        }else {
            for(int i=period +1; i >classesPerDay ; i++){
                ArrayList<Lecture> lectures = schedule[day][i];

                for(Lecture lecture1 : lectures){
                    if(lecture1.isTheoritical() && lecture1.isConflictedStudents(lecture.getDivision())){
                        return false;
                    }
                }
            }


        }

        return false;

    }


    private  boolean DoesExceedThreeLectures(StudentGroup group , int day){
        int count =0;
        for(int i=0 ; i<classesPerDay; i++){

            ArrayList<Lecture> lectures = schedule[day][i];

            for(Lecture lecture : lectures){
                if( lecture.isConflictedStudents(group)){
                    count++;
                }
            }
        }
        if (count > 3){
            return true;
        }

        return false;
    }

    private boolean isMoreThanOnePracticalLecture(Lecture lecture,int day ,int period){

        StudentGroup studentGroup = lecture.getDivision();

        for(int i=0 ; i<classesPerDay; i++){

            ArrayList<Lecture> lectures = schedule[day][i];

            for(Lecture lecture1 : lectures){
                if( lecture1.isConflictedStudents(studentGroup) && i!=period && !lecture1.isTheoritical()){
                    return true;
                }
            }
        }

        return false;

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
    private boolean matchSubjectClassroom(Subject subject, ClassRoom classroom)
    {
        if((subject.getType() == ClassType.theoretical && classroom.getType() == ClassroomType.theater)
                ||(subject.getType() == ClassType.practical && classroom.getType() == ClassroomType.lab))
            return true;
        return false;
    }




    public int getWeight(){

        return weight;

    }
    public boolean isAccepted()
    {
        for (StudentGroup studentGroup : studentGroups)
        {
            if(!studentGroup.isEmpty())
                return false;
        }
        return true;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        int i=1;
        int j=1;
        for (ArrayList<Lecture>[] x : schedule)
        {
            j=0;
            for (ArrayList y : x)
            {
                j++;
                str.append("(" + i +j +")");
                if(y.isEmpty()) {
                    str.append("empty" + " ");
                }
                else{
                    str.append(y + " ");
                }
            }
            str.append("\n");
            i++;
        }

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule1 = (Schedule) o;
        for(int i = 0; i<daysPerWeek;i++)
        {
            for(int j = 0;j<classesPerDay;j++)
            {
                if(!(schedule1.schedule[i][j].equals(this.schedule[i][j])))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(schedule);
    }

    public void print()
    {
        for(int i = 0;i<daysPerWeek;i++)
        {
            for(int j = 0;j<classesPerDay;j++)
            {
                System.out.print("period"+i+" "+j);
                for(Lecture lecture: schedule[i][j])
                {
                    System.out.print(lecture.toString());
                    System.out.print("    ");
                }
            }
            System.out.print("\n");

        }
        System.out.print("weight ="+weight);
        System.out.print("\ndone\n\n");

    }
}