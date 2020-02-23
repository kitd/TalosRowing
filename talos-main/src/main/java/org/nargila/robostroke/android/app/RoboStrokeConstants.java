/*
 * Copyright (c) 2011 Tal Shalif
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
package org.nargila.robostroke.android.app;

interface RoboStrokeConstants {
    int ROBOSTROKE_ERROR = 0;
    int HEART_MONITOR_SERVICE_ERROR = 1;
    String BLE_SAMPLE_HRV_DATA_SERVICE = "com.sample.hrv.ACTION_DATA_AVAILABLE";
    String BLE_SAMPLE_HRV_DATA_SERVICE_EXTRA_CHARACTERISTIC_UUI = "com.sample.hrv.EXTRA_CHARACTERISTIC_UUI";
    String BLE_SAMPLE_HRV_DATA_SERVICE_EXTRA_TEXT = "com.sample.hrv.EXTRA_TEXT";
    String BLE_SAMPLE_HRV_HEART_RATE_DATA_UUID = "00002a37-0000-1000-8000-00805f9b34fb";

    String[] HRM_SERVICE_ACTIONS = {BLE_SAMPLE_HRV_DATA_SERVICE};
    String TAG = "RoboStroke";
    boolean D = true;
    int TILT_FREEZE_CALIBRATION_TIME = 15;
}
