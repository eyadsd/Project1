package com.company;

import java.util.HashSet;
import java.util.List;

public class Branch extends StudentGroup {

    List<Section> sections;
    int numberOfSections;

    public Branch(int id , List<Subject> subjects, List<Section> sections){

        allSubjects = new HashSet<Subject>();
        this.id = id;
        for (Subject subject : subjects)
        {
            if(subject.getType() == ClassType.theoretical)
            allSubjects.add(subject);
        }

    }


    @Override
    public boolean doesIntersect(StudentGroup division) {
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
