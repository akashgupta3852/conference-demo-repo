package com.pluralsight.conferencedemo.actuator.endpoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

	@Override
	public Health health() {
		// perform a custom health check

		// inspect the status

		// healthy
		// return Health.up().build();

		// unhealthy
		// return Health.down().build();

		// unhealthy with failed reasons\

		System.out.println("bbb");
		return Health.up().withDetail("response_code", getClass()).withDetail("response_ms", getClass())
				.withDetail("num_entries", getClass()).build();
	}

}

/**
 * The health endpoint allows for adding additional custom health checks to the
 * already built-in health checks, and the great thing is is that it's really
 * easy to do so. Simply create and register a new bean that implements the
 * HealthIndicator interface and implement its one method, the health method,
 * with any custom logic. The health method returns a health object which is
 * used to indicate the appropriate status, such as up or down. Let's take a
 * look at some example code. We first create a new class annotated with
 * the @Component annotation so our class is registered as a bean and we
 * implement the HealthIndicator interface. Again, the HealthIndicator interface
 * requires you to implement one method, the health method, and in this method,
 * you would perform the custom health check. For instance, maybe you have a
 * legacy system that your application depends on. You could write code to
 * perform a check on the legacy system, inspect its status, and then hopefully
 * return a healthy status. Alternatively, if after inspecting the status you
 * determine that the system wasn't healthy, you could return a new health
 * object using the down method to indicate that it's in an unhealthy state. The
 * health object also provides the ability to add additional details. This could
 * be useful for returning details about a healthy response, but it's
 * particularly useful for returning additional details about an unhealthy
 * response. For instance, we could add what response code we received or how
 * long the response took or the number of times that we retried. This would all
 * be useful information when inspecting why the health check failed.
 */