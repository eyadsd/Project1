package com.company;

import java.util.Set;

public abstract class Students{
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


    public abstract boolean doesIntersect(Students division);

    public int getId() {
        return id;
    }
}
