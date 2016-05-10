package de.akquinet.jpapitfalls.experiment.serializedcollection;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@SuppressWarnings("unused")
@Stateless
public class SerializedCollectionExperiment implements Experiment {

    @EJB
    private SerializedCollectionsOperations serializedCollectionsOperations;

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
        StringBuilder result = new StringBuilder();

        final Long fieldOfStudyId = serializedCollectionsOperations.createFieldOfStudyAndStudentsTheWrongWay();

        serializedCollectionsOperations.setAllStudentsToPassed(fieldOfStudyId);

        serializedCollectionsOperations.listStudentsPassedGrade(fieldOfStudyId, result);

        return result.toString();
    }
}
