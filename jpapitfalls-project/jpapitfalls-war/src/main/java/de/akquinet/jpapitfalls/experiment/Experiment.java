package de.akquinet.jpapitfalls.experiment;

public interface Experiment {
    String getId();
    String getName();
    String getDescription();
    String executeExperiment();
}
