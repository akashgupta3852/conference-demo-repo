package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name = "speakers")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Speaker  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long speakerId;
	private String firstName;
	private String lastName;
	private String title;
	private String company;
	private String speakerBio;

	@ManyToMany(mappedBy = "speakers")
	@JsonIgnore
	private List<Session> sessions;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] speakerPhoto;

	public Speaker() {
	}

	public Long getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(Long speakerId) {
		this.speakerId = speakerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSpeakerBio() {
		return speakerBio;
	}

	public void setSpeakerBio(String speakerBio) {
		this.speakerBio = speakerBio;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public byte[] getSpeakerPhoto() {
		return speakerPhoto;
	}

	public void setSpeakerPhoto(byte[] speakerPhoto) {
		this.speakerPhoto = speakerPhoto;
	}
}
