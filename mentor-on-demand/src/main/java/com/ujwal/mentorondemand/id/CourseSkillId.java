package com.ujwal.mentorondemand.id;

import java.io.Serializable;

public class CourseSkillId implements Serializable{

	private static final long serialVersionUID = 888710006182205725L;
	
	private long courseId;
	private long skillId;
	
	public CourseSkillId() {
		// default constructor
	}
	
	public CourseSkillId(long courseId, long skillId) {
		super();
		this.courseId = courseId;
		this.skillId = skillId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (courseId ^ (courseId >>> 32));
		result = prime * result + (int) (skillId ^ (skillId >>> 32));
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
		CourseSkillId other = (CourseSkillId) obj;
		if (courseId != other.courseId)
			return false;
		if (skillId != other.skillId)
			return false;
		return true;
	}
	
}
