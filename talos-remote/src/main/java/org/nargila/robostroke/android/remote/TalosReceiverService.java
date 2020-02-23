/*
 * Copyright (c) 2013 Tal Shalif
 *
 * This file is part of Talos-Rowing.
 *
 * Talos-Rowing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Talos-Rowing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Talos-Rowing.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.nargila.robostroke.android.remote;

import android.content.Intent;

import org.nargila.robostroke.data.remote.DataReceiver;
import org.nargila.robostroke.data.remote.DataRemote;
import org.nargila.robostroke.data.remote.DataRemote.DataRemoteError;
import org.nargila.robostroke.data.remote.DatagramDataReceiver;

public class TalosReceiverService extends TalosService {

    private final static String BROADCAST_ID = TalosReceiverService.class.getName();


    public TalosReceiverService() {
    }


    @Override
    protected DataRemote makeImpl(String host, int port) throws DataRemoteError {
        DataReceiver impl = new DatagramDataReceiver(host, port, s -> {

            Intent intent = new Intent(BROADCAST_ID);
            intent.putExtra("data", s);
            sendBroadcast(intent);
        });

        return impl;
    }


    @Override
    protected void afterStart() {
    }


    @Override
    protected void beforeStop() {
    }
}