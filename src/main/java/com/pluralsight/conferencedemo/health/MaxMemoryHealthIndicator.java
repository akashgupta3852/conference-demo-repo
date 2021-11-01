package com.pluralsight.conferencedemo.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MaxMemoryHealthIndicator implements HealthIndicator {

//	@Override
//	public Health health() {
//		boolean valid = Runtime.getRuntime().maxMemory() > (2097152005);
//		System.out.println("Status is " + valid);
//		System.out.println(Runtime.getRuntime().maxMemory());
//		Status status = valid ? Status.UP : Status.DOWN;
//		return Health.status(status).build();
//	}

	private final String message_key = "Memory";

	@Override
	public Health health() {
		if (!isMemoryAvailable())
			return Health.down().withDetail(message_key, "Not Available").build();
		return Health.up().withDetail(message_key, "Available").build();
	}

	public Boolean isMemoryAvailable() {
		return Runtime.getRuntime().maxMemory() > (2097152005);
	}
}
/**
 * Remember that our demo application utilizes an in-memory database to store
 * its to-do lists and items. If the amount of memory configured for the JVM is
 * not big enough to store our in-memory database, we'll definitely have
 * problems with our app. Ensuring that our application is started with an
 * adequate amount of memory is critical to its health. We can capture this by
 * writing our own custom HealthIndicator. First, create a new package inside
 * the main package called health. In the new package, we'll create a new class
 * called MaxMemoryHealthIndicator. Annotate the class with the @Component
 * annotation, then implement the HealthIndicator interface along with its one
 * method, health. In the health method, we'll retrieve the maximum configured
 * memory by calling Runtime.getRuntime .maxMemory. We can then compare this to
 * an arbitrary value. For demo purposes, we'll use 100 MB. We set the value of
 * this to a Boolean to indicate whether or not we have a valid amount of
 * memory. Then we create a new status object called status and return
 * status.down if don't have enough memory or status.up if we do. Lastly, we
 * used a health class to build and return a new health instance with our
 * particular status set by calling Health.status and then passing in the status
 * followed by build. Before we start our application, it'll be helpful to
 * configure a property so that we can see the details in the response of our
 * health endpoint call. By default, no details are provided. Navigate to the
 * src, main, resources folder and open up the application.properties. In the
 * application.properties, add a new property, management.endpoint .health .show
 * -details and equals always. Now typically you wouldn't want to set this to
 * always unless you had an adequately secure health endpoint via other means.
 * However, for demo purposes, it's okay for now. Alright, now we're ready to
 * start that application up and try things out. Right-click on the main
 * application class and go to Run. Fire up a browser and visit
 * localhost:8080/actuator/health. In the response, you can see that we get a
 * much more detailed response than what we previously saw where it was just up
 * or down. We could also see that there is now a check called maxMemory and it
 * has a status of UP indicating that our application was started with enough
 * memory.
 */
