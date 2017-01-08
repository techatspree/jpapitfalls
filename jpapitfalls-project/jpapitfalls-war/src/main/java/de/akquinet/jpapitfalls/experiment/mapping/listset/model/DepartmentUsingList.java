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

package de.akquinet.jpapitfalls.experiment.mapping.listset.model;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentUsingList extends EntityBase {

    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @OrderColumn
    @Getter
    @Setter
    private List<EmployeeUsingList> employees;

    /**
     * Constructor.
     * @param name
     */
    public DepartmentUsingList(String name) {
        this.name = name;
    }

    /** */
    public void addEmployee(EmployeeUsingList e) {
        if (employees == null) {
            employees = new ArrayList<EmployeeUsingList>();
        }
        employees.add(e);
    }

    /**
     * Returns a String representation of a <code>DepartmentUsingList</code> object.
     * @return a String representation of a <code>DepartmentUsingList</code> object.
     */
    public String toString() {
        return "DepartmentUsingList(" + getFieldRepr()+ ")";
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
