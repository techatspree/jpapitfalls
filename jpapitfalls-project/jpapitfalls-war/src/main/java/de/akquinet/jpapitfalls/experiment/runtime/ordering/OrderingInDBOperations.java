package de.akquinet.jpapitfalls.experiment.runtime.ordering;

import de.akquinet.jpapitfalls.experiment.model.company.Address;
import de.akquinet.jpapitfalls.experiment.model.company.Employee;
import de.akquinet.jpapitfalls.experiment.model.company.FullTimeEmployee;
import de.akquinet.jpapitfalls.experiment.model.company.Insurance;
import de.akquinet.jpapitfalls.experiment.model.company.PartTimeEmployee;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import de.akquinet.jpapitfalls.experiment.util.Utilities;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
@Stateless
public class OrderingInDBOperations {

    private static Logger LOG = Logger.getLogger(OrderingInDBOperations.class);

    @PersistenceContext
    private EntityManager em;

    public OrderingInDBOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
        TestData.createMoreTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sortInDB() {
        String queryText =
                "SELECT i, e FROM Insurance i JOIN i.employee e ORDER BY e.address.city, e.address.zip";
        Query q = em.createQuery(queryText);
        List<Object[]> queryResult = q.getResultList();
        for (Object[] elem : queryResult) {
            String tmp = ((Insurance)elem[0]).getCarrier();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sortInMemory() {
        String queryText = "SELECT i FROM Insurance i";
        Query q = em.createQuery(queryText);
        List<Insurance> queryResult = q.getResultList();
        Collections.sort(queryResult,
                (o1, o2) -> {
                    int result = 0;
                    Employee e1 = o1.getEmployee();
                    Employee e2 = o2.getEmployee();
                    if (e1 == null) {
                        result = (e2 == null) ? 0 : 1;
                    } else if (e2 == null) {
                        result = -1;
                    } else {
                        Address a1 = e1.getAddress();
                        Address a2 = e1.getAddress();
                        if (a1 == null) {
                            result = (a2 == null) ? 0 : 1;
                        } else if (a2 == null) {
                            result = -1;
                        } else {
                            result = Utilities.compareStrings(a1.getCity(), a2.getCity());
                            if (result == 0) {
                                result = Utilities.compareStrings(a1.getZip(), a2.getZip());
                            }
                        }
                    }
                    return result;
                });
        for (Insurance i : queryResult) {
            String tmp = i.getCarrier();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
