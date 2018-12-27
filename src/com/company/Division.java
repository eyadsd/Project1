package com.company;

import java.util.Set;

public abstract class Division {
    Set<Subject> subjects;
    public boolean isSatisfied()
    {
        if(subjects.isEmpty())
        {
            return true;
        }
        else
        {
            return false;

        }
    }
}
