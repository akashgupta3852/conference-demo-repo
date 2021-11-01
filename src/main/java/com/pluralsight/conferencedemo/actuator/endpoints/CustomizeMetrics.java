package com.pluralsight.conferencedemo.actuator.endpoints;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Service
public class CustomizeMetrics {

	private Timer timer;

	public CustomizeMetrics(MeterRegistry meterRegistry) {
		// give the timer a name
		timer = meterRegistry.timer("long.running.up.timer");
	}

	public void longRunningOperation() {
		timer.record(() -> {
			// a long running operation
			for (int i = 0; i < 900000; i++) {
				System.out.println("hffhgj");
			}
		});
	}

}

/**
 * We can customize metrics endpoint by adding our own metrics to the existing
 * metrics. There is a couple of different ways to customize the metrics
 * endpoint, but the easiest is to inject a MeterRegistry into the class that's
 * generating the metrics and then use or record the metric in that class. Let's
 * take a look at some example code. Suppose we had a really complex service
 * that had a long-running operation. We know that the method takes a long time
 * to execute and we want to regularly record how long so we can keep track of
 * it over time and make sure it's not getting worse. We can do that by
 * injecting a meter registry and creating a new named timer metric. We can then
 * use that timer to record the execution time taken by that method. The
 * MeterRegistry class comes from the micrometer library, which is a technology
 * agnostic library for collecting metrics. It supports things like timers which
 * we just used, as well as other metric collectors, such as counters or gauges.
 */
