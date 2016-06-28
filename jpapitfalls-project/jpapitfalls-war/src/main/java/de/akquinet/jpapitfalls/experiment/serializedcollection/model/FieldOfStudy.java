package de.akquinet.jpapitfalls.experiment.serializedcollection.model;

public interface FieldOfStudy {
    String getName();

    java.util.HashSet<Student> getStudents();

    void setName(String name);
}
