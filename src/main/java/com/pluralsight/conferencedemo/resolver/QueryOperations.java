package com.pluralsight.conferencedemo.resolver;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.services.SessionService;

@Component
public class QueryOperations implements GraphQLQueryResolver {

	private SessionService sessionService;

	public QueryOperations(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public List<Session> findAllSessions() {
		return sessionService.findAll();
	}

	public long countSessions() {
		return sessionService.count();
	}

}
/**
 * The QueryOperations implement a query resolver, which will use our repository
 * to query the database. The query resolver allows Spring to automatically
 * detect and call the right method in response to one of the GraphQL queries
 * defined inside of a schema. Notice we have the implementation for the
 * findAllApplications operation and the countApplications operation. If you
 * remember from the GraphQL schema within our query section, we had these two
 * operations, findAllSessions, countSessions. Notice for the find all sessions,
 * it's a simple call out to our sessionRepository to find all of the sessions.
 * The query response is typically sent using JSON.
 */
