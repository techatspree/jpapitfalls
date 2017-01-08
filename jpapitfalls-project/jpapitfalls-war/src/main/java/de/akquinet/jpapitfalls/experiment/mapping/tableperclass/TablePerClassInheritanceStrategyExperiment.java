package de.akquinet.jpapitfalls.experiment.mapping.tableperclass;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class TablePerClassInheritanceStrategyExperiment implements Experiment {
    @EJB
    private TablePerClassInheritanceStrategyOperations tablePerClassInheritanceStrategyOperations;

    @Override
    public String getId() {
        return "mapping : (6) table per class inheritance";
    }

    @Override
    public String getName() {
        return "TABLE_PER_CLASS inheritance strategy";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that TABLE_PER_CLASS inheritance strategy " +
               "might have drawbacks when navigating relationships.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        tablePerClassInheritanceStrategyOperations.createTablePerClassInstances();
        System.gc();
        long before = System.currentTimeMillis();
        tablePerClassInheritanceStrategyOperations.selectTablePerClassInstances();
        long after = System.currentTimeMillis();
        result.append("Selecting employee instances with TABLE_PER_CLASS inheritance took " + (after-before) + "ms");
        tablePerClassInheritanceStrategyOperations.clearTestData();
        result.append("<br>");

        result.append("<br><i>Doing it the right way:</i><br>");
        tablePerClassInheritanceStrategyOperations.createSingleTableInstances();
        System.gc();
        before = System.currentTimeMillis();
        tablePerClassInheritanceStrategyOperations.selectSingleTableInstances();
        after = System.currentTimeMillis();
        result.append("Selecting employee instances with SINGLE_TABLE inheritance took " + (after-before) + "ms");
        tablePerClassInheritanceStrategyOperations.clearTestData();

        return result.toString();
    }
}
