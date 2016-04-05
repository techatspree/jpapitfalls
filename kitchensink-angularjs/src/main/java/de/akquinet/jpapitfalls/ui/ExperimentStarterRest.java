package de.akquinet.jpapitfalls.ui;

import de.akquinet.jpapitfalls.experiment.Experiment;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Path("/experiment")
public class ExperimentStarterRest {

    private static Logger LOG = Logger.getLogger(ExperimentStarterRest.class);

    @Inject @Any
    private Instance<Experiment> experiments;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExperimentDto[] getAllExperiments() {
        return experimentsAsStream()
                .map(experiment -> ExperimentDto
                        .builder()
                        .id(experiment.getId())
                        .name(experiment.getName())
                        .description(experiment.getDescription())
                        .build())
                .toArray(ExperimentDto[]::new);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ExperimentResultDto runExperiment(@PathParam("id") String experimentId) {
        LOG.debugf("Execute experiment %s", experimentId);

        final Optional<Experiment> experimentOptional = experimentsAsStream()
                .filter(experiment -> experiment.getId().equals(experimentId))
                .findFirst();

        return experimentOptional.isPresent() ?
                createResult(experimentOptional.get().executeExperiment()) :
                createResult("Unknown experiment with id " + experimentId + "!");
    }

    private ExperimentResultDto createResult(String description) {
        return ExperimentResultDto
                .builder()
                .description(description)
                .build();
    }

    private Stream<Experiment> experimentsAsStream() {
        return StreamSupport
                .stream(experiments.spliterator(), false);
    }

}
