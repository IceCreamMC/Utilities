package org.sharkurmc.utilities.java.updater.application;

import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class Main implements Callable<String> {
    private Logger LOGGER = Logger.getLogger("Java Updater");

    @CommandLine.Option(names = "-downloadUrl", description = "Download url", required = true)
    private String downloadUrl;

    @CommandLine.Option(names = "-fileName", description = "Filename for downloaded jar", required = true)
    private String fileName;

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    public String call() throws Exception {
        LOGGER.info("A new version of the program is currently being downloaded.");
        LOGGER.warning("Don't worry, the program isn't stuck, it just doesn't have a progress bar.");

        File file = new File("cache", fileName);
        file.getParentFile().mkdirs();

        java.nio.file.Files.copy(new java.net.URL(downloadUrl).openStream(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        LOGGER.info("New version of the program has been downloaded.");

        return "success";
    }
}
