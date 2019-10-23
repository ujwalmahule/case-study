package com.ujwal.mentorondemand.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.ujwal.mentorondemand.id.CourseSkillId;

@Entity
@IdClass(CourseSkillId.class)
@Table(name = "Course_Skill")
public class CourseSkill {
	
	@Id
	@Column(name = "course_id")
	private long courseId;
	
	@Id
	@Column(name = "skill_id")
	private long skillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Skill skill;
	
	@Lob
	@Column(name = "toc", nullable = false)
	private String toc;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getToc() {
		return toc;
	}

	public void setToc(String toc) {
		this.toc = toc;
	}
	
}
