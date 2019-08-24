package com.ujwal.mentorondemand.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.ujwal.mentorondemand.id.StudentCourseId;

@Entity
@IdClass(StudentCourseId.class)
@Table(name = "Student_Course")
public class StudentCourse {
	
	@Id
	@Column(name = "course_id")
	private long courseId;
	
	@Id
	@Column(name = "student_id")
	private long studentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private User student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id")
	@MapsId
	private Payment payment;
	
	@Column(name = "status")
	@Max(100)
	@Min(0)
	private int status;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
