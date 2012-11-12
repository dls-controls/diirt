/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epics.pvmanager;

import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author carcassi
 */
public class NewCacheCollectorTest {
    
    public NewCacheCollectorTest() {
    }

    @Test
    public void inputOutput() throws InterruptedException {
        NewCacheCollector<Integer> collector = new NewCacheCollector<>(5);
        assertThat(collector.getValue().size(), equalTo(0));
        collector.setValue(0);
        assertThat(collector.getValue(), equalTo(Arrays.asList(0)));
        assertThat(collector.getValue(), equalTo(Arrays.asList(0)));
        collector.setValue(1);
        collector.setValue(2);
        collector.setValue(3);
        assertThat(collector.getValue(), equalTo(Arrays.asList(0,1,2,3)));
        assertThat(collector.getValue(), equalTo(Arrays.asList(0,1,2,3)));
        collector.setValue(4);
        collector.setValue(5);
        collector.setValue(6);
        assertThat(collector.getValue(), equalTo(Arrays.asList(2,3,4,5,6)));
        assertThat(collector.getValue(), equalTo(Arrays.asList(2,3,4,5,6)));
    }
}
