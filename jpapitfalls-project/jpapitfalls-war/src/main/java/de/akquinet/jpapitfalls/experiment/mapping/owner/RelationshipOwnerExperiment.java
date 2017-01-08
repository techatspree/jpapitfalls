package de.akquinet.jpapitfalls.experiment.mapping.owner;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 26.12.16.
 */
public class RelationshipOwnerExperiment implements Experiment {

    @EJB
    private RelationshipOwnerOperations relationshipOwnerOperations;

    @Override
    public String getId() {
        return "mapping : (4) relationship owner";
    }

    @Override
    public String getName() {
        return "Relationship Owner";
    }

    @Override
    public String getDescription() {
        return "This experiment shows that bidirectional relationships are persisted " +
                "based on the owning side of the relationship.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");

        Long deptId = relationshipOwnerOperations.createDepartment();
        relationshipOwnerOperations.addEmployee(deptId);
        relationshipOwnerOperations.printEmployeesOfDepartment(deptId, result);
        relationshipOwnerOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");

        deptId = relationshipOwnerOperations.createDepartment();
        relationshipOwnerOperations.setDepartment(deptId);
        relationshipOwnerOperations.printEmployeesOfDepartment(deptId, result);
        relationshipOwnerOperations.clearTestData();

        return result.toString();
    }
}