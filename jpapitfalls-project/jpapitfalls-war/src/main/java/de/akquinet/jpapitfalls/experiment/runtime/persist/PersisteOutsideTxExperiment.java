package de.akquinet.jpapitfalls.experiment.runtime.persist;

import de.akquinet.jpapitfalls.experiment.Experiment;
import de.akquinet.jpapitfalls.experiment.model.company.Employee;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 14.11.16.
 */
public class PersisteOutsideTxExperiment implements Experiment {
    @EJB
    private PersistOutsideTxOperations persistOutsideTxOperations;

    @Override
    public String getId() {
        return "runtime : (5) persist result";
    }

    @Override
    public String getName() {
        return "EM.persist outside of an active transaction.";
    }

    @Override
    public String getDescription() {
        return "This experiment shows the effect of persisting an entity inside and outside of an active transaction.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        long empid;
        result.append("<br><i>Doing it the wrong way:</i><br>");
        result.append("<ul>");
        result.append("<li>");
        result.append("EM.persist outside of an transaction invoked on an container-managed entity manager: ");
        empid = persistOutsideTxOperations.persistOutsideTxWithContainerManagedEM(result);
        result.append("<br>");
        persistOutsideTxOperations.clearTestData();
        result.append("</li>");

        empid = persistOutsideTxOperations.persistOutsideTxWithApplicationManagedEM(result);
        result.append("<li>");
        result.append("EM.persist outside of an transaction invoked on an application-managed entity manager: ");
        result.append("<br>");
        result.append("Load employee with id " + empid + ": ");
        persistOutsideTxOperations.printEmployee(empid, result);
        persistOutsideTxOperations.clearTestData();
        result.append("</ul>");
        result.append("</li>");

        result.append("<br><i>Doing it the right way:</i><br>");
        result.append("<ul>");
        result.append("<li>");
        empid = persistOutsideTxOperations.persistInsideTxWithContainerManagedEM(result);
        result.append("EM.persist inside of an transaction invoked on an container-managed entity manager: ");
        result.append("<br>");
        result.append("Load employee with id " + empid + ": ");
        persistOutsideTxOperations.printEmployee(empid, result);
        persistOutsideTxOperations.clearTestData();
        result.append("</li>");

        empid = persistOutsideTxOperations.persistInsideTxWithApplicationManagedEM(result);
        result.append("<li>");
        result.append("EM.persist outside of an transaction invoked on an application-managed entity manager: ");
        result.append("<br>");
        result.append("Load employee with id " + empid + ": ");
        persistOutsideTxOperations.printEmployee(empid, result);
        persistOutsideTxOperations.clearTestData();
        result.append("</ul>");
        result.append("</li>");

        return result.toString();
    }
}
