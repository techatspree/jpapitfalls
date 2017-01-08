package de.akquinet.jpapitfalls.experiment.mapping.joined;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 30.12.16.
 */
public class JoinedInheritanceStrategyExperiment implements Experiment {
    @EJB
    private JoinedInheritanceStrategyOperations joinedInheritanceStrategyOperations;

    @Override
    public String getId() {
        return "mapping : (5) joined inheritance";
    }

    @Override
    public String getName() {
        return "JOINED inheritance strategy";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that JOINED inheritance strategy might have drawbacks " +
                "when loading instances.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        joinedInheritanceStrategyOperations.createJoinedInstances();
        System.gc();
        long before = System.currentTimeMillis();
        joinedInheritanceStrategyOperations.selectJoinedInstances();
        long after = System.currentTimeMillis();
        result.append("Selecting employee instances with JOINED inheritance took " + (after-before) + "ms");
        joinedInheritanceStrategyOperations.clearTestData();
        result.append("<br>");

        result.append("<br><i>Doing it the right way:</i><br>");
        joinedInheritanceStrategyOperations.createSingleTableInstances();
        System.gc();
        before = System.currentTimeMillis();
        joinedInheritanceStrategyOperations.selectSingleTableInstances();
        after = System.currentTimeMillis();
        result.append("Selecting employee instances with SINGLE_TABLE inheritance took " + (after-before) + "ms");
        joinedInheritanceStrategyOperations.clearTestData();

        return result.toString();
    }
}
