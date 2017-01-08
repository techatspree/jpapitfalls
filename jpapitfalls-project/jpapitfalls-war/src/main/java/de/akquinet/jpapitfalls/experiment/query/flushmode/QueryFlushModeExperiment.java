package de.akquinet.jpapitfalls.experiment.query.flushmode;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class QueryFlushModeExperiment implements Experiment {
    @EJB
    private QueryFlushModeOperations queryFlushModeOperations;

    @Override
    public String getId() {
        return "query : (5) flush mode";
    }

    @Override
    public String getName() {
        return "Unexpected query result with FlushModeType.COMMIT";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that query result might include instances not qualifying " +
                "the query filter when using FlushModeType.COMMIT.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        queryFlushModeOperations.createTestData();
        queryFlushModeOperations.runQueryWithFlushModeCommit(result);
        queryFlushModeOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");
        queryFlushModeOperations.createTestData();
        queryFlushModeOperations.runQueryWithFlushModeAuto(result);
        queryFlushModeOperations.clearTestData();
        return result.toString();
    }
}
