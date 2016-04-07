package de.akquinet.jpapitfalls.ui;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExperimentDto {
    String id;
    String name;
    String description;
}
