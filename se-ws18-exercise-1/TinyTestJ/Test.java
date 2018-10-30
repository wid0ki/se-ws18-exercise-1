// minimal test suite example, based on http://docs.oracle.com/javase/1.5.0/docs/guide/language/annotations.html

package TinyTestJ;

import java.lang.annotation.*;

/**
 * Indicates that the annotated method is a test method.
 * This annotation should be used only on parameterless static methods.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test { }
