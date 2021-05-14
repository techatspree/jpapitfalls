package de.akquinet.jpapitfalls.experiment.runtime.remove;

import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.Insurance;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Michael Bouschen on 13.05.21.
 */
@Stateless
public class RemoveOperations {

    private static Logger LOG = Logger.getLogger(RemoveOperations.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public RemoveOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee selectEmployee() {
        return TestData.getEmployee(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public void doRemoveWrong(Employee emp) {
        emp = em.merge(emp);
        em.remove(emp.getInsurance());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void doRemoveRight(Employee emp) {
        emp = em.merge(emp);
        Insurance ins = emp.getInsurance();
        emp.setInsurance(null);
        em.remove(ins);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printEmployee(Long id, StringBuilder result) {
        Employee emp = em.find(Employee.class, id);
        result.append(emp).append("<br>");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printInsurance(Long id, StringBuilder result) {
        Insurance ins = em.find(Insurance.class, id);
        result.append(ins).append("<br>");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
