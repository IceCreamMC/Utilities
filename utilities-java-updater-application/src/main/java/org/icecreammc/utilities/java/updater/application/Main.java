package org.icecreammc.utilities.java.updater.application;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
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

    @CommandLine.Option(names = "-pid", description = "Kill PID")
    private String pid;

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    public String call() throws Exception {
        if (restart != null && restart.equals("true")) {
            String cmd;
            if (System.getProperty("os.name").contains("Windows")) cmd = "taskkill /F /PID " + pid;
            else cmd = "kill -9 " + pid;

            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
                return "success";
            }

            System.out.printf("Wait 5 seconds");
            Thread.sleep(5000);
            Files.move(Paths.get("./cache/"+fileName), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            return "success";
        }

        File file = new File("cache", fileName);
        file.getParentFile().mkdirs();

        java.nio.file.Files.copy(new java.net.URL(downloadUrl).openStream(), file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        return "success";
    }
}
