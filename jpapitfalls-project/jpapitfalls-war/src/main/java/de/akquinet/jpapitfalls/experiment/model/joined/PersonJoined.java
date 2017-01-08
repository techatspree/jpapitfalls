package de.akquinet.jpapitfalls.experiment.model.joined;

import de.akquinet.jpapitfalls.experiment.model.EntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Michael Bouschen on 31.12.16.
 */
@Entity
@Table(indexes=@Index(name="PJ_NAME_IDX", columnList="lastname, firstname"))
@Inheritance(strategy= InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonJoined extends EntityBase implements Serializable {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    /**
     * Return a String representation of a <code>EmployeeReachable</code> object.
     * @return a String representation of a <code>EmployeeReachable</code> object.
     */
    public String toString() {
        return "PersonJoined(" + getFieldRepr() + ")";
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
