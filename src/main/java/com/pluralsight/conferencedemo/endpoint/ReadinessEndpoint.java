package com.pluralsight.conferencedemo.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// Don't forget to expose the endpoint
@Component
@Endpoint(id = "readiness") // This ID will be used in the path to access our endpoint.
public class ReadinessEndpoint {

	private String ready = "NOT_READY";

	// When we call the path /actuator/readiness, this is the method that will get
	// invoked
	@ReadOperation
	public String getReadiness() {
		return ready;
	}

	// This method will execute and set the status to ready when Spring sends the
	// ApplicationReadyEvent after starting up.
	@EventListener(ApplicationReadyEvent.class) // For demo purpose
	public void setReadiness() { // This method will set whether or not the application is ready to serve traffic
		// We can imagine that there could be some complex logic here to determine
		// whether or not the app is ready.
		ready = "READY";
	}

}

/**
 * Spring Boot Actuator comes with a built-in endpoint for checking health as
 * we've seen throughout the course, but it doesn't provide a check for
 * readiness. So you're probably wondering what exactly is readiness and
 * readiness determines whether the application is able to receive traffic. An
 * application may be healthy, but not ready to receive traffic. Let's write a
 * custom actuator endpoint that determines readiness. First, create a new
 * package inside the main package called endpoint. In the new package, create a
 * new class called ReadinessEndpoint. Annotate the class with the @Component
 * annotation and the @Endpoint annotation. Set the ID attribute of
 * the @Endpoint annotation to readiness. This ID will then be used in the path
 * to access our endpoint. Create a new string instance variable called ready
 * and initialize it to the String NOT_READY. Next, add a new method called
 * getReadiness that returns a string. Annotate the method with
 * the @ReadOperation annotation and return the instance variable indicating
 * whether or not the application is ready. When we call the path
 * /actuator/readiness, this is the method that will get invoked. Right now, our
 * endpoint is always returning NOT_READY. Let's create a new method called
 * setReady that returns void. This method will set whether or not the
 * application is ready to serve traffic. You can imagine that there could be
 * some complex logic here to determine whether or not the app is ready. We'll
 * just be doing a simple check for our demo. Annotate the method with
 * the @EventListener annotation and use the ApplicationReadyEvent class as the
 * value. In the body of the method, change the instance variable ready to the
 * value of ready. This method will execute and set the status to ready when
 * Spring sends the ApplicationReadyEvent after starting up. We're almost ready
 * to give our new actuator endpoint a try, but don't forget that we need to
 * expose the endpoint. Navigate to the src, main, resources, and open up the
 * application.properties. On the property that controls which endpoints are
 * exposed, add a comma to the end of the value and type readiness. We're now
 * ready to start our application. Navigate to the main application class,
 * right-click, and go to Run. Once the application is started, fire up a
 * browser and visit localhost:8090/actuator/readiness. If everything went as
 * planned, you'll get back a ready response indicating that the application is
 * ready to receive traffic.
 */
