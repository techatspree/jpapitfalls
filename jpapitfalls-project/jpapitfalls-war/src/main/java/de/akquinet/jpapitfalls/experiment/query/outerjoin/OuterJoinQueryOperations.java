package de.akquinet.jpapitfalls.experiment.query.outerjoin;

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
public class OuterJoinQueryOperations {

    private static Logger LOG = Logger.getLogger(OuterJoinQueryOperations.class);

    @PersistenceContext
    private EntityManager em;

    public OuterJoinQueryOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runNavigationQuery (StringBuilder result) {
        String queryText = "SELECT DISTINCT e.department FROM Employee e";
        Query q = em.createQuery(queryText);
        List<Employee> queryResult = q.getResultList();
        result.append("The following query <br>  ").append(queryText).append("<br>returns<br>");
        Utilities.printCollection(queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runOuterJoinQuery (StringBuilder result) {
        String queryText = "SELECT DISTINCT d FROM Employee e LEFT OUTER JOIN e.department d";
        Query q = em.createQuery(queryText);
        List<Employee> queryResult = q.getResultList();
        result.append("The following query <br>  ").append(queryText).append("<br>returns<br>");
        Utilities.printCollection(queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }


}
