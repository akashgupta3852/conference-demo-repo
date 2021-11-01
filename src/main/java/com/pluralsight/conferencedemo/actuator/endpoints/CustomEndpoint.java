package com.pluralsight.conferencedemo.actuator.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomEndpoint {

	@ReadOperation
	public String readMethod() {
		// gather and return information e.g. name of the app, version, or may be some
		// statistics
		return "It has read the information";
	}

	@WriteOperation
	public void writeMethod() {
		// perform some action e.g. restart the app
		System.out.println("Restarted");
	}

}
