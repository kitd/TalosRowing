package org.nargila.robostroke.data.media;

import org.nargila.robostroke.common.ListenerList;
import org.nargila.robostroke.common.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;


public interface ExternalMedia {

    void addEventListener(EventListener listener);

    void removeEventListener(EventListener listener);

    long getDuration();

    long getTime();

    boolean setTime(long time);

    boolean isPlaying();

    void start();

    void play();

    void pause();

    void stop();

    boolean step();

    boolean setRate(double rate);

    enum MediaFramework {
        VLC,
        GST
    }

    enum EventType {
        PLAY,
        PAUSE,
        STOP,
        DURATION,
        TIME
    }

    enum VideoEffect {
        NONE("none"),
        ROTATE90("clockwise"),
        ROTATE180("rotate-180"),
        ROTATE270("counterclockwise");


        final String method;

        VideoEffect(String method) {
            this.method = method;
        }

        @Override
        public String toString() {
            return method;
        }
    }

    interface EventListener {
        void onEvent(EventType event);
    }

    abstract class TimeNotifiyer {

        private static final Logger logger = LoggerFactory.getLogger(TimeNotifiyer.class);

        private final AtomicLong lastTime = new AtomicLong();

        private final ScheduledExecutorService timeExecutor = Executors.newSingleThreadScheduledExecutor();

        private final AtomicBoolean stopped = new AtomicBoolean();
        private final Listeners listeners;
        private final Runnable timeNotifyRunnable = new Runnable() {
            @Override
            public void run() {
                synchronized (stopped) {
                    if (!stopped.get()) {
                        long time = getTime();
                        lastTime.set(time);
                        listeners.dispatch(EventType.TIME, time);
                    }
                }
            }
        };
        private ScheduledFuture<?> timeNotifyJob;

        TimeNotifiyer(Listeners listeners) {
            this.listeners = listeners;
        }

        public synchronized void start() {

            logger.info("starting time notifyer");

            if (timeNotifyJob != null) {
                throw new IllegalStateException("already started");
            }

            timeNotifyJob = timeExecutor.scheduleWithFixedDelay(timeNotifyRunnable, 0, 50, TimeUnit.MILLISECONDS);
        }

        public synchronized void stop() {

            logger.info("stopping time notifyer");

            synchronized (stopped) {
                stopped.set(true);
                if (timeNotifyJob != null) timeNotifyJob.cancel(true);
                timeExecutor.shutdownNow();
            }
        }

        public long getLastTime() {
            return lastTime.get();
        }

        protected abstract long getTime();
    }

    class Listeners extends ListenerList<EventListener, Pair<EventType, Object>> {
        @Override
        protected void dispatch(EventListener listener, Pair<EventType, Object> eventObject) {
            listener.onEvent(eventObject.first);
        }

        void dispatch(EventType event, Object data) {
            dispatch(Pair.create(event, data));
        }
    }

}
