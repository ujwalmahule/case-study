package com.ujwal.mentorondemand.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ujwal.mentorondemand.constants.USER_ROLE;
import com.ujwal.mentorondemand.model.Mentor;
import com.ujwal.mentorondemand.model.User;
import com.ujwal.mentorondemand.model.UserRole;

public class MentorValidator implements ConstraintValidator<ValidateMentor, Object>{

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if(object instanceof Mentor) {
			Mentor mentor = (Mentor) object;
			return validate(mentor.getUserProfile().getUserRole(), mentor);
		} else if(object instanceof User) {
			User user = (User) object;
			return validate(user.getUserRole(), user.getMentor());
		}
		
		return true;
	}

	private boolean validate(UserRole userRole, Mentor mentor) {
		if(mentor!=null && !USER_ROLE.MENTOR.equals(userRole.getName())) {
			return false;
		}
		
		return true;
	}

}
