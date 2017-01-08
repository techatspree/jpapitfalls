package de.akquinet.jpapitfalls.experiment.mapping.listset;

import de.akquinet.jpapitfalls.experiment.mapping.listset.model.DepartmentUsingList;
import de.akquinet.jpapitfalls.experiment.mapping.listset.model.DepartmentUsingSet;
import de.akquinet.jpapitfalls.experiment.mapping.listset.model.EmployeeUsingSet;
import de.akquinet.jpapitfalls.experiment.mapping.listset.model.EmployeeUsingList;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mbouschen on 29.12.16.
 */
@Stateless
public class ListVersusSetOperations {
    private static Logger LOG = Logger.getLogger(ListVersusSetOperations.class);

    @PersistenceContext
    private EntityManager em;

    public ListVersusSetOperations() {}

    public static final int NR_OF_INSTANCES_TO_ADD = 500;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createListInstances() {
        DepartmentUsingList dept = new DepartmentUsingList("dept");
        for (int i = 1; i < 10; i++) {
            EmployeeUsingList emp = new EmployeeUsingList("emp" + i + "First", "emp" + i + "Last");
            dept.addEmployee(emp);
        }
        em.persist(dept);
        return dept.getId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createSetInstances() {
        DepartmentUsingSet dept = new DepartmentUsingSet("dept");
        for (int i = 1; i < 10; i++) {
            EmployeeUsingSet emp = new EmployeeUsingSet("emp" + i + "First", "emp" + i + "Last");
            dept.addEmployee(emp);
        }
        em.persist(dept);
        return dept.getId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addToList(Long deptId) {
        final DepartmentUsingList dept = em.find(DepartmentUsingList.class, deptId);
        final int min = 100;
        final int max = min + NR_OF_INSTANCES_TO_ADD;
        for (int i = min; i < max; i++) {
            EmployeeUsingList emp = new EmployeeUsingList("emp" + i + "First", "emp" + i + "Last");
            em.persist(emp);
            dept.addEmployee(emp);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addToSet(Long deptId) {
        final DepartmentUsingSet dept = em.find(DepartmentUsingSet.class, deptId);
        final int min = 100;
        final int max = min + NR_OF_INSTANCES_TO_ADD;
        for (int i = min; i < max; i++) {
            EmployeeUsingSet emp = new EmployeeUsingSet("emp" + i + "First", "emp" + i + "Last");
            em.persist(emp);
            dept.addEmployee(emp);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void clearTestData() {
        em.createQuery("DELETE FROM EmployeeUsingSet").executeUpdate();
        em.createQuery("DELETE FROM EmployeeUsingList").executeUpdate();
        em.createQuery("DELETE FROM DepartmentUsingSet").executeUpdate();
        em.createQuery("DELETE FROM DepartmentUsingList").executeUpdate();
    }
}
