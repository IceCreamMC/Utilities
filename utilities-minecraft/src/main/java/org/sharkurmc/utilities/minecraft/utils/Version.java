package org.sharkurmc.utilities.minecraft.utils;

import org.bukkit.Bukkit;

public class Version {
    private static int major = -1;
    private static int minor = -1;

    public static int major() {
        if (major != -1) {
            return major;
        }

        String pack = Bukkit.getServer().getClass().getPackage().getName();
        String[] version = pack.substring(pack.lastIndexOf('.') + 1).substring(1).split("_");
        major = Integer.parseInt(version[0]);
        return major;
    }

    public static int minor() {
        if (minor != -1) {
            return minor;
        }

        String pack = Bukkit.getServer().getClass().getPackage().getName();
        String[] version = pack.substring(pack.lastIndexOf('.') + 1).substring(1).split("_");
        minor = Integer.parseInt(version[1]);
        return minor;
    }
}
