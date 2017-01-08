package de.akquinet.jpapitfalls.experiment.runtime.iteration;

import de.akquinet.jpapitfalls.experiment.model.company.Address;
import de.akquinet.jpapitfalls.experiment.model.company.Department;
import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.FullTimeEmployee;
import de.akquinet.jpapitfalls.experiment.model.company.PartTimeEmployee;
import de.akquinet.jpapitfalls.experiment.model.company.Project;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
@Stateless
public class IterationOperations {

    private static Logger LOG = Logger.getLogger(IterationOperations.class);

    @PersistenceContext
    private EntityManager em;

    public IterationOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
        TestData.createMoreTestData(em);
   }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectIterationInMemory() {
        String queryText = "SELECT e FROM Employee e";
        Query q = em.createQuery(queryText);
        List<Employee> queryResult = q.getResultList();
        for (Employee e : queryResult) {
            Address a = e.getAddress();
            if ((a != null) && "Berlin".equals(a.getCity())) {
                String tmp = e.getFirstname();
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectUsingQuery() {
        String queryText = "SELECT e FROM Employee e JOIN e.address a WHERE a.city = 'Berlin'";
        Query q = em.createQuery(queryText);
        List<Employee> queryResult = q.getResultList();
        for (Employee e : queryResult) {
            String tmp = e.getFirstname();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
