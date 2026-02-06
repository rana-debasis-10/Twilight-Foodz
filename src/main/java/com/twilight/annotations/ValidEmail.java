package com.twilight.annotations;

import com.twilight.validators.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jdk.jfr.Description;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;

import java.lang.annotation.*;

@MetadataDefinition
@Name("Email validity")
@Description("Must follow email format")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "Invalid email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
