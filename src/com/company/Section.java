package com.company;


import java.util.ArrayList;
import java.util.HashSet;

class Section extends Students {



    public Section(int id , ArrayList<String> subjects) {

        allSubjects = new HashSet<Subject>();
        this.id = id;

        for(String classname : subjects){

            Subject s = new Subject(classname,ClassType.practical);
            allSubjects.add(s);


        }
    }

    @Override
    public boolean doesIntersect(Students division) {
        if(!(division instanceof Section)){
           division.doesIntersect(this);

        }

        return this.id ==division.id;


    }
}
