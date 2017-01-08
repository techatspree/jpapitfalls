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

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/** */
@Entity
@DiscriminatorValue("FT")
public class FullTimeEmployee extends Employee {

    /** */
    public FullTimeEmployee() { 
        setStatus(EmployeeStatus.FULLTIME);
    }

    @Getter
    @Setter
    private double salary;

    /** */
    public FullTimeEmployee(String firstname, String lastname, double weeklyhours, double salary) {
        super(firstname, lastname, weeklyhours);
        setStatus(EmployeeStatus.FULLTIME);
    }

    /**
     * Return a String representation of a <code>Employee</code> object.
     * @return a String representation of a <code>Employee</code> object.
     */
    public String toString() {
        return "FullTimeEmployee(" + getFieldRepr() + ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(super.getFieldRepr());
        rc.append(", salary ").append(salary);
        return rc.toString();
    }
}
