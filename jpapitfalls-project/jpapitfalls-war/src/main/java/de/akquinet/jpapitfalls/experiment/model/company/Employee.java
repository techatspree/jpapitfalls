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

package de.akquinet.jpapitfalls.experiment.model.company;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;

/** */
@Entity
@Table(name="EMP_TAB", indexes = @Index(name="cityIdx", columnList = "city, zip"))
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    discriminatorType=DiscriminatorType.STRING,
    name="DISCRIMINATOR", length=10)
@NamedQuery(
    name="findEmployeeByLastname", 
    query="SELECT e FROM Employee e WHERE e.lastname = :lastname")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employee extends EntityBase implements Serializable {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private double weeklyhours;

    @Getter
    @Setter
    private Date hiredate;

    @Embedded
    @Getter
    @Setter
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(nullable=true)
    @Getter
    @Setter
    private EmployeeStatus status;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "DEPT_ID", nullable = true)
    @Getter
    @Setter
    private Department department;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "EMP_PROJ_TAB")
    @Getter
    @Setter
    private Set<Project> projects;

    @OneToOne(mappedBy = "employee", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Getter
    @Setter
    private Insurance insurance;

    /** */
    public Employee(String firstname, String lastname, double weeklyHours) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.weeklyhours = weeklyHours;
    }

    /** */
    public void addProject(Project p) {
        if (projects == null) {
            projects = new HashSet<Project>();
        }
        projects.add(p);
    }

    /**
     * Return a String representation of a <code>Employee</code> object.
     * @return a String representation of a <code>Employee</code> object.
     */
    public String toString() {
        return "Employee(" + getFieldRepr() + ")";
    }

    /**
     * Returns a String representation of the non-relationship fields.
     * @return a String representation of the non-relationship fields.
     */
    protected String getFieldRepr() {
        StringBuilder rc = new StringBuilder();
        rc.append(getId());
        rc.append(", ").append(firstname);
        rc.append(" ").append(lastname);
        rc.append(", weeklyhours ").append(weeklyhours);
        rc.append(", hiredate ").append(hiredate);
        return rc.toString();
    }
}
