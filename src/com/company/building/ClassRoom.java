package com.company.building;

import com.company.Subject;

import java.util.Objects;

public class ClassRoom {
    int id;
    ClassroomType type;
    public ClassRoom(int id,ClassroomType type)
    {
        this.id = id;
        this.type = type;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassRoom classRoom = (ClassRoom) o;
        return id == classRoom.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public ClassroomType getType()
    {
        return this.type;
    }
}
