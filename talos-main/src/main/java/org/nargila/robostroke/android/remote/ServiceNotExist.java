package org.nargila.robostroke.android.remote;

class ServiceNotExist extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceNotExist(String detailMessage) {
        super(detailMessage);
    }

}