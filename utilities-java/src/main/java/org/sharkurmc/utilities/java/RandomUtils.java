package org.sharkurmc.utilities.java;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    /**
     * Generate random int
     * @param min
     * @param max
     * @return int
     */
    public static int randomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Generate random double
     * @param min
     * @param max
     * @return double
     */
    public static double randomDouble(double min, double max) {
        return ((Math.random() * (max - min)) + min);
    }

    /**
     * Generate random string
     * @param length
     * @return string
     */
    public static String randomString(int length) {
        byte[] array = new byte[length];
        ThreadLocalRandom.current().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
