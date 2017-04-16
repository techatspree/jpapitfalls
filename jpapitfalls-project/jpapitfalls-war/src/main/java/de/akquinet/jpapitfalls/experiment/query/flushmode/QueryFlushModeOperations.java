package de.akquinet.jpapitfalls.experiment.query.flushmode;

import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import de.akquinet.jpapitfalls.experiment.util.Utilities;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
@Stateless
public class QueryFlushModeOperations {

    private static Logger LOG = Logger.getLogger(QueryFlushModeOperations.class);

    @PersistenceContext
    private EntityManager em;

    public QueryFlushModeOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData() {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runQueryWithFlushModeCommit(StringBuilder result) {
        Employee e = TestData.getEmployee(em);
        e.setWeeklyhours(41.0d);
        String queryText = "SELECT e FROM Employee e WHERE e.weeklyhours > 40.0d";
        Query q = em.createQuery(queryText);
        q.setFlushMode(FlushModeType.COMMIT);
        List<Employee> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runQueryWithFlushModeAuto(StringBuilder result) {
        Employee e = TestData.getEmployee(em);
        e.setWeeklyhours(41.0d);
        String queryText = "SELECT e FROM Employee e WHERE e.weeklyhours > 40.0d";
        Query q = em.createQuery(queryText);
        q.setFlushMode(FlushModeType.AUTO);
        List<Employee> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
