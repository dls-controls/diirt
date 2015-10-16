/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.diirt.support.ca;

import gov.aps.jca.dbr.DBR;
import gov.aps.jca.dbr.DBRType;
import gov.aps.jca.dbr.DBR_CTRL_Double;
import gov.aps.jca.dbr.DBR_TIME_Double;
import org.diirt.vtype.VDouble;
import org.diirt.vtype.VTypeToString;

/**
 *
 * @author carcassi
 */
class VDoubleFromDbr extends VNumberMetadata<DBR_TIME_Double, DBR_CTRL_Double> implements VDouble {

    public VDoubleFromDbr(DBR_TIME_Double dbrValue, DBR_CTRL_Double metadata, JCAConnectionPayload connPayload) {
        super(dbrValue, metadata, connPayload);
    }

    private static DBR_TIME_Double convert(DBR dbrValue) {
        try {
            return (DBR_TIME_Double) dbrValue.convert(DBRType.TIME_DOUBLE);
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't convert " + dbrValue.getType() + " to DBR_TIME_DOUBLE", ex);
        }
    }

    public VDoubleFromDbr(DBR dbrValue, DBR_CTRL_Double metadata, JCAConnectionPayload connPayload) {
        this(convert(dbrValue), metadata, connPayload);
    }

    @Override
    public Double getValue() {
        return dbrValue.getDoubleValue()[0];
    }

    @Override
    public String toString() {
        return VTypeToString.toString(this);
    }

}
