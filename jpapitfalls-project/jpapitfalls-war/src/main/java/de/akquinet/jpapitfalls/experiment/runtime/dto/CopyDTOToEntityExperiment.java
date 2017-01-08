package de.akquinet.jpapitfalls.experiment.runtime.dto;

import de.akquinet.jpapitfalls.experiment.Experiment;

import javax.ejb.EJB;

/**
 * Created by Michael Bouschen on 31.12.16.
 */
public class CopyDTOToEntityExperiment implements Experiment {
    @EJB
    private CopyDTOToEntityOperations copyDTOToEntityOperations;

    @Override
    public String getId() {
        return "runtime : (5) copy dto";
    }

    @Override
    public String getName() {
        return "Copy DTO to Entity.";
    }

    @Override
    public String getDescription() {
        return "This experiment shows how to copy back DTO values into an JPA entity.";
    }

    @Override
    public String executeExperiment() {
        StringBuilder result = new StringBuilder();

        result.append("<br><i>Doing it the wrong way:</i><br>");
        copyDTOToEntityOperations.createTestData();
        CopyDTOToEntityOperations.InsuranceDTO dto = copyDTOToEntityOperations.selectInsurance();
        dto.setCarrier("new name");
        copyDTOToEntityOperations.copyDTOTheWrongWay(dto);
        result.append("Updated insurance and associated employee:");
        copyDTOToEntityOperations.printInsurance(dto.getId(), result);
        copyDTOToEntityOperations.clearTestData();

        result.append("<br><i>Doing it the right way:</i><br>");
        copyDTOToEntityOperations.createTestData();
        dto = copyDTOToEntityOperations.selectInsurance();
        dto.setCarrier("new name");
        copyDTOToEntityOperations.copyDTOTheRightWay(dto);
        result.append("Updated insurance and associated employee:");
        copyDTOToEntityOperations.printInsurance(dto.getId(), result);
        copyDTOToEntityOperations.clearTestData();
        return result.toString();
    }
}
