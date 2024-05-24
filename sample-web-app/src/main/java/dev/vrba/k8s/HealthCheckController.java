package dev.vrba.k8s;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/health")
public class HealthCheckController {

    private final HealthCheckService service;

    public HealthCheckController(@NonNull HealthCheckService service) {
        this.service = service;
    }

    @Get
    @NonNull
    public HttpResponse<HealthCheckResult> check() {
        return switch (service.getHealthStatus()) {
            case HEALTHY -> HttpResponse.ok(new HealthCheckResult(true));
            case UNHEALTHY -> HttpResponse.serverError(new HealthCheckResult(false));
        };
    }

    @Get("/unhealthy")
    @NonNull
    public HttpResponse<?> unhealthy() {
        // Simulate downtime for 10-30 seconds
        final var downtime = (int) ((Math.random() * 20) + 10);
        service.setUnhealthyForSeconds(downtime);
        return HttpResponse.ok();
    }
}
