package p08CardGame.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumAnnotation {
    String type() default "Enumeration";
    String description() default "Provides suit constants for a Card class.";
    String category();
}
