package de.akquinet.jpapitfalls.experiment.serializedcollection;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.Stateless;

@Stateless
public class SerializedCollectionExperiment implements Experiment {
    @Override
    public String getId() {
        return "serializedcollection";
    }

    @Override
    public String getName() {
        return "Serialized Collection";
    }

    @Override
    public String getDescription() {
        return "tbd";
    }

    @Override
    public String executeExperiment() {

        return "tbd";
    }
}
