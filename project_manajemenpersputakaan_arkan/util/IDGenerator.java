package util;

public class IDGenerator {
    public static String generate(String prefix) {
        return prefix + System.currentTimeMillis();
    }
}
