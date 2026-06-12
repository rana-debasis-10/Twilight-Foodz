package com.twilight.annotations;

import com.twilight.validators.MobileNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jdk.jfr.Description;
import jdk.jfr.MetadataDefinition;
import jdk.jfr.Name;
import java.lang.annotation.*;

@MetadataDefinition
@Name("Mobile number validity")
@Description("Mobile Number Must be exactly of 10 digit")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = MobileNumberValidator.class)
@Documented
public @interface MobileNumber {
    String message() default "Invalid Mobile Number or Empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
