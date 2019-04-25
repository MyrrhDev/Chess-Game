package Domini;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class executorJUnitTests {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TableroTest.class);
        System.out.println("Numero de fallos encontrados: " + result.getFailureCount());
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
