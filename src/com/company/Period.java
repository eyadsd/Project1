package com.company;

import java.util.List;

public class Period {
    List<Lecture> lectures;

    public void addLecture(){


    }
    public boolean isFeasible(Lecture lecture){

        for(Lecture lect :lectures){
            if (lect.isConflicted(lecture)) {

                return false;
            }

        }
        return true;



    }
}
