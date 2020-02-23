package org.nargila.robostroke;

import org.nargila.robostroke.common.ClockTime;

import java.io.InputStream;
import java.util.Properties;

public class TestProperties {

    private final String testName;
    private final Properties testProperties = new Properties();
    TestProperties() throws Exception {
        this.testName = getClass().getSimpleName();
        String propFileName = testName + ".properties";
        InputStream resourceAsStream = getClass().getResourceAsStream(propFileName);

        if (resourceAsStream == null) {
            throw new Exception("test property file " + propFileName + " not found (package " + getClass().getPackage().getName() + ")");
        }

        testProperties.load(resourceAsStream);
    }

    public static String getCallingMethodName() {
        return getCallingMethodName(null);
    }

    private static String getCallingMethodName(String callee) {

        if (callee == null) {
            callee = "getCallingMethodName";
        }

        StackTraceElement[] e = Thread.currentThread().getStackTrace();
        boolean nextIsCaller = false;

        for (StackTraceElement s : e) {
            System.out.println(s.getMethodName());
            if (nextIsCaller) {
                return s.getMethodName();
            }

            nextIsCaller = s.getMethodName().equals(callee);
        }

        throw new AssertionError("HDIGH: getMethodName failed");
    }

    @SuppressWarnings("unchecked")
    <T> T v(String name, T defVal) {
        try {
            return (T) v(name, defVal.getClass());
        } catch (NoSuchProperty p) {
            return defVal;
        }
    }

    String v(String name) {
        String caller = getCallingMethodName("v"); // should return test method name from which v() was called
        return v(caller, name, String.class);
    }

    <T> T v(String name, Class<T> clazz) {
        String caller = getCallingMethodName("v"); // should return test method name from which v() was called
        return v(caller, name, clazz);
    }

    @SuppressWarnings("unchecked")
    private <T> T v(String caller, String name, Class<T> clazz) {

        String[] candidates = {
                caller + "." + name,
                name
        };

        String val = null;

        for (String varName : candidates) {

            val = testProperties.getProperty(varName);

            if (val != null) {
                break;
            }
        }

        if (val == null) {
            throw new NoSuchProperty("test property " + name + " was not found in " + testName + " for caller '" + caller + "'");
        }

        Object res;

        if (clazz == ClockTime.class) {
            res = ClockTime.fromString(val);
        } else if (clazz == Long.class) {
            res = new Long(val);
        } else if (clazz == Integer.class) {
            res = new Integer(val);
        } else if (clazz == String.class) {
            res = val;
        } else if (clazz == Double.class) {
            res = new Double(val);
        } else {
            throw new AssertionError("unhandled template class " + clazz.getName());
        }

        return (T) res;
    }

    @SuppressWarnings("serial")
    static class NoSuchProperty extends IllegalArgumentException {

        NoSuchProperty(String msg) {
            super(msg);
        }
    }
}
