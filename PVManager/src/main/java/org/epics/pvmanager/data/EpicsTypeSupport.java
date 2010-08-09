/*
 * Copyright 2010 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */

package org.epics.pvmanager.data;

import org.epics.pvmanager.NullUtils;
import org.epics.pvmanager.TypeSupport;

/**
 * Adds support for EPICS standard types.
 *
 * @author carcassi
 */
class EpicsTypeSupport {

    private static boolean installed = false;

    static void install() {
        // Install only once
        if (installed)
            return;

        addVDouble();

        installed = true;
    }

    private static void addVDouble() {
        // Add support for all scalars: simply return the new value
        TypeSupport.addTypeSupport(VDouble.class, new TypeSupport<VDouble>() {
            @Override
            public Notification<VDouble> prepareNotification(VDouble oldValue, VDouble newValue) {
                if (NullUtils.equalsOrBothNull(oldValue, newValue))
                    return new Notification<VDouble>(false, null);
                return new Notification<VDouble>(true, newValue);
            }
        });
    }
    

}
