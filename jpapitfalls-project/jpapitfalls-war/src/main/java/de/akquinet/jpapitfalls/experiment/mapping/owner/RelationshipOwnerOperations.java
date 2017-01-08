package de.akquinet.jpapitfalls.experiment.mapping.owner;

import de.akquinet.jpapitfalls.experiment.mapping.owner.model.DepartmentOwner;
import de.akquinet.jpapitfalls.experiment.mapping.owner.model.EmployeeOwner;
import de.akquinet.jpapitfalls.experiment.util.Utilities;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mbouschen on 26.12.16.
 */
@Stateless
public class RelationshipOwnerOperations {
    private static Logger LOG = Logger.getLogger(RelationshipOwnerOperations.class);

    @PersistenceContext
    private EntityManager em;

    public RelationshipOwnerOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createDepartment() {
        DepartmentOwner dept = new DepartmentOwner("dept");
        em.persist(dept);
        em.flush();
        return dept.getId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addEmployee(Long deptId) {
        DepartmentOwner dept = em.find(DepartmentOwner.class, deptId);
        EmployeeOwner emp = new EmployeeOwner("emp1First", "emp1Last");
        em.persist(emp);
        dept.addEmployee(emp);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setDepartment(Long deptId) {
        DepartmentOwner dept = em.find(DepartmentOwner.class, deptId);
        EmployeeOwner emp = new EmployeeOwner("emp2First", "emp2Last");
        em.persist(emp);
        emp.setDepartment(dept);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printEmployeesOfDepartment(Long deptId, StringBuilder result) {
        DepartmentOwner dept = em.find(DepartmentOwner.class, deptId);
        result.append("Related emplyoees of Department " + dept + " <br>");
        Utilities.printCollection(dept.getEmployees(), result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        em.createQuery("DELETE FROM EmployeeOwner").executeUpdate();
        em.createQuery("DELETE FROM DepartmentOwner").executeUpdate();
    }
}
