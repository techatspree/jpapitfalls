package de.akquinet.jpapitfalls.experiment.serializedcollection;

import de.akquinet.jpapitfalls.experiment.Experiment;
import de.akquinet.jpapitfalls.experiment.serializedcollection.model.FieldOfStudy;

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

        final FieldOfStudy fieldOfStudy = serializedCollectionsOperations.createFieldOfStudyAndStudentsTheWrongWay();

        result.append("<b>Doing it the wrong way:</b><br>");

        serializedCollectionsOperations.setAllStudentsToPassed(fieldOfStudy, result);
        serializedCollectionsOperations.listStudentsFromCollectionAndFromDatabase(fieldOfStudy, result);

        result.append("<b>Doing it the right way:</b><br>");

        return result.toString();
    }
}
