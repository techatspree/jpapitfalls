package de.akquinet.jpapitfalls.experiment.runtime.ordering;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class OrderingInDBExperiment implements Experiment {
    @EJB
    private OrderingInDBOperations orderingInDBOperations;

    @Override
    public String getId() {
        return "runtime : (2) orderingInDB";
    }

    @Override
    public String getName() {
        return "Ordering in DB rather than in memory";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that ordering in DB is preferable over ordering in memory.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        orderingInDBOperations.createTestData();
        result.append("Sort query result in memory.<br>");
        long before = System.currentTimeMillis();
        orderingInDBOperations.sortInMemory();
        long after = System.currentTimeMillis();
        result.append("Selecting instances took " + (after-before) + "ms");
        orderingInDBOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");
        orderingInDBOperations.createTestData();
        result.append("Sort query result in DB.<br>");
        before = System.currentTimeMillis();
        orderingInDBOperations.sortInDB();
        after = System.currentTimeMillis();
        result.append("Selecting instances took " + (after-before) + "ms");
        orderingInDBOperations.clearTestData();

        return result.toString();
    }
}
