package org.sharkurmc.utilities.test;

import org.sharkurmc.utilities.java.updater.JavaUpdater;

public class Main {
    public static void main(String[] args) {
        JavaUpdater.start("test.jar", "https://jenkins.cezarsalat.tk/");
        //JavaUpdater.start("test.jar", "https://jenkins.cezarsalat.tk/job/Sharkur/job/ver%2F1.19/lastSuccessfulBuild/artifact/build/libs/sharkur-paperclip-1.19-R0.1-SNAPSHOT-reobf.jar");
    }
}
