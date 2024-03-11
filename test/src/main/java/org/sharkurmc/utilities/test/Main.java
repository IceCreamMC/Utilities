package org.icecreammc.utilities.test;

import org.icecreammc.utilities.java.updater.JavaUpdater;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static AtomicBoolean   processed = new AtomicBoolean(true) ;
    public static void main(String[] args) throws InterruptedException {
        synchronized (processed) {
            JavaUpdater.start("https://jenkins.cezarsalat.tk/job/Sharkur/job/ver%2F1.19/lastSuccessfulBuild/artifact/build/libs/sharkur-paperclip-1.19-R0.1-SNAPSHOT-reobf.jar");
        }

        JavaUpdater.restart(0);
    }
}
