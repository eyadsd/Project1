package com.company;

public class Lecture {
    Subject subject;
    Teacher teacher;
    Division division;



    public Lecture(Subject subject,Teacher teacher, Division division){
        this.teacher = teacher;
        this.subject = subject;
        this.division = division;

    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public boolean isConflicted(Lecture lecture){
        if(this.teacher.equals(lecture.getTeacher())){
            return true;
        }
        if(this.division.equals(lecture.getDivision())){
            return true;
        }
        return false;

    }

}
