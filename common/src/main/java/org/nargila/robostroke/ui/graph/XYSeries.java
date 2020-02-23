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
package org.nargila.robostroke.ui.graph;

import org.nargila.robostroke.ui.PaintStyle;
import org.nargila.robostroke.ui.RSPaint;

public interface XYSeries {

    /**
     * Adds a new value to the series.
     *
     * @param x the value for the X axis
     * @param y the value for the Y axis
     */
    void add(double x, double y);

    Renderer getRenderer();

    void setRenderer(Renderer renderer);

    /**
     * Removes an existing value from the series.
     *
     * @param index the index in the series of the value to remove
     */
    void remove(int index);

    /**
     * Removes all the existing values from the series.
     */
    void clear();

    /**
     * Returns the X axis value at the specified index.
     *
     * @param index the index
     * @return the X value
     */
    double getX(int index);

    /**
     * Returns the Y axis value at the specified index.
     *
     * @param index the index
     * @return the Y value
     */
    double getY(int index);

    /**
     * Returns the series item count.
     *
     * @return the series item count
     */
    int getItemCount();

    /**
     * Returns the minimum value on the X axis.
     *
     * @return the X axis minimum value
     */
    double getMinX();

    /**
     * Returns the minimum value on the Y axis.
     *
     * @return the Y axis minimum value
     */
    double getMinY();

    /**
     * Returns the maximum value on the X axis.
     *
     * @return the X axis maximum value
     */
    double getMaxX();

    /**
     * Returns the maximum value on the Y axis.
     *
     * @return the Y axis maximum value
     */
    double getMaxY();

    double getxRange();

    void setxRange(double xRange);

    XMode getXMode();

    void setXMode(XMode mode);

    boolean isIndependantYAxis();

    void setIndependantYAxis(boolean independantYAxis);

    double getyAxisSize();

    void setyAxisSize(double yAxisSize);

    enum XMode {
        FIXED,
        GROWING,
        ROLLING
    }

    class Renderer {
        public final RSPaint strokePaint;
        final RSPaint fillPaint;

        public Renderer(RSPaint strokePaintIns) {
            this(initPaint(strokePaintIns), null);
        }

        public Renderer(RSPaint strokePaint, RSPaint fillPaint) {
            this.strokePaint = strokePaint;
            this.fillPaint = fillPaint;
        }

        private static RSPaint initPaint(RSPaint strokePaintInst) {
            strokePaintInst.setARGB(0xff, 0xff, 0xff, 0xff);
            strokePaintInst.setStyle(PaintStyle.STROKE);
            strokePaintInst.setAntiAlias(false);
            strokePaintInst.setStrokeWidth(2f);

            return strokePaintInst;

        }
    }

}