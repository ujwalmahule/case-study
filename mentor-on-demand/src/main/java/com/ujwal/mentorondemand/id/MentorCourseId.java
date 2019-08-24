package com.ujwal.mentorondemand.id;

import java.io.Serializable;

public class MentorCourseId implements Serializable{

	private static final long serialVersionUID = 3724015187938145754L;
	
	private long mentorId;
	private long courseId;
	
	public MentorCourseId() {
		// default constructor
	}
	
	public MentorCourseId(long mentorId, long courseId) {
		super();
		this.mentorId = mentorId;
		this.courseId = courseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (mentorId ^ (mentorId >>> 32));
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
		MentorCourseId other = (MentorCourseId) obj;
		if (mentorId != other.mentorId)
			return false;
		if (courseId != other.courseId)
			return false;
		return true;
	}
	
}
