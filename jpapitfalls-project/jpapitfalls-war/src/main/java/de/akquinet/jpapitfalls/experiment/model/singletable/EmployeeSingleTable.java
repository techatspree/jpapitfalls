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

package de.akquinet.jpapitfalls.experiment.model.singletable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by Michael Bouschen on 31.12.16.
 */
@Entity
@DiscriminatorValue("EMP")
@NoArgsConstructor
@AllArgsConstructor
public abstract class EmployeeSingleTable extends PersonSingleTable {

    @Getter
    @Setter
    private double weeklyhours;

    @OneToOne(mappedBy = "employee", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Getter
    @Setter
    private InsuranceSingleTable insurance;

    /** */
    public EmployeeSingleTable(String firstname, String lastname, double weeklyHours) {
        super(firstname, lastname);
        this.weeklyhours = weeklyHours;
    }

    /**
     * Return a String representation of a <code>EmployeeReachable</code> object.
     * @return a String representation of a <code>EmployeeReachable</code> object.
     */
    public String toString() {
        return "EmployeeJoined(" + getFieldRepr() + ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(super.getFieldRepr());
        rc.append(", weeklyhours ").append(weeklyhours);
        return rc.toString();
    }
}
