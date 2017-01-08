package de.akquinet.jpapitfalls.experiment.query.isnull;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 28.12.16.
 */
public class IsNullQueryExperiment implements Experiment {

    @EJB
    private IsNullQueryOperations isNullQueryOperations;

    @Override
    public String getId() {
        return "query : (2) isNull";
    }

    @Override
    public String getName() {
        return "Query using IS NULL";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that non existent relationships must be checked using IS NULL.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");

        isNullQueryOperations.createTestData();

        isNullQueryOperations.runParameterQuery(result);

        result.append("<br><i>Doing it the right way:</i><br>");

        isNullQueryOperations.runIsNullQuery(result);

        isNullQueryOperations.clearTestData();

        return result.toString();
    }
}
