package com.ujwal.mentorondemand.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course_template_status")
public class CourseTemplateStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "description", unique = true, nullable = false)
	private String desc;

	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<CourseTemplate> courseDetails;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<CourseTemplate> getCourseDetails() {
		return courseDetails;
	}

	public void setCourseDetails(List<CourseTemplate> courseDetails) {
		this.courseDetails = courseDetails;
	}
	
}
