/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager.sim;

import java.net.URI;
import org.epics.vtype.AlarmSeverity;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.epics.util.time.TimeDuration.*;
import org.epics.util.time.Timestamp;

/**
 *
 * @author carcassi
 */
public class ReplayParserTest {

    public ReplayParserTest() {
    }

    @Test
    public void unmarshalParse1() throws Exception {
        // Unmarshal XML file
        XmlValues values = ReplayParser.parse(new URI("./src/test/resources/org/epics/pvmanager/replay/parse1.xml"));
        assertThat(values.getValues().size(), equalTo(4));
        assertThat(values.getValues().get(0), instanceOf(XmlVDouble.class));

        // Check first value
        XmlVDouble value = (XmlVDouble) values.getValues().get(0);
        assertThat(value.getValue(), equalTo(0.0));
        assertThat(value.getTimestamp(), equalTo(Timestamp.of(0, 0)));
        assertThat(value.getAlarmSeverity(), equalTo(AlarmSeverity.NONE));
        assertThat(value.getAlarmName(), equalTo("NONE"));
        assertThat(value.getTimeUserTag(), equalTo(0));
        assertThat(value.getLowerCtrlLimit(), equalTo(-10.0));
        assertThat(value.getLowerDisplayLimit(), equalTo(-10.0));
        assertThat(value.getLowerAlarmLimit(), equalTo(-9.0));
        assertThat(value.getLowerWarningLimit(), equalTo(-8.0));
        assertThat(value.getUpperWarningLimit(), equalTo(8.0));
        assertThat(value.getUpperAlarmLimit(), equalTo(9.0));
        assertThat(value.getUpperCtrlLimit(), equalTo(10.0));
        assertThat(value.getUpperDisplayLimit(), equalTo(10.0));

        // Check second value
        value = (XmlVDouble) values.getValues().get(1);
        assertThat(value.getValue(), equalTo(1.0));
        assertThat(value.getTimestamp(), equalTo(Timestamp.of(0, 0).plus(ofMillis(100))));
        assertThat(value.getAlarmSeverity(), equalTo(AlarmSeverity.INVALID));
        assertThat(value.getAlarmName(), equalTo("RECORD"));
        assertThat(value.getTimeUserTag(), equalTo(0));

        // Check third value
        value = (XmlVDouble) values.getValues().get(2);
        assertThat(value.getValue(), equalTo(2.0));
        assertThat(value.getTimestamp(), equalTo(Timestamp.of(0, 0).plus(ofMillis(200))));
        assertThat(value.getAlarmSeverity(), equalTo(AlarmSeverity.NONE));
        assertThat(value.getAlarmName(), equalTo("NONE"));
        assertThat(value.getTimeUserTag(), equalTo(0));

        // Check fourth value
        value = (XmlVDouble) values.getValues().get(3);
        assertThat(value.getValue(), equalTo(3.0));
        assertThat(value.getTimestamp(), equalTo(Timestamp.of(0, 0).plus(ofMillis(500))));
        assertThat(value.getAlarmSeverity(), equalTo(AlarmSeverity.NONE));
        assertThat(value.getAlarmName(), equalTo("NONE"));
        assertThat(value.getTimeUserTag(), equalTo(0));
    }

}