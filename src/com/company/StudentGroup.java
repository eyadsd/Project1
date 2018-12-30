package com.company;

import java.util.HashSet;
import java.util.Set;

public abstract class StudentGroup {
    protected Set<Subject> allSubjects;

    protected int id;



    public boolean isSatisfied()
    {
        if(allSubjects.isEmpty())
        {
            return true;
        }
        else
        {
            return false;

        }
    }


    public abstract boolean doesIntersect(StudentGroup division);

    public int getId() {
        return id;
    }

    public Set<Subject> getAllSubjects() {
        return allSubjects;
    }


    public void removeSubject(Subject subject){
        allSubjects.remove(subject);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if(o==null){
            return false;
        }
        if ( getClass() != o.getClass()){

            return false;

        }

        StudentGroup that = (StudentGroup) o;

        return id == that.id;
    }

    @Override
    public String toString() {
        return "id ="+id;
    }

    @Override
    public int hashCode() {
        int result = allSubjects != null ? allSubjects.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
    public boolean isEmpty(){
        return allSubjects.isEmpty();
    }
}
