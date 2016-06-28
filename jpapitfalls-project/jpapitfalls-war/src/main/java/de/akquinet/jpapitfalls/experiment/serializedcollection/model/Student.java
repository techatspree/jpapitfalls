package de.akquinet.jpapitfalls.experiment.serializedcollection.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student extends EntityBase implements Serializable{

    @Basic
    @Getter
    @Setter
    private String name;

    @Basic
    @Getter
    @Setter
    private Boolean passed;
}
