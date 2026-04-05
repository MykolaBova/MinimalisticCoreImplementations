package framework;

public class Assert {
    public static void assertEquals(Object expected, Object actual) {
        if (expected == null && actual == null) return;
        if (expected != null && expected.equals(actual)) return;
        throw new AssertionError("Ожидалось: " + expected + ", но получено: " + actual);
    }
}

