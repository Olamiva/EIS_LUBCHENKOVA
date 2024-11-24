package Lab5_Annotation_Validation.Annotation;

import Lab5_Annotation_Validation.Validator.ConnectedRailwayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConnectedRailwayValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRailway {
    String message() default "Нет связи между участками";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}