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

package de.akquinet.jpapitfalls.experiment.mapping.owner.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentOwner extends EntityBase {

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy="department")
    @Getter
    @Setter
    private Set<EmployeeOwner> employees;

    /**
     * Constructor.
     * @param name
     */
    public DepartmentOwner(String name) {
        this.name = name;
    }

    /** */
    public void addEmployee(EmployeeOwner e) {
        if (employees == null) {
            employees = new HashSet<EmployeeOwner>();
        }
        employees.add(e);
    }

    /**
     * Returns a String representation of a <code>DepartmentReachable</code> object.
     * @return a String representation of a <code>DepartmentReachable</code> object.
     */
    public String toString() {
        return "DepartmentOwner(" + getFieldRepr()+ ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(getId());
        rc.append(", name ").append(name);
        return rc.toString();
    }
}
