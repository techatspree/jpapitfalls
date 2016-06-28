package de.akquinet.jpapitfalls.experiment.serializedcollection.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FieldOfStudyRight extends EntityBase  {

    @Basic
    @Getter
    @Setter
    private String name;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @OneToMany()
    @JoinColumn(name="FIELD_OF_STUDY_ID")
    @Getter
    private Set<Student> students = new HashSet<>();

}
