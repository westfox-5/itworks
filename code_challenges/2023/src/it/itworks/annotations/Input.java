package it.itworks.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Input {
   int position() default -1;

   int max() default -1;
   int min() default -1;
}
