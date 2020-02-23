package org.nargila.robostroke.android.common;

import android.content.Context;
import android.os.Environment;

import org.apache.log4j.Level;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Call {@link #configure()}} from your application's activity.
 */
public class ConfigureLog4J {

    private static File logFilePath;

    public static void configure(Context context, String name) {
        configure(context, Level.WARN, name);
    }

    public static void configure(Context context, Level rootLevel, String name) {

        final LogConfigurator logConfigurator = new LogConfigurator();

        if (name != null && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            logFilePath = new File(context.getExternalFilesDir(null) + File.separator + name + ".log");
            logFilePath.delete();
            logConfigurator.setFileName(logFilePath.getAbsolutePath());
            logConfigurator.setUseFileAppender(true);
        } else {
            logConfigurator.setUseFileAppender(false);
        }

        logConfigurator.setRootLevel(rootLevel);
        logConfigurator.configure();
    }

    public static File getLogFilePath() {
        return logFilePath;
    }
}