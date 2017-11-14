package de.akquinet.jpapitfalls.experiment.query.update;

import de.akquinet.jpapitfalls.experiment.model.company.Insurance;
import de.akquinet.jpapitfalls.experiment.model.company.TestData;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
@Stateless
public class UpdateQueryOperations {

    private static Logger LOG = Logger.getLogger(UpdateQueryOperations.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public UpdateQueryOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTestData () {
        TestData.createTestData(em);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runUpdateQuery(StringBuilder result) {
        Insurance ins = TestData.getInsurance(em);
        Query q = em.createQuery("UPDATE Insurance i SET i.carrier = 'UPDATED' WHERE i = :ins");
        q.setParameter("ins", ins);
        int updates = q.executeUpdate();
        result.append("UPDATE query updated ").append(updates).append(" instance(s)");
        result.append(", but instance in memory still has carrier ").append(ins.getCarrier());
        result.append("<br>");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void runDeleteQuery(StringBuilder result) {
        Insurance ins = TestData.getInsurance(em);
        Query q = em.createQuery("DELETE FROM Insurance i WHERE i = :ins");
        q.setParameter("ins", ins);
        int deletes = q.executeUpdate();
        result.append("DELETE query deleted ").append(deletes).append(" instance(s)");
        result.append(", but instance in memory is still accessible ").append(ins.getCarrier());
        result.append("<br>");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        TestData.clearTestData(em);
    }

}
