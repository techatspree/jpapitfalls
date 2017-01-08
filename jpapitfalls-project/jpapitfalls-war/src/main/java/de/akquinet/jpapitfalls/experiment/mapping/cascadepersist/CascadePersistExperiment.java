package de.akquinet.jpapitfalls.experiment.mapping.cascadepersist;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 26.12.16.
 */
public class CascadePersistExperiment implements Experiment {

    @EJB
    private CascadePersistOperations cascadePersistOperations;

    @Override
    public String getId() {
        return "mapping : (3) cascade=persist";
    }

    @Override
    public String getName() {
        return "Reachabiliy with cascade=persist";
    }

    @Override
    public String getDescription() {
        return "This experiment shows the effect of annotating a relationship with " +
                "cascade=persist with respect to reachability.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");

        cascadePersistOperations.createNonCascade(result);
        cascadePersistOperations.printNonCascadeInstances(result);
        cascadePersistOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");

        cascadePersistOperations.createCascadePersist(result);
        cascadePersistOperations.printCascadePersistInstances(result);
        cascadePersistOperations.clearTestData();

        return result.toString();
    }
}