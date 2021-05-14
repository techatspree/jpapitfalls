package de.akquinet.jpapitfalls.experiment.runtime.remove;

import de.akquinet.jpapitfalls.experiment.Experiment;
import de.akquinet.jpapitfalls.experiment.model.company.Employee;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 13.05.21.
 */
public class RemoveExperiment implements Experiment {
    @EJB
    private RemoveOperations removeOperations;

    @Override
    public String getId() {
        return "runtime : (7) remove";
    }

    @Override
    public String getName() {
        return "Removed instance being recreated with cascade=PERSIST.";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that a removed instance is recreated " +
                "when referenced in a relationship with cascade=PERSIST";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        removeOperations.createTestData();
        Employee emp = removeOperations.selectEmployee();
        Long insId = emp.getInsurance() == null ? null : emp.getInsurance().getId();
        result.append("Remove an insurance that is still referenced by a persistent employee.<br>");
        removeOperations.doRemoveWrong(emp);
        result.append("Print insurance:<br>");
        removeOperations.printInsurance(insId, result);
        removeOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");
        removeOperations.createTestData();
        emp = removeOperations.selectEmployee();
        insId = emp.getInsurance() == null ? null : emp.getInsurance().getId();
        result.append("Remove an insurance and nullify the relationship to the insurance.<br>");
        removeOperations.doRemoveRight(emp);
        result.append("Print insurance:<br>");
        removeOperations.printInsurance(insId, result);
        removeOperations.clearTestData();

        return result.toString();
    }
}
