package org.nargila.robostroke.data;

public interface ClockProvider {
    long getTime();

    void run();

    void stop();

    void reset(long initialTime);
}