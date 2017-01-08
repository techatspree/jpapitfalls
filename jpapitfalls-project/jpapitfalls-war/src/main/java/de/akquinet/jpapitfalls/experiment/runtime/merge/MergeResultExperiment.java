package de.akquinet.jpapitfalls.experiment.runtime.merge;

import de.akquinet.jpapitfalls.experiment.Experiment;
import de.akquinet.jpapitfalls.experiment.model.company.Employee;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 31.12.16.
 */
public class MergeResultExperiment implements Experiment {
    @EJB
    private MergeResultOperations mergeResultOperations;

    @Override
    public String getId() {
        return "runtime : (4) merge result";
    }

    @Override
    public String getName() {
        return "EM.merge returns the merged instance.";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that you must not ignore the return value of EM.merge.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        mergeResultOperations.createTestData();
        Employee emp = mergeResultOperations.selectEmployee();
        emp.setFirstname("Detached");
        mergeResultOperations.doMergeWrong(emp);
        result.append("Print merge employee");
        mergeResultOperations.printEmployee(emp.getId(), result);
        mergeResultOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");
        mergeResultOperations.createTestData();
        emp = mergeResultOperations.selectEmployee();
        emp.setFirstname("Detached");
        mergeResultOperations.doMergeRight(emp);
        result.append("Print merge employee");
        mergeResultOperations.printEmployee(emp.getId(), result);
        mergeResultOperations.clearTestData();

        return result.toString();
    }
}
