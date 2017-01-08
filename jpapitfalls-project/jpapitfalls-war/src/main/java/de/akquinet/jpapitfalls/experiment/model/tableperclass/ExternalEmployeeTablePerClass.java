package de.akquinet.jpapitfalls.experiment.model.tableperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Michael Bouschen on 31.12.16.
 */
@Entity
@DiscriminatorValue("EXT")
@NoArgsConstructor
@AllArgsConstructor
public class ExternalEmployeeTablePerClass extends EmployeeTablePerClass {

    @Getter
    @Setter
    private String company;

    /** */
    public ExternalEmployeeTablePerClass(String firstname, String lastname, double weeklyHours, String company) {
        super(firstname, lastname, weeklyHours);
        this.company = company;
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
        rc.append(", company ").append(company);
        return rc.toString();
    }
}
