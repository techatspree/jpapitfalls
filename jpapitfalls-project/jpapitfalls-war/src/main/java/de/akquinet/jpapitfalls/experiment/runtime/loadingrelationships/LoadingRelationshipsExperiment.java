package de.akquinet.jpapitfalls.experiment.runtime.loadingrelationships;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class LoadingRelationshipsExperiment implements Experiment {
    @EJB
    private LoadingRelationshipsOperations loadingRelationshipsOperations;

    @Override
    public String getId() {
        return "runtime : (3) loading relationships";
    }

    @Override
    public String getName() {
        return "Loading Multiple Relationships";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that loading relationships in a single query " +
                "is preferable over navigation in memory.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        loadingRelationshipsOperations.createTestData();
        result.append("Select employees and navigate to related instances to retrieve their values.<br>");
        long before = System.currentTimeMillis();
        loadingRelationshipsOperations.selectRootObjectsPlusRelationshipNavigation();
        long after = System.currentTimeMillis();
        result.append("Selecting instances took " + (after-before) + "ms");
        loadingRelationshipsOperations.clearTestData();
        result.append("<br>");

        result.append("<br><i>Doing it the right way:</i><br>");
        loadingRelationshipsOperations.createTestData();
        result.append("Select all values using single JOIN query.<br>");
        before = System.currentTimeMillis();
        loadingRelationshipsOperations.selectUsingJoinQuery();
        after = System.currentTimeMillis();
        result.append("Selecting instances took " + (after-before) + "ms");
        loadingRelationshipsOperations.clearTestData();
        return result.toString();
    }
}
