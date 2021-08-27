package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
	@Autowired
	private SpeakerRepository speakerRepository;

	@GetMapping("/list")
	public List<Speaker> getAll() {
		return speakerRepository.findAll();
	}

	@GetMapping("/list/{id}")
	public Speaker getById(@PathVariable Long id) {
		return speakerRepository.getOne(id);
	}

	@PostMapping
	public Speaker create(@RequestBody Speaker speaker) {
		return speakerRepository.saveAndFlush(speaker);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		speakerRepository.deleteById(id);
	}

	@PutMapping("{id}")
	public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
		Speaker existingSpeaker = speakerRepository.getOne(id);
		BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
		return speakerRepository.saveAndFlush(speaker);
	}
}
