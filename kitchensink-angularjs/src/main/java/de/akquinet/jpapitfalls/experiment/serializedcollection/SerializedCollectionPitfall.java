package de.akquinet.jpapitfalls.experiment.serializedcollection;

import de.akquinet.jpapitfalls.experiment.JpaPitfall;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class SerializedCollectionPitfall implements JpaPitfall{
    @Override
    public String getId() {
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
