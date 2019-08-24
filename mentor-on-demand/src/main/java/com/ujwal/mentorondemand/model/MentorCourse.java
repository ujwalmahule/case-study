package com.ujwal.mentorondemand.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.ujwal.mentorondemand.id.MentorCourseId;

@Entity
@IdClass(MentorCourseId.class)
@Table(name = "Mentor_Course")
public class MentorCourse {
	
	@Id
	@Column(name = "course_id")
	private long courseId;
	
	@Id
	@Column(name = "mentor_id")
	private long mentorId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Mentor mentor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Course course;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getMentorId() {
		return mentorId;
	}

	public void setMentorId(long mentorId) {
		this.mentorId = mentorId;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
