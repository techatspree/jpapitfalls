package de.akquinet.jpapitfalls.experiment.runtime.persist;

import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.FullTimeEmployee;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import java.util.List;

/**
 * Created by Michael Bouschen on 14.11.17.
 */
@Stateless
public class PersistOutsideTxOperations {

    private static Logger LOG = Logger.getLogger(PersistOutsideTxOperations.class);

    public static long NO_EMP_ID = -1;

    private EntityManagerFactory emf;

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public PersistOutsideTxOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee selectEmployee() {
        return TestData.getEmployee(em);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
     public long persistOutsideTxWithContainerManagedEM(StringBuilder result) {
        long empid = NO_EMP_ID;
        try {
            Employee emp = new FullTimeEmployee("Michael", "Bouschen", 40.0, 1000.0);
            em.persist(emp);
            empid = emp.getId();
            result.append("<br> ERROR: should throw TransactionRequiredException.");
        } catch (TransactionRequiredException ex) {
            // expected exception
            result.append("<br> throws expected TransactionRequiredException ");
        }
        return empid;
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public long persistOutsideTxWithApplicationManagedEM(StringBuilder result) {
        EntityManagerFactory emf = getEMF();
        EntityManager amEM = emf.createEntityManager();
        Employee emp = new FullTimeEmployee("Michael", "Bouschen", 40.0, 1000.0);
        amEM.persist(emp);
        return emp.getId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public long persistInsideTxWithContainerManagedEM(StringBuilder result) {
        Employee emp = new FullTimeEmployee("Michael", "Bouschen", 40.0, 1000.0);
        em.persist(emp);
        return emp.getId();
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public long persistInsideTxWithApplicationManagedEM(StringBuilder result) {
        EntityManagerFactory emf = getEMF();
        EntityManager amEM = emf.createEntityManager();
        amEM.getTransaction().begin();
        Employee emp = new FullTimeEmployee("Michael", "Bouschen", 40.0, 1000.0);
        amEM.persist(emp);
        amEM.getTransaction().commit();
        return emp.getId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printEmployee(Long id, StringBuilder result) {
        Employee emp = em.find(Employee.class, id);
        result.append(emp).append("<br>");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Employee findEmployee(Long id) {
        return em.find(Employee.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    private EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("se");
        }
        return emf;
    }

}
