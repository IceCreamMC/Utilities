package org.sharkurmc.utilities.minecraft.utils;

public class TeamLimits {
    private static int lengthLimit = 0;

    /**
     * Get prefix and suffix length limit
     * @return int
     */
    public static int getLengthLimit() {
        if (lengthLimit != 0) return lengthLimit;

        int minor = Version.minor();

        if (minor >= 13) lengthLimit = 64;
        else lengthLimit = 16;

        return lengthLimit;
    }
}
