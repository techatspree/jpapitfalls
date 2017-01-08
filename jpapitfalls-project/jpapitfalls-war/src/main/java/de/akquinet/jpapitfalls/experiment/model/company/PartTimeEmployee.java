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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/** */
@Entity
@AllArgsConstructor
@DiscriminatorValue("PT")
public class PartTimeEmployee extends Employee {

    @Getter
    @Setter
    private double wage;

    /** */
    public PartTimeEmployee() { 
        setStatus(EmployeeStatus.PARTTIME);
    }

    /** */
    public PartTimeEmployee(String firstname, String lastname, double weeklyHours, double wage) {
        super(firstname, lastname, weeklyHours);
        setStatus(EmployeeStatus.PARTTIME);
        this.wage = wage;
    }

    /**
     * Return a String representation of a <code>Employee</code> object.
     * @return a String representation of a <code>Employee</code> object.
     */
    public String toString() {
        return "PartTimeEmployee(" + getFieldRepr() + ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(super.getFieldRepr());
        rc.append(", wage ").append(wage);
        return rc.toString();
    }
}
