package de.akquinet.jpapitfalls.experiment.mapping.serializedcollection.model;

import java.util.Set;

public interface FieldOfStudy {
    String getName();

    Set<Student> getStudents();

    void setName(String name);
}
