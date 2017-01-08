package de.akquinet.jpapitfalls.experiment.mapping.serializedcollection.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.HashSet;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldOfStudyWrong extends EntityBase implements FieldOfStudy {

    @Basic
    @Getter
    @Setter
    private String name;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Basic
    @Getter
    private HashSet<Student> students = new HashSet<>();

}
