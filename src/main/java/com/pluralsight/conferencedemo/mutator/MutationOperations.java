package com.pluralsight.conferencedemo.mutator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.pluralsight.conferencedemo.exception.SessionException;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.services.SessionService;

@Component
public class MutationOperations implements GraphQLMutationResolver {

	@Autowired
	private SessionService sessionService;

	public Session newSession(String sessionName, String sessionDescription, Integer sessionLength) {
		return sessionService.createSession(sessionName, sessionDescription, sessionLength);
	}

	public Boolean deleteSession(Long id) throws SessionException {
		sessionService.deleteByIdUsingGraph(id);
		return true;

	}

	public Session updateSessionDescription(Long id, String sessionDescription) throws SessionException {
		return sessionService.updateSessionDescription(id, sessionDescription);
	}

}
/**
 * GraphQL also has the ability to update the data stored on the server by means
 * of mutations. Mutations such as creating, updating, or deleting will change
 * the data, unlike a query. Mutations are defined in the Java code by defining
 * a class that implements GraphQLMutationResolver. Mutation implements
 * GraphQLMutationResolver. The mutation resolver allows Spring to automatically
 * detect and call the right method in response to one of the GraphQL mutations
 * declared inside of the schema. So in the schema for Mutation, there are
 * three, creating a new session, deleting an session, and updating the session
 * description.
 */