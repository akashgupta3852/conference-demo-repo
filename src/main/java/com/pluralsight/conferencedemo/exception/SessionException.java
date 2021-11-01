package com.pluralsight.conferencedemo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class SessionException extends Exception implements GraphQLError {

	private static final long serialVersionUID = 1L;

	public SessionException(String exceptionMessage) {
		super(exceptionMessage);
	}

	private Map<String, Object> extensions = new HashMap<>();

	public SessionException(String exceptionMessage, Long invalidSessionId) {
		super(exceptionMessage);
		extensions.put("invalid_session_id", invalidSessionId);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		return null;
	}

}
