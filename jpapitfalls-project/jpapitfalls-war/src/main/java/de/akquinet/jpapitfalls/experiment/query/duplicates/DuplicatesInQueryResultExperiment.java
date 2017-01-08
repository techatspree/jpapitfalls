package de.akquinet.jpapitfalls.experiment.query.duplicates;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 19.12.2016.
 */
public class DuplicatesInQueryResultExperiment implements Experiment {

    @EJB
    private DuplicatesInQueryResultOperations duplicatesInQueryResultOperations;

    @Override
    public String getId() {
        return "query : (1) duplicatesInQueryResult";
    }

    @Override
    public String getName() {
        return "Duplicate instances in query result";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that some JPQL queries might unexpectedly " +
               "include duplicates in the result.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");

        duplicatesInQueryResultOperations.createTestData();

        duplicatesInQueryResultOperations.runQueryWithDuplicates(result);

        result.append("<br><i>Doing it the right way:</i><br>");

        duplicatesInQueryResultOperations.runQueryWithoutDuplicates(result);

        duplicatesInQueryResultOperations.clearTestData();

        return result.toString();
    }
}
