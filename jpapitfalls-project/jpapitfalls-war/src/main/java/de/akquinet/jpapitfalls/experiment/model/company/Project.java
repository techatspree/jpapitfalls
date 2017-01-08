/*
 * Copyright (c) akquinet tech@spree GmbH 2016
 * All rights reserved
 *
 * The copyright of the computer program(s) herein is the property of
 * akquinet tech@spree GmbH.
 * The program(s) may be used/copied only with the written permission of
 * akquinet tech@spree or in accordance with the terms and conditions stipulated
 * in the agreement/contract under which the program(s) have been supplied.
 */

package de.akquinet.jpapitfalls.experiment.model.company;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/** */
@Entity
@Table(name = "PROJ_TAB")
@NoArgsConstructor
@AllArgsConstructor
public class Project extends EntityBase implements Serializable {

    @Getter
    @Setter
    private String name;

    /** */
    @ManyToMany(mappedBy = "projects")
    @Getter
    @Setter
    private Set<Employee> employees;

    /** */
    public Project(String name) {
        this.name = name;
    }

    /** */
    public void addEmployee(Employee e) {
        if (employees == null) {
            employees = new HashSet<Employee>();
        }
        employees.add(e);
    }

    /**
     * Returns a String representation of a <code>Department</code> object.
     * @return a String representation of a <code>Department</code> object.
     */
    public String toString() {
        return "Project(" + getFieldRepr()+ ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(getId());
        rc.append(", ").append(name);
        return rc.toString();
    }
}
