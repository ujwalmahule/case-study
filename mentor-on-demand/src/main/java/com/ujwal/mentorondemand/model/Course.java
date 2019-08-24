package com.ujwal.mentorondemand.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ujwal.mentorondemand.validator.ValidateMentor;

@ValidateMentor
@Entity
@Table(name = "Course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "fee", nullable = false)
	private double fee;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date", nullable = false)
	private Date start_date;
	
	@Lob
	@Column(name = "toc", nullable = false)
	private String toc;
	
	@Column(name = "comission", nullable = false)
	private float comission;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
	private List<MentorSkill> courseSkills;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
	private List<MentorCalendar> calendar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getToc() {
		return toc;
	}

	public void setToc(String toc) {
		this.toc = toc;
	}

	public float getComission() {
		return comission;
	}

	public void setComission(float comission) {
		this.comission = comission;
	}

	public List<MentorSkill> getCourseSkills() {
		return courseSkills;
	}

	public void setCourseSkills(List<MentorSkill> courseSkills) {
		this.courseSkills = courseSkills;
	}

	public List<MentorCalendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<MentorCalendar> calendar) {
		this.calendar = calendar;
	}
	
}
