package org.nargila.robostroke.data.remote;


public interface DataReceiver extends DataRemote {

    /**
     * set listener to receive data asynchronously
     */
    void setListener(Listener listener);

    interface Listener {
        void onDataReceived(String s);
    }
}
