package de.akquinet.jpapitfalls.experiment.mapping.joined;

import de.akquinet.jpapitfalls.experiment.model.joined.EmployeeJoined;
import de.akquinet.jpapitfalls.experiment.model.joined.ExternalEmployeeJoined;
import de.akquinet.jpapitfalls.experiment.model.joined.FullTimeEmployeeJoined;
import de.akquinet.jpapitfalls.experiment.model.joined.PartTimeEmployeeJoined;
import de.akquinet.jpapitfalls.experiment.model.singletable.EmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.model.singletable.ExternalEmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.model.singletable.FullTimeEmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.model.singletable.PartTimeEmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.util.Utilities;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
@Stateless
public class JoinedInheritanceStrategyOperations {

    private static Logger LOG = Logger.getLogger(JoinedInheritanceStrategyOperations.class);

    @PersistenceContext
    private EntityManager em;

    public static final int NR_OF_EMPLOYEES = 200;

    public JoinedInheritanceStrategyOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createJoinedInstances() {
        for (int i = 0; i < NR_OF_EMPLOYEES; i++) {
            FullTimeEmployeeJoined ft = new FullTimeEmployeeJoined(
                    "ft" + i + "First", "ft" + i + "Last", 40.0d, 40000.0d);
            em.persist(ft);
            PartTimeEmployeeJoined pt = new PartTimeEmployeeJoined(
                    "pt" + i + "First", "pt" + i + "Last", 20.0d, 20000.0d);
            em.persist(pt);
            ExternalEmployeeJoined ext = new ExternalEmployeeJoined(
                    "ext" + i + "First", "ext" + i + "Last", 30.0d, "company");
            em.persist(ext);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createSingleTableInstances() {
        for (int i = 0; i < NR_OF_EMPLOYEES; i++) {
            FullTimeEmployeeSingleTable ft = new FullTimeEmployeeSingleTable(
                    "ft" + i + "First", "ft" + i + "Last", 40.0d, 40000.0d);
            em.persist(ft);
            PartTimeEmployeeSingleTable pt = new PartTimeEmployeeSingleTable(
                    "pt" + i + "First", "pt" + i + "Last", 20.0d, 20000.0d);
            em.persist(pt);
            ExternalEmployeeSingleTable ext = new ExternalEmployeeSingleTable(
                    "ext" + i + "First", "ext" + i + "Last", 30.0d, "company");
            em.persist(ext);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectJoinedInstances() {
        String queryText =
                "SELECT e FROM EmployeeJoined e " +
                "WHERE e.firstname LIKE '%1%' AND e.weeklyhours > 25.0" +
                "ORDER BY e.lastname, e.firstname";
        Query q = em.createQuery(queryText);
        List<EmployeeJoined> queryResult = q.getResultList();
        for (EmployeeJoined e : queryResult) {
            String tmp1 = e.getFirstname();
            double tmp2 = e.getWeeklyhours();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectSingleTableInstances() {
        String queryText =
                "SELECT e FROM EmployeeSingleTable e " +
                "WHERE e.firstname LIKE '%1%' AND e.weeklyhours > 25.0" +
                "ORDER BY e.lastname, e.firstname";
        Query q = em.createQuery(queryText);
        List<EmployeeSingleTable> queryResult = q.getResultList();
        for (EmployeeSingleTable e : queryResult) {
            String tmp1 = e.getFirstname();
            double tmp2 = e.getWeeklyhours();        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        em.createQuery("DELETE FROM EmployeeJoined").executeUpdate();
        em.createQuery("DELETE FROM EmployeeSingleTable").executeUpdate();
    }
}
