package dev.vrba.k8s;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.time.OffsetDateTime;

@Controller
public class HomeController {

    private final HealthCheckService service;

    public HomeController(@NonNull HealthCheckService service) {
        this.service = service;
    }

    @Get
    public HttpResponse<CurrentTimeResponse> getCurrentTime() {
        if (!service.isHealthy()) {
            throw new RuntimeException("I am not healthy!");
        }

        return HttpResponse.ok(new CurrentTimeResponse(OffsetDateTime.now()));
    }
}
