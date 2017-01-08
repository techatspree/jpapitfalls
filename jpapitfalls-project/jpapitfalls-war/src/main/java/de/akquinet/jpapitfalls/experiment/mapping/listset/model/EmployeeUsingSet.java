/*
 * Copyright (c) akquinet tech@spree GmbH 2010
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
import javax.persistence.ManyToOne;
import java.io.Serializable;

/** */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUsingSet extends EntityBase implements Serializable {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Getter
    @Setter
    private DepartmentUsingSet department;

    /** */
    public EmployeeUsingSet(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Return a String representation of a <code>EmployeeUsingList</code> object.
     * @return a String representation of a <code>EmployeeUsingList</code> object.
     */
    public String toString() {
        return "EmployeeUsingSet(" + getFieldRepr() + ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(getId());
        rc.append(", firstname ").append(firstname);
        rc.append(", lastname ").append(lastname);
        return rc.toString();
    }
}
