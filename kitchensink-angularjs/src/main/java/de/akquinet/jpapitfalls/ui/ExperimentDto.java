package de.akquinet.jpapitfalls.ui;

import lombok.Builder;
import lombok.Value;

/**
 * Created by tnfink on 23.03.16.
 */
@Value
@Builder
public class ExperimentDto {
    String id;
    String description;
}
