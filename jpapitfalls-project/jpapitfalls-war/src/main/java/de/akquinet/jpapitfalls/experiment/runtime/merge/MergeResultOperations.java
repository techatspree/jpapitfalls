package de.akquinet.jpapitfalls.experiment.runtime.merge;

import de.akquinet.jpapitfalls.experiment.model.company.Employee;
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
 * Created by Michael Bouschen on 30.12.16.
 */
@Stateless
public class MergeResultOperations {

    private static Logger LOG = Logger.getLogger(MergeResultOperations.class);

    @PersistenceContext
    private EntityManager em;

    public MergeResultOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee selectEmployee() {
        return TestData.getEmployee(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
     public void doMergeWrong(Employee emp) {
        Query q = em.createQuery("SELECT e FROM Employee e");
        List<Employee> queryResult = q.getResultList();
        for (Employee e : queryResult) {
            String tmp = e.getFirstname() + " " + e.getWeeklyhours();
        }
        em.merge(emp);
        emp.setFirstname("New name");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void doMergeRight(Employee emp) {
        Query q = em.createQuery("SELECT e FROM Employee e");
        List<Employee> queryResult = q.getResultList();
        for (Employee e : queryResult) {
            String tmp = e.getFirstname() + " " + e.getWeeklyhours();
        }
        emp = em.merge(emp);
        emp.setFirstname("New name");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printEmployee(Long id, StringBuilder result) {
        Employee emp = em.find(Employee.class, id);
        result.append(emp).append("<br>");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
