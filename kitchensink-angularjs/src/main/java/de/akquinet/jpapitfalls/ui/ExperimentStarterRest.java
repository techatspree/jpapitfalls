package de.akquinet.jpapitfalls.ui;

import de.akquinet.jpapitfalls.experiment.JpaPitfall;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static de.akquinet.jpapitfalls.ui.ExperimentDto.*;

@Path("experiment")
public class ExperimentStarterRest {

    @Inject @Any
    private Instance<JpaPitfall> jpaPitfalls;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExperimentDto[] getAllExperiments() {
        return StreamSupport
                .stream(jpaPitfalls.spliterator(), false)
                .map(jpaPitfall -> ExperimentDto
                        .builder()
                        .id(jpaPitfall.getId())
                        .description(jpaPitfall.getDescription())
                        .build())
                .toArray(ExperimentDto[]::new);
    }
}
