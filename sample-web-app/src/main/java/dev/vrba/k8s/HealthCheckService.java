package dev.vrba.k8s;

import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
public class HealthCheckService {

    private HealthStatus status = HealthStatus.HEALTHY;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @NonNull
    public boolean isHealthy() {
        return status == HealthStatus.HEALTHY;
    }

    @NonNull
    public HealthStatus getHealthStatus() {
        return status;
    }

    public void setUnhealthyForSeconds(int seconds) {
        status = HealthStatus.UNHEALTHY;
        scheduler.schedule(() -> status = HealthStatus.HEALTHY, seconds, TimeUnit.SECONDS);
    }
}
