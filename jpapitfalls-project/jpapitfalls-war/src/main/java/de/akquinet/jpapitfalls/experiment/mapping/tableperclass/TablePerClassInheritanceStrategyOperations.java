package de.akquinet.jpapitfalls.experiment.mapping.tableperclass;

import de.akquinet.jpapitfalls.experiment.model.company.Insurance;
import de.akquinet.jpapitfalls.experiment.model.singletable.ExternalEmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.model.singletable.InsuranceSingleTable;
import de.akquinet.jpapitfalls.experiment.model.tableperclass.EmployeeTablePerClass;
import de.akquinet.jpapitfalls.experiment.model.tableperclass.ExternalEmployeeTablePerClass;
import de.akquinet.jpapitfalls.experiment.model.tableperclass.FullTimeEmployeeTablePerClass;
import de.akquinet.jpapitfalls.experiment.model.tableperclass.InsuranceTablePerClass;
import de.akquinet.jpapitfalls.experiment.model.tableperclass.PartTimeEmployeeTablePerClass;
import de.akquinet.jpapitfalls.experiment.model.singletable.EmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.model.singletable.FullTimeEmployeeSingleTable;
import de.akquinet.jpapitfalls.experiment.model.singletable.PartTimeEmployeeSingleTable;
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
public class TablePerClassInheritanceStrategyOperations {

    private static Logger LOG = Logger.getLogger(TablePerClassInheritanceStrategyOperations.class);

    @PersistenceContext
    private EntityManager em;

    public static final int NR_OF_EMPLOYEES = 100;

    public TablePerClassInheritanceStrategyOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTablePerClassInstances() {
        for (int i = 0; i < NR_OF_EMPLOYEES; i++) {
            FullTimeEmployeeTablePerClass ft = new FullTimeEmployeeTablePerClass(
                    "ftemp" + i + "First", "ftemp" + i + "Last", 40.0d, 40000.0d);
            InsuranceTablePerClass ftins = new InsuranceTablePerClass("FT carrier" + i);
            ft.setInsurance(ftins);
            ftins.setEmployee(ft);
            em.persist(ft);
            em.persist(ftins);
            PartTimeEmployeeTablePerClass pt = new PartTimeEmployeeTablePerClass(
                    "ptemp" + i + "First", "ptemp" + i + "Last", 20.0d, 20000.0d);
            InsuranceTablePerClass ptins = new InsuranceTablePerClass("PT carrier" + i);
            pt.setInsurance(ptins);
            ptins.setEmployee(pt);
            em.persist(pt);
            em.persist(ptins);
            ExternalEmployeeTablePerClass ext = new ExternalEmployeeTablePerClass(
                    "ext" + i + "First", "ext" + i + "Last", 30.0d, "company");
            InsuranceTablePerClass extins = new InsuranceTablePerClass("EXT carrier" + i);
            ext.setInsurance(extins);
            extins.setEmployee(ext);
            em.persist(ext);
            em.persist(extins);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createSingleTableInstances() {
        for (int i = 0; i < NR_OF_EMPLOYEES; i++) {
            FullTimeEmployeeSingleTable ft = new FullTimeEmployeeSingleTable(
                    "ft" + i + "First", "ftemp" + i + "Last", 40.0d, 40000.0d);
            InsuranceSingleTable ftins = new InsuranceSingleTable("FT carrier" + i);
            ft.setInsurance(ftins);
            ftins.setEmployee(ft);
            em.persist(ft);
            em.persist(ftins);
            PartTimeEmployeeSingleTable pt = new PartTimeEmployeeSingleTable(
                    "pt" + i + "First", "ptemp" + i + "Last", 20.0d, 20000.0d);
            InsuranceSingleTable ptins = new InsuranceSingleTable("PT carrier" + i);
            pt.setInsurance(ptins);
            ptins.setEmployee(pt);
            em.persist(pt);
            em.persist(ptins);
            ExternalEmployeeSingleTable ext = new ExternalEmployeeSingleTable(
                    "ext" + i + "First", "ext" + i + "Last", 30.0d, "company");
            InsuranceSingleTable extins = new InsuranceSingleTable("EXT carrier" + i);
            ext.setInsurance(extins);
            extins.setEmployee(ext);
            em.persist(ext);
            em.persist(extins);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectTablePerClassInstances() {
        /*
        String queryText =
                "SELECT e FROM EmployeeTablePerClass e " +
                "WHERE e.firstname LIKE '%1%' AND e.weeklyhours > 25.0 " +
                "ORDER BY e.lastname, e.firstname";
        Query q = em.createQuery(queryText);
        List<EmployeeTablePerClass> queryResult = q.getResultList();
        for (EmployeeTablePerClass e : queryResult) {
            String tmp1 = e.getFirstname();
            double tmp2 = e.getWeeklyhours();
        }
        */
        String queryText = "SELECT i FROM InsuranceTablePerClass i";
        Query q = em.createQuery(queryText);
        List<InsuranceTablePerClass> queryResult = q.getResultList();
        for (InsuranceTablePerClass i : queryResult) {
            EmployeeTablePerClass e = i.getEmployee();
            String tmp1 = e.getFirstname();
            double tmp2 = e.getWeeklyhours();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void selectSingleTableInstances() {
        /*String queryText =
                "SELECT e FROM EmployeeSingleTable e " +
                "WHERE e.firstname LIKE '%1%' AND e.weeklyhours > 25.0 " +
                "ORDER BY e.lastname, e.firstname";
        Query q = em.createQuery(queryText);
        List<EmployeeSingleTable> queryResult = q.getResultList();
        for (EmployeeSingleTable e : queryResult) {
            String tmp1 = e.getFirstname();
            double tmp2 = e.getWeeklyhours();
        } */
        String queryText = "SELECT i FROM InsuranceSingleTable i";
        Query q = em.createQuery(queryText);
        List<InsuranceSingleTable> queryResult = q.getResultList();
        for (InsuranceSingleTable i : queryResult) {
            EmployeeSingleTable e = i.getEmployee();
            String tmp1 = e.getFirstname();
            double tmp2 = e.getWeeklyhours();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        em.createQuery("DELETE FROM InsuranceSingleTable").executeUpdate();
        em.createQuery("DELETE FROM EmployeeSingleTable").executeUpdate();
        em.createQuery("DELETE FROM InsuranceTablePerClass").executeUpdate();
        em.createQuery("DELETE FROM EmployeeTablePerClass").executeUpdate();
    }

}
