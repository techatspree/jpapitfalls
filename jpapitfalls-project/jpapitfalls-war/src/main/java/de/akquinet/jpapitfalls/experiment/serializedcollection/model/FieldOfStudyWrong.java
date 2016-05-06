package de.akquinet.jpapitfalls.experiment.serializedcollection.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldOfStudyWrong extends EntityBase {

    @Basic
    @Getter
    @Setter
    private String name;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Basic
    private HashSet<StudentWrong> students = new HashSet<>();

}
