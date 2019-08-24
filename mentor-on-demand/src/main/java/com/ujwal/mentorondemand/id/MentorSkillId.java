package com.ujwal.mentorondemand.id;

import java.io.Serializable;

public class MentorSkillId implements Serializable{

	private static final long serialVersionUID = -7349423728544788117L;

	private long mentorId;
	private long skillId;
	
	public MentorSkillId() {
		// default constructor
	}
	
	public MentorSkillId(long mentorId, long skillId) {
		super();
		this.mentorId = mentorId;
		this.skillId = skillId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (mentorId ^ (mentorId >>> 32));
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
		MentorSkillId other = (MentorSkillId) obj;
		if (mentorId != other.mentorId)
			return false;
		if (skillId != other.skillId)
			return false;
		return true;
	}
	
}
