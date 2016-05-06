package de.akquinet.jpapitfalls.experiment.serializedcollection.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentWrong extends EntityBase{

    @Basic
    @Getter
    @Setter
    private String name;
}
