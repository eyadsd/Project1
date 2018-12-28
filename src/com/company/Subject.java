package com.company;

import java.util.Objects;

public class Subject {
   private String className;
    private ClassType type;

    public Subject(String className , ClassType type){
        this.className=className;
        this.type=type;
    }

    public String getClassName() {
        return className;
    }



    public ClassType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(className, subject.className) &&
                type == subject.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, type);
    }
}
