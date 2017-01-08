package de.akquinet.jpapitfalls.experiment.model.singletable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by mbouschen on 31.12.16.
 */
@Entity
@DiscriminatorValue("PT")
@NoArgsConstructor
@AllArgsConstructor
public class PartTimeEmployeeSingleTable extends EmployeeSingleTable {

    @Getter
    @Setter
    private double wage;

    /** */
    public PartTimeEmployeeSingleTable(String firstname, String lastname, double weeklyHours, double wage) {
        super(firstname, lastname, weeklyHours);
        this.wage = wage;
    }

    /**
     * Return a String representation of a <code>EmployeeReachable</code> object.
     * @return a String representation of a <code>EmployeeReachable</code> object.
     */
    public String toString() {
        return "PartTimeEmployeeJoined(" + getFieldRepr() + ")";
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
