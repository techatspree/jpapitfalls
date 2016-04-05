package de.akquinet.jpapitfalls.ui;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static de.akquinet.jpapitfalls.ui.ExperimentDto.*;

/**
 * Created by tnfink on 23.03.16.
 */
@Path("experiment")
public class ExperimentStarterRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExperimentDto[] getAllExperiments() {
        return new ExperimentDto[] {
            builder().id("testid").description("test description").build()
        };
    }
}
