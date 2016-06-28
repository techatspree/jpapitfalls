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
        return "This experiment shows that serialized collections may be falsely used as relational associations.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");

        final Long fieldOfStudyWrongId =
                serializedCollectionsOperations.createFieldOfStudyAndStudentsTheWrongWay();

        serializedCollectionsOperations
                .executeWrongExperiment(fieldOfStudyWrongId, result, this);

        result.append("<br><i>Doing it the right way:</i><br>");

        final Long fieldOfStudyRightId =
                serializedCollectionsOperations.createFieldOfStudyAndStudentsTheRightWay();

        serializedCollectionsOperations
                .executeRightExperiment(fieldOfStudyRightId, result, this);

        return result.toString();
    }

}
