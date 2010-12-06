/*
 * Copyright 2010 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */

package org.epics.pvmanager.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * NumberFormat factory.
 *
 * @author carcassi
 */
public final class NumberFormats {

    private static final Map<Integer, DecimalFormat> precisionFormat =
            new HashMap<Integer, DecimalFormat>();
    private static Locale currentLocale = Locale.US;

    /**
     * Returns a number format that formats a number with the given
     * number of precision digits.
     *
     * @param precision number of digits past the decimal point
     * @return a number format
     */
    public static NumberFormat format(int precision) {
        NumberFormat format = precisionFormat.get(precision);
        if (format == null) {
            precisionFormat.put(precision, createFormatter(precision));
        }
        return precisionFormat.get(precision);
    }

    /**
     * Creates a new number format that formats a number with the given
     * number of precision digits.
     *
     * @param precision number of digits past the decimal point
     * @return a number format
     */
    private static DecimalFormat createFormatter(int precision) {
        if (precision < 0)
            throw new IllegalArgumentException("Precision must be non-negative");

        if (precision == 0)
            return new DecimalFormat("0");

        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < precision; i++) {
            sb.append("0");
        }
        return new DecimalFormat(sb.toString());
    }

}
