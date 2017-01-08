package de.akquinet.jpapitfalls.experiment.runtime.iteration;

import de.akquinet.jpapitfalls.experiment.Experiment;
import de.akquinet.jpapitfalls.experiment.runtime.loadingrelationships.LoadingRelationshipsOperations;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class IterationExperiment implements Experiment {
    @EJB
    private IterationOperations iterationOperations;

    @Override
    public String getId() {
        return "runtime : (1) iteration";
    }

    @Override
    public String getName() {
        return "Iteration in DB";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that iterating in DB is preferable over iterating in memory.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        iterationOperations.createTestData();
        long before = System.currentTimeMillis();
        iterationOperations.selectIterationInMemory();
        long after = System.currentTimeMillis();
        result.append("Selecting instances took " + (after-before) + "ms");
        iterationOperations.clearTestData();
        result.append("<br>");

        result.append("<br><i>Doing it the right way:</i><br>");
        iterationOperations.createTestData();
        before = System.currentTimeMillis();
        iterationOperations.selectUsingQuery();
        after = System.currentTimeMillis();
        result.append("Selecting instances took " + (after-before) + "ms");
        iterationOperations.clearTestData();

        return result.toString();
    }
}
