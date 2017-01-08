package de.akquinet.jpapitfalls.experiment.query.outerjoin;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 28.12.16.
 */
public class OuterJoinQueryExperiment implements Experiment {

    @EJB
    private OuterJoinQueryOperations outerJoinQueryOperations;

    @Override
    public String getId() {
        return "query : (3) outer join";
    }

    @Override
    public String getName() {
        return "Outer Join Query";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that you should use LEFT OUTER JOIN for " +
               "navigating relationships that might be null.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");

        outerJoinQueryOperations.createTestData();

        outerJoinQueryOperations.runNavigationQuery(result);

        result.append("<br><i>Doing it the right way:</i><br>");

        outerJoinQueryOperations.runOuterJoinQuery(result);

        outerJoinQueryOperations.clearTestData();

        return result.toString();
    }
}
