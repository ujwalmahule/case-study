package com.ujwal.mentorondemand.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ujwal.mentorondemand.validator.ValidateMentor;

@ValidateMentor
@Entity
@Table(name = "Mentor")
public class Mentor {
	
	@Id
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id", updatable = false, nullable = false)
	@MapsId
	private User userProfile;
	
	@Column(name = "experience")
	private int experience;
	
	@Column(name = "linkedIn")
	private String linkedIn;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "verified")
	private boolean verified;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mentor")
	private List<MentorSkill> mentorSkills;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mentor")
	private List<MentorCalendar> calendar;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mentor")
	private List<MentorCourse> courses;
	
	public User getUserProfile() {
		return userProfile;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<MentorSkill> getMentorSkills() {
		return mentorSkills;
	}

	public List<MentorCalendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<MentorCalendar> calendar) {
		this.calendar = calendar;
	}

	public void setUserProfile(User userProfile) {
		this.userProfile = userProfile;
	}

	public void setMentorSkills(List<MentorSkill> mentorSkills) {
		this.mentorSkills = mentorSkills;
	}
}
