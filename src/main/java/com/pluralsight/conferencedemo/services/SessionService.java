package com.pluralsight.conferencedemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.conferencedemo.exception.SessionException;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	public List<Session> findAll() {
		return sessionRepository.findAll();
	}

	public Session getById(Long id) throws SessionException {
		Optional<Session> session = sessionRepository.findById(id);
		if (session.isPresent())
			return session.get();
		throw new SessionException("Session not found for this ID: " + id);
	}

	public Session create(Session session) {
		return sessionRepository.saveAndFlush(session);
	}

	public void deleteById(Long id) throws SessionException {
		this.getById(id);
		sessionRepository.deleteById(id);
	}

	public Session update(Long id, Session session) throws SessionException {
		this.getById(id);
		session.setSessionId(id);
		return sessionRepository.saveAndFlush(session);
	}

	public long count() {
		return sessionRepository.count();
	}

	// Below methods are created for GraphQL and getById is over written because, we
	// have to pass invalid id in the exception constructor
	public Session createSession(String sessionName, String sessionDescription, Integer sessionLength) {
		Session session = new Session(sessionName, sessionDescription, sessionLength);
		return sessionRepository.saveAndFlush(session);
	}

	public Session getByIdUsingGraph(Long id) throws SessionException {
		Optional<Session> session = sessionRepository.findById(id);
		if (session.isPresent())
			return session.get();
		throw new SessionException("Session not found for this ID: " + id, id);
	}

	public void deleteByIdUsingGraph(Long id) throws SessionException {
		this.getByIdUsingGraph(id);
		sessionRepository.deleteById(id);
	}

	public Session updateSessionDescription(Long id, String sessionDescription) throws SessionException {
		Session session = this.getByIdUsingGraph(id);
		session.setSessionDescription(sessionDescription);
		return sessionRepository.saveAndFlush(session);
	}

}
