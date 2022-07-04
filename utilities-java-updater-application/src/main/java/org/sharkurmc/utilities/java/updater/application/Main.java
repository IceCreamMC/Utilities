package org.sharkurmc.utilities.java.updater.application;

import picocli.CommandLine;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;

public class Main implements Callable<String> {
    @CommandLine.Option(names = "-downloadUrl", description = "Download url")
    private String downloadUrl;

    @CommandLine.Option(names = "-fileName", description = "Filename for downloaded jar")
    private String fileName;

    @CommandLine.Option(names = "-restart", description = "Restart jar", defaultValue = "false")
    private String restart;

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    public String call() throws Exception {
        if (restart != null && restart.equals("true")) {
            Files.move(Paths.get("./cache/"+fileName), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            return "success";
        }

        File file = new File("cache", fileName);
        file.getParentFile().mkdirs();

        java.nio.file.Files.copy(new java.net.URL(downloadUrl).openStream(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        return "success";
    }
}
