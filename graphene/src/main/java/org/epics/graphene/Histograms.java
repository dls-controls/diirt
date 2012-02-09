/*
 * Copyright 2011 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.graphene;

/**
 *
 * @author carcassi
 */
public class Histograms {
    public static Histogram1D createHistogram(Dataset1D dataset) {
        Histogram1DFromDataset1D histogram = new Histogram1DFromDataset1D();
        histogram.setDataset(dataset);
        return histogram;
    }
}
