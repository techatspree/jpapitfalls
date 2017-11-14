package de.akquinet.jpapitfalls.experiment.mapping.cascadepersist;

import de.akquinet.jpapitfalls.experiment.mapping.cascadepersist.model.DepartmentCascadePersist;
import de.akquinet.jpapitfalls.experiment.mapping.cascadepersist.model.DepartmentNonCascade;
import de.akquinet.jpapitfalls.experiment.mapping.cascadepersist.model.EmployeeNonCascade;
import de.akquinet.jpapitfalls.experiment.mapping.cascadepersist.model.EmployeeCascadePersist;
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
 * Created by mbouschen on 26.12.16.
 */
@Stateless
public class CascadePersistOperations {
    private static Logger LOG = Logger.getLogger(CascadePersistOperations.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public CascadePersistOperations() {}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createCascadePersist(StringBuilder sb) {
        DepartmentCascadePersist dept = new DepartmentCascadePersist("dept");
        EmployeeCascadePersist emp1 = new EmployeeCascadePersist("emp1First", "emp1Last");
        EmployeeCascadePersist emp2 = new EmployeeCascadePersist("emp2First", "emp2Last");
        dept.addEmployee(emp1);
        emp1.setDepartment(dept);
        dept.addEmployee(emp2);
        emp2.setDepartment(dept);
        em.persist(dept);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createNonCascade(StringBuilder sb) {
        DepartmentNonCascade dept = new DepartmentNonCascade("dept");
        EmployeeNonCascade emp1 = new EmployeeNonCascade("emp1First", "emp1Last");
        EmployeeNonCascade emp2 = new EmployeeNonCascade("emp2First", "emp2Last");
        dept.addEmployee(emp1);
        emp1.setDepartment(dept);
        dept.addEmployee(emp2);
        emp2.setDepartment(dept);
        em.persist(dept);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printNonCascadeInstances (StringBuilder result) {
        result.append("Create instances w/o cascade=persist - no employees in DB").append("<br>");
        String queryText = "SELECT e FROM EmployeeNonCascade e";
        Query q = em.createQuery(queryText);
        List<EmployeeNonCascade> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void printCascadePersistInstances(StringBuilder result) {
        result.append("Create instances w/ cascade=persist - there are employees in DB").append("<br>");
        String queryText = "SELECT e FROM EmployeeCascadePersist e";
        Query q = em.createQuery(queryText);
        List<EmployeeCascadePersist> queryResult = q.getResultList();
        Utilities.printQueryResult(queryText, queryResult, result);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        em.createQuery("DELETE FROM EmployeeCascadePersist").executeUpdate();
        em.createQuery("DELETE FROM EmployeeNonCascade").executeUpdate();
        em.createQuery("DELETE FROM DepartmentCascadePersist").executeUpdate();
        em.createQuery("DELETE FROM DepartmentNonCascade").executeUpdate();
    }
}
