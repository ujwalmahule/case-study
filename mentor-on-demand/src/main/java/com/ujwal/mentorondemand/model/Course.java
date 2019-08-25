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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name = "batch_name", nullable = false)
	private String batchName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private CourseTemplate details;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private CourseStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
	private List<CourseSkill> courseSkills;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "course")
	private List<MentorCalendar> calendar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public CourseTemplate getDetails() {
		return details;
	}

	public void setDetails(CourseTemplate details) {
		this.details = details;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}

	public List<CourseSkill> getCourseSkills() {
		return courseSkills;
	}

	public void setCourseSkills(List<CourseSkill> courseSkills) {
		this.courseSkills = courseSkills;
	}

	public List<MentorCalendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<MentorCalendar> calendar) {
		this.calendar = calendar;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
}
