package com.ujwal.mentorondemand.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.ujwal.mentorondemand.id.MentorSkillId;

@Entity
@IdClass(MentorSkillId.class)
@Table(name = "Mentor_Skill")
public class MentorSkill {
	
	@Id
	@Column(name = "mentor_id")
	private long mentorId;
	
	@Id
	@Column(name = "skill_id")
	private long skillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Mentor mentor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Skill skill;
	
	@Column(name = "self_rating")
	@Max(10)
	@Min(0)
	private int selfRating;
	
	@Column(name = "trainings_delivered")
	@Min(0)
	private int trainingsDelivered;
	
	@Min(0)
	@Column(name = "experience")
	private int experience;
	
	@Column(name = "facilities")
	private String facilities;
	
	public long getMentorId() {
		return mentorId;
	}

	public void setMentorId(long mentorId) {
		this.mentorId = mentorId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public int getSelfRating() {
		return selfRating;
	}

	public void setSelfRating(int selfRating) {
		this.selfRating = selfRating;
	}

	public int getTrainingsDelivered() {
		return trainingsDelivered;
	}

	public void setTrainingsDelivered(int trainingsDelivered) {
		this.trainingsDelivered = trainingsDelivered;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
}
