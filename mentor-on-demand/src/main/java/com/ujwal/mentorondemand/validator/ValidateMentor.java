package com.ujwal.mentorondemand.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MentorValidator.class)
@Documented
public @interface ValidateMentor {
	String message() default "{USER.INVALID_TYPE}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
