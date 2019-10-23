package com.ujwal.mentorondemand.id;

import java.io.Serializable;

public class StudentCourseId implements Serializable{

	private static final long serialVersionUID = 3724015187938145754L;
	
	private long studentId;
	private long courseId;
	
	public StudentCourseId() {
		// default constructor
	}
	
	public StudentCourseId(long studentId, long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		result = prime * result + (int) (courseId ^ (courseId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCourseId other = (StudentCourseId) obj;
		if (studentId != other.studentId)
			return false;
		if (courseId != other.courseId)
			return false;
		return true;
	}
	
}
