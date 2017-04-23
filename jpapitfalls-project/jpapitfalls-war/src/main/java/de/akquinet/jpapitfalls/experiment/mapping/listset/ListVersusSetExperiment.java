package de.akquinet.jpapitfalls.experiment.mapping.listset;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 29.12.16.
 */
public class ListVersusSetExperiment implements Experiment {

    @EJB
    private ListVersusSetOperations listVersusSetOperations;

    @Override
    public String getId() {
        return "mapping : (2) list vs. set";
    }

    @Override
    public String getName() {
        return "Multi-valued Relationship of type List or Set";
    }

    @Override
    public String getDescription() {
        return "This experiment shows the difference using a list or a set " +
                "as type of in a multi-valued relationship.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        Long deptListId = listVersusSetOperations.createListInstances();
        System.gc();
        long before = System.currentTimeMillis();
        listVersusSetOperations.addToList(deptListId);
        long after = System.currentTimeMillis();
        result.append("Adding ").append(ListVersusSetOperations.NR_OF_INSTANCES_TO_ADD).
               append(" instances to the list relationship took ").
               append(after-before).append("ms");
        listVersusSetOperations.clearTestData();
        result.append("<br>");

        result.append("<br><i>Doing it the right way:</i><br>");
        Long deptSetId = listVersusSetOperations.createSetInstances();
        System.gc();
        before = System.currentTimeMillis();
        listVersusSetOperations.addToSet(deptSetId);
        after = System.currentTimeMillis();
        result.append("Adding ").append(ListVersusSetOperations.NR_OF_INSTANCES_TO_ADD).
               append(" instances to the set relationship took ").
               append(after-before).append("ms");
        listVersusSetOperations.clearTestData();

        return result.toString();
    }
}