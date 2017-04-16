package de.akquinet.jpapitfalls.experiment.query.isnull;

import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import de.akquinet.jpapitfalls.experiment.util.Utilities;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Michael Bouschen on 28.12.16.
 */
@Stateless
public class IsNullQueryOperations {

    private static Logger LOG = Logger.getLogger(IsNullQueryOperations.class);

    @PersistenceContext
    private EntityManager em;

    public IsNullQueryOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runParameterQuery (StringBuilder result) {
        String queryText = "SELECT e FROM Employee e WHERE e.department = :dept";
        Query q = em.createQuery(queryText);
        q.setParameter("dept", null);
        List<Employee> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runIsNullQuery (StringBuilder result) {
        String queryText = "SELECT e FROM Employee e WHERE e.department IS NULL";
        Query q = em.createQuery(queryText);
        List<Employee> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
