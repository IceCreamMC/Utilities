package org.icecreammc.utilities.java.updater;

import org.icecreammc.utilities.java.FileUtils;

import java.io.File;
import java.util.logging.Logger;

public class JavaUpdater {
    private static Logger LOGGER = Logger.getLogger("Java Updater");
    public static void start(String downloadUrl) {
        String path = JavaUpdater.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fileName = FileUtils.extractFileName(path);

        start(fileName, downloadUrl);
    }

    public static void start(String fileName, String downloadUrl) {
        File file = new File("cache", "updater.jar");
        file.getParentFile().mkdirs();

        try {
            java.nio.file.Files.copy(new java.net.URL("https://jenkins.cezarsalat.tk/job/utilities/job/main/lastSuccessfulBuild/artifact/utilities-java-updater-application/target/utilities-java-updater-application-1.1-SNAPSHOT-jar-with-dependencies.jar").openStream(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            Logger.getLogger("JavaUpdater").warning("Failed to download updater.");
        }

        download(fileName, downloadUrl, file.getPath());
    }

    public static void restart(long pid) {
        String path = JavaUpdater.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fileName = FileUtils.extractFileName(path);

        restart(fileName, pid);
    }

    public static void restart(String fileName, long pid) {
        File file = new File("cache", "updater.jar");
        file.getParentFile().mkdirs();

        try {
            Runtime.getRuntime().exec("java -jar "+file.getPath()+" -restart=true"+" -fileName="+fileName+" -pid="+pid);
        } catch (Exception e) {
            System.out.printf("Can't restart jar.");
        }
    }

    private static void download(String fileName, String downloadUrl, String updaterPath) {
        try {
            Process p = Runtime.getRuntime().exec("java -jar "+updaterPath+" -downloadUrl="+downloadUrl+" -fileName="+fileName +" -restart=false");

            LOGGER.info("A new version of the program is currently being downloaded.");
            LOGGER.warning("Don't worry, the program isn't stuck, it just doesn't have a progress bar.");

            p.waitFor();

            LOGGER.info("New version of the program has been downloaded.");
        } catch (Exception e) {
            Logger.getLogger("JavaUpdater").warning("Failed to run updater.");
        }
    }
}
