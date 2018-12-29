package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Branch extends StudentGroup {

    List<Section> sections;


    public Branch(int id , List<Subject> subjects, List<Section> sections){

        this.sections=sections;
        allSubjects = new HashSet<Subject>();
        this.id = id;
        for (Subject subject : subjects)
        {
            if(subject.getType() == ClassType.theoretical)
            allSubjects.add(subject);
        }


    }
    public Branch(Branch branch){

        this.id = branch.id;
        this.sections = new ArrayList<Section>();
        this.allSubjects = new HashSet<>();

        for(Section section :branch.sections){
            sections.add(new Section(section));
        }

        for (Subject subject: branch.allSubjects){
            this.allSubjects.add(new Subject(subject));
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
