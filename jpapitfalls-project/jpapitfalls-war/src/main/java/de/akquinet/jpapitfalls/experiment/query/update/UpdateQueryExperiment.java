package de.akquinet.jpapitfalls.experiment.query.update;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class UpdateQueryExperiment implements Experiment {
    @EJB
    private UpdateQueryOperations updateQueryOperations;

    @Override
    public String getId() {
        return "query : (4) update/delete query";
    }

    @Override
    public String getName() {
        return "UPDATE / DELETE JPQL does not synchronize cache";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that an UPDATE or DELETE JPQL query does not synchronize the EM cache.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Run UPDATE query</i><br>");
        updateQueryOperations.createTestData();
        updateQueryOperations.runUpdateQuery(result);
        updateQueryOperations.clearTestData();

        result.append("<br><i>Run DELETE query</i><br>");
        updateQueryOperations.createTestData();
        updateQueryOperations.runDeleteQuery(result);
        updateQueryOperations.clearTestData();

        return result.toString();
    }
}
