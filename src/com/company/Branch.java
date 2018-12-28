package com.company;

import java.util.HashSet;
import java.util.List;

public class Branch extends Students {

    List<Section> sections;

    public Branch(int id , List<String> subjects){

        allSubjects = new HashSet<Subject>();
        this.id = id;

        for(String classname : subjects){

            Subject s = new Subject(classname,ClassType.theoretical);
            allSubjects.add(s);

        }

    }


    @Override
    public boolean doesIntersect(Students division) {
        if(!(division instanceof Branch)){
            for(Section s: sections){
                if(s.doesIntersect(division)){
                    return true;
                }
            }
        }

       return division.getId() ==this.id;


    }
}
