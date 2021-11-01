package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.exception.SessionException;
import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.services.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

	@Autowired
	private SessionService sessionService;

	@GetMapping("/list")
	public List<Session> getAll() {
		return sessionService.findAll();
	}

	@GetMapping("/list/{id}")
	public Session getById(@PathVariable Long id) {
		try {
			return sessionService.getById(id);
		} catch (SessionException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED) // it gives 201 when the method executes and finished
	public Session create(@RequestBody Session session) {
		return sessionService.create(session);
		// save methods only save the data but not committed to databases
		// we use saveAndFlush methods for saving and committing it to databases
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		// Also needs to check for children record before deleting
		try {
			sessionService.deleteById(id);
			return true;
		} catch (SessionException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("{id}")
	public Session update(@PathVariable Long id, @RequestBody Session session) {
		// Because this is a PUT, we expect all attributes to be passed in. A PATCH
		// would only need what has changed.
		// TODO: Add validations that all attributes are passed in, otherwise return a
		// 400 bad payload
		try {
			return sessionService.update(id, session);
		} catch (SessionException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// http://localhost:8082/api/v1/sessions/x/id?aa=9
	// "The id is 9"
	@GetMapping("x/id")
	public String getById(@RequestParam String aa) {
		return "The id is " + aa;
	}

	// http://localhost:8082/api/v1/sessions/y/id?personId=k
	// "The person id is k"
	@GetMapping("y/id")
	public String getByPersonId(@RequestParam("personId") String bb) {
		return "The person id is " + bb;
	}

	// http://localhost:8082/api/v1/sessions/name?person=akash
	// "The person name is akash"
	// http://localhost:8082/api/v1/sessions/name
	// "The person name is null"
	// http://localhost:8082/api/v1/sessions/name?msfba
	// "The person name is null"
	@GetMapping(value = "/name")
	public String getName(@RequestParam(value = "person", required = false) String personName) {
		return "The person name is " + personName;
	}

	// http://localhost:8082/api/v1/sessions/names?personName=akash
	// "The person name is akash"
	// http://localhost:8082/api/v1/sessions/names?msfba
	// "Bad request"
	@GetMapping(value = "/names")
	public String getNames(@RequestParam String personName) {
		return "The person name is " + personName;
	}

}
