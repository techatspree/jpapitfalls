package de.akquinet.jpapitfalls.experiment.runtime.loadingrelationships;

import de.akquinet.jpapitfalls.experiment.model.company.Address;
import de.akquinet.jpapitfalls.experiment.model.company.Department;
import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.FullTimeEmployee;
import de.akquinet.jpapitfalls.experiment.model.company.Insurance;
import de.akquinet.jpapitfalls.experiment.model.company.PartTimeEmployee;
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
public class LoadingRelationshipsOperations {

    private static Logger LOG = Logger.getLogger(LoadingRelationshipsOperations.class);

    @PersistenceContext
    private EntityManager em;

    public LoadingRelationshipsOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
        TestData.createMoreTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectUsingJoinQuery() {
        String queryText =
                "SELECT e.firstname, e.lastname, d.name, i.carrier " +
                "FROM Employee e LEFT OUTER JOIN e.department d LEFT OUTER JOIN e.insurance i " +
                "ORDER BY e.lastname, e.firstname";
        Query q = em.createQuery(queryText);
        List<Object[]> queryResult = q.getResultList();
        for (Object[] elem : queryResult) {
            String tmp = elem[0] + " " + elem[1] + " " + elem[2] + " " + elem[3];
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectRootObjectsPlusRelationshipNavigation() {
        String queryText = "SELECT e FROM Employee e ORDER BY e.lastname, e.firstname";
        Query q = em.createQuery(queryText);
        List<Employee> queryResult = q.getResultList();
        for (Employee e : queryResult) {
            String dept = e.getDepartment() == null ? null : e.getDepartment().getName();
            String carrier = e.getInsurance() == null ? null : e.getInsurance().getCarrier();
            String tmp = e.getFirstname() + " " + e.getLastname() + " " + dept + " " + carrier;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
