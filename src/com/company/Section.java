package com.company;


import java.util.ArrayList;
import java.util.HashSet;

class Section extends StudentGroup {



    public Section(int id , ArrayList<Subject> subjects) {

        allSubjects = new HashSet<Subject>();
        this.id = id;
        for (Subject subject : subjects)
        {
            if(subject.getType() == ClassType.practical)
                allSubjects.add(subject);
        }
    }

    @Override
    public boolean doesIntersect(StudentGroup division) {
        if(!(division instanceof Section)){
           division.doesIntersect(this);

        }

        return this.id ==division.id;


    }
}
