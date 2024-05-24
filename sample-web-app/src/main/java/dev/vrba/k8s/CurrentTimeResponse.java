package dev.vrba.k8s;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.serde.annotation.Serdeable;

import java.time.OffsetDateTime;

@Serdeable
public record CurrentTimeResponse(@NonNull OffsetDateTime currentTime) {
}
