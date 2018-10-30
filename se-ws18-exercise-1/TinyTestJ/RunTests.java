// minimal test suite example, based on http://docs.oracle.com/javase/1.5.0/docs/guide/language/annotations.html

package TinyTestJ;

import java.lang.reflect.*;

// Runs all methods with annotation @Test from classname in first parameter
public class RunTests {
  public static void main(String[] args) throws Exception {
    int passed = 0, failed = 0;
    for (Method m: Class.forName(args[0]).getMethods()) {
      if (m.isAnnotationPresent(Test.class)) {
        try {
          m.invoke(null);
          passed++;
        } catch (Throwable ex) {
          System.out.printf("Test %s failed:\n  Message: %s\n  Location: %s\n", m, ex.getCause().getMessage(), ex.getCause().getStackTrace()[0]);
          failed++;
        }
      }
    }
    System.out.printf("Tests: passed: %d, failed %d.\n", passed, failed);
    System.exit(failed);
  }
}

