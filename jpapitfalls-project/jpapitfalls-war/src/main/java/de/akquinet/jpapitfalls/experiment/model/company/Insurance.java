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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/** */
@Entity
@Table(name = "INS_TAB")
@NoArgsConstructor
@AllArgsConstructor
public class Insurance extends EntityBase implements Serializable {

    @Getter
    @Setter
    private String carrier;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
              fetch = FetchType.LAZY)
    @JoinColumn(name="EMP_ID", nullable = true)
    @Getter
    @Setter
    private Employee employee;

    /**
     * Constructor.
     * @param carrier
     */
    public Insurance(String carrier) {
        this();
        this.carrier = carrier;
    }

    /**
     * Returns a String representation of a <code>Department</code> object.
     * @return a String representation of a <code>Department</code> object.
     */
    public String toString() {
        return "Insurance(" + getFieldRepr()+ ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(getId());
        rc.append(", ").append(carrier);
        return rc.toString();
    }
}
