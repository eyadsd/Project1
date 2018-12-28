package com.company.building;

import java.util.Objects;

public class ClassRoom {
    int id;
    public ClassRoom(int id)
    {
        this.id = id;

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
}
