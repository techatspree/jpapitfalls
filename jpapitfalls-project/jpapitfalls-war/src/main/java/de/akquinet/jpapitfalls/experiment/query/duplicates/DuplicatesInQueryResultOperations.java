package de.akquinet.jpapitfalls.experiment.query.duplicates;

import de.akquinet.jpapitfalls.experiment.model.company.Department;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import de.akquinet.jpapitfalls.experiment.util.Utilities;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 * Created by Michael Bouschen on 19.12.2016.
 */
@Stateless
public class DuplicatesInQueryResultOperations {

    private static Logger LOG = Logger.getLogger(DuplicatesInQueryResultOperations.class);

    @PersistenceContext
    private EntityManager em;

    public DuplicatesInQueryResultOperations () {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runQueryWithDuplicates (StringBuilder result) {
        String queryText = "SELECT d FROM Department d JOIN d.employees e WHERE e.weeklyhours >= 39.0d";
        Query q = em.createQuery(queryText);
        List<Department> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runQueryWithoutDuplicates(StringBuilder result) {
        String queryText = "SELECT DISTINCT d FROM Department d JOIN d.employees e WHERE e.weeklyhours >= 39.0d";
        Query q = em.createQuery(queryText);
        List<Department> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
