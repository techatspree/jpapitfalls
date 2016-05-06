package de.akquinet.jpapitfalls.experiment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityBase {

    @Id
    @GeneratedValue
    @Getter
    private long id;

}
