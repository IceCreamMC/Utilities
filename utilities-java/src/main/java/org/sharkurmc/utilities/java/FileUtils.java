package org.sharkurmc.utilities.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class FileUtils {
    /**
     * Extract fileName from filepath
     * @param filePath
     * @return
     */
    public static String extractFileName(String filePath){
        try {
            Pattern regex = Pattern.compile("([^\\\\/:*?\"<>|\r\n]+$)");
            Matcher regexMatcher = regex.matcher(filePath);
            if (regexMatcher.find()) return regexMatcher.group(1);
        } catch (PatternSyntaxException ex) {
            return null;
        }

        return filePath;
    }
}
