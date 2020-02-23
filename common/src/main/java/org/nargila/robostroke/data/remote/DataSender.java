package org.nargila.robostroke.data.remote;


public interface DataSender extends DataRemote {
    /**
     * Send a data item - non blocking
     *
     * @param data item to send
     */
    void write(String data);
}
