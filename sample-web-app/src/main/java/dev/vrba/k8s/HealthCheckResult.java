package dev.vrba.k8s;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record HealthCheckResult(boolean healthy) {
}
