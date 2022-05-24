package com.javachallenge.fileapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Target(PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidator.class)
public @interface ValidateFile {

    String message() default "Invalid file!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
