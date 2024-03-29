package org.icecreammc.utilities.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRandomUtils {
    @Test
    public void testRandomString() {
        String randomString = RandomUtils.randomString(500);
        Assertions.assertFalse(randomString.isEmpty());
    }

    @Test
    public void testRandomSecureString() {
        String randomSecureString = RandomUtils.randomSecureString(500);
        Assertions.assertFalse(randomSecureString.isEmpty());
    }

    @Test
    public void testRandomInt() {
        Object i = RandomUtils.randomInt(1, 50);
        Assertions.assertTrue(i instanceof Integer);
    }

    @Test
    public void testRandomDouble() {
        Object i = RandomUtils.randomDouble(1, 50);
        Assertions.assertTrue(i instanceof Double);
    }
}
