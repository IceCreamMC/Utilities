package org.sharkurmc.utilities.java.updater;

import org.sharkurmc.utilities.java.FileUtils;

import java.io.File;
import java.util.logging.Logger;

public class JavaUpdater {
    public static void start(String downloadUrl) {
        String path = JavaUpdater.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String fileName = FileUtils.extractFileName(path);

        start(fileName, downloadUrl);
    }

    public static void start(String fileName, String downloadUrl) {
        File file = new File("cache", "updater.jar");
        file.getParentFile().mkdirs();

        try {
            java.nio.file.Files.copy(new java.net.URL("https://jenkins.cezarsalat.tk/job/utilities/job/main/lastSuccessfulBuild/artifact/utilities-minecraft/target/utilities-java-updater-application-1.0-SNAPSHOT-jar-with-dependencies.jar").openStream(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            Logger.getLogger("JavaUpdater").warning("Failed to download updater.");
        }

        download(fileName, downloadUrl, file.getPath());
    }

    private static void download(String fileName, String downloadUrl, String updaterPath) {
        try {
            Process p = Runtime.getRuntime().exec("java -jar "+updaterPath+" -downloadUrl="+downloadUrl+" -fileName="+fileName);

            System.out.println("Waiting for downloader...");
            p.waitFor();
            System.out.println("Java file done.");
        } catch (Exception e) {
            Logger.getLogger("JavaUpdater").warning("Failed to run updater.");
        }
    }
}
