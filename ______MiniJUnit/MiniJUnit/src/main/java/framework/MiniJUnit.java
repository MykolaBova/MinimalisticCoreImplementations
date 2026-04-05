package framework;

import annotations.*;

import java.lang.reflect.Method;

public class MiniJUnit {

    public static void runTests(Class<?> testClass) throws Exception {
        Method[] methods = testClass.getDeclaredMethods();
        Object testInstance = testClass.getDeclaredConstructor().newInstance();

        Method beforeMethod = null;
        Method afterMethod = null;
        Method beforeClassMethod = null;
        Method afterClassMethod = null;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) beforeMethod = method;
            if (method.isAnnotationPresent(After.class)) afterMethod = method;
            if (method.isAnnotationPresent(BeforeClass.class)) beforeClassMethod = method;
            if (method.isAnnotationPresent(AfterClass.class)) afterClassMethod = method;
        }

        int tests = 0;
        int passed = 0;
        int ignored = 0;

        if (beforeClassMethod != null) {
            beforeClassMethod.invoke(testInstance);
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                if (method.isAnnotationPresent(Ignore.class)) {
                    Ignore ignore = method.getAnnotation(Ignore.class);
                    System.out.printf("⚠️  %s - IGNORED (%s)%n", method.getName(), ignore.reason());
                    ignored++;
                    continue;
                }

                tests++;
                long startTime = System.currentTimeMillis();
                try {
                    if (beforeMethod != null) beforeMethod.invoke(testInstance);
                    method.invoke(testInstance);
                    if (afterMethod != null) afterMethod.invoke(testInstance);
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.printf("✅ %s - PASSED (%d ms)%n", method.getName(), duration);
                    passed++;
                } catch (Throwable ex) {
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    System.out.printf("❌ %s - FAILED (%d ms) (%s)%n", method.getName(), duration, ex.getCause());
                    ex.getCause().printStackTrace(System.err);
                }
            }
        }

        if (afterClassMethod != null) {
            afterClassMethod.invoke(testInstance);
        }

        System.out.printf(
                "Всего тестов: %d, Пройдено: %d, Ошибок: %d, Пропущено: %d%n",
                tests + ignored, passed, tests - passed, ignored
        );
    }
}


