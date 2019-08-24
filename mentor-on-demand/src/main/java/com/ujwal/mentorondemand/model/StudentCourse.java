package com.ujwal.mentorondemand.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.ujwal.mentorondemand.id.StudentCourseId;

@Entity
@IdClass(StudentCourseId.class)
@Table(name = "Mentor_Skill")
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
	
	@Column(name = "status")
	@Max(100)
	@Min(0)
	private int status;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "studentId", referencedColumnName = "userId"),
		@JoinColumn(name = "courseId", referencedColumnName = "courseId")
	})
	private List<Payment> payments;
}
