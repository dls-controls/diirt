/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.epics.pvmanager.pva;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.epics.pvdata.factory.FieldFactory;
import org.epics.pvdata.pv.Field;
import org.epics.pvdata.pv.PVStructure;
import org.epics.pvdata.pv.ScalarType;
import org.epics.vtype.VByteArray;
import org.epics.vtype.VDouble;
import org.epics.vtype.VDoubleArray;
import org.epics.vtype.VEnum;
import org.epics.vtype.VFloatArray;
import org.epics.vtype.VInt;
import org.epics.vtype.VIntArray;
import org.epics.vtype.VShortArray;
import org.epics.vtype.VString;
import org.epics.vtype.VStringArray;
import org.epics.pvmanager.pva.adapters.PVFieldToVByteArray;
import org.epics.pvmanager.pva.adapters.PVFieldToVDouble;
import org.epics.pvmanager.pva.adapters.PVFieldToVDoubleArray;
import org.epics.pvmanager.pva.adapters.PVFieldToVEnum;
import org.epics.pvmanager.pva.adapters.PVFieldToVFloatArray;
import org.epics.pvmanager.pva.adapters.PVFieldToVInt;
import org.epics.pvmanager.pva.adapters.PVFieldToVIntArray;
import org.epics.pvmanager.pva.adapters.PVFieldToVShortArray;
import org.epics.pvmanager.pva.adapters.PVFieldToVString;
import org.epics.pvmanager.pva.adapters.PVFieldToVStringArray;

/**
 *
 * @author carcassi
 */
public class PVAVTypeAdapterSet implements PVATypeAdapterSet {
    
    @Override
    public Set<PVATypeAdapter> getAdapters() {
        return converters;
    }
    
    //  -> VDouble
    final static PVATypeAdapter ToVDouble = new PVATypeAdapter(VDouble.class, new String[] { "NTScalar", "scalar_t", "structure" }, null) {

            @Override
            public VDouble createValue(PVStructure message, Field valueType, boolean disconnected) {
                return new PVFieldToVDouble(message, disconnected);
            }
        };

    //  -> VInt
    final static PVATypeAdapter ToVInt = new PVATypeAdapter(VInt.class, new String[] { "NTScalar", "scalar_t", "structure" }, null) {

            @Override
            public VInt createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVInt(message, disconnected);
            }
        };

    //  -> VString
    final static PVATypeAdapter ToVString = new PVATypeAdapter(VString.class, new String[] { "NTScalar", "scalar_t", "structure" }, null) {

            @Override
            public VString createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVString(message, disconnected);
            }
        };
            
    //  -> VEnum
    final static PVATypeAdapter ToVEnum = new PVATypeAdapter(VEnum.class, new String[] { "NTScalar", "scalar_t", "structure" }, null) {

            @Override
            public VEnum createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVEnum(message, disconnected);
            }
        };

    //  -> VArrayDouble
    final static PVATypeAdapter ToVArrayDouble = new PVATypeAdapter(VDoubleArray.class, new String[] { "NTArray", "scalarArray_t", "structure" }, FieldFactory.getFieldCreate().createScalarArray(ScalarType.pvDouble)) {

            @Override
            public VDoubleArray createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVDoubleArray(message, disconnected);
            }
        };

    //  -> VArrayFloat
    final static PVATypeAdapter ToVArrayFloat = new PVATypeAdapter(VFloatArray.class, new String[] { "NTArray", "scalarArray_t", "structure" }, FieldFactory.getFieldCreate().createScalarArray(ScalarType.pvFloat)) {

            @Override
            public VFloatArray createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVFloatArray(message, disconnected);
            }
        };
        
    //  -> VArrayInt
    final static PVATypeAdapter ToVArrayInt = new PVATypeAdapter(VIntArray.class, new String[] { "NTArray", "scalarArray_t", "structure" }, FieldFactory.getFieldCreate().createScalarArray(ScalarType.pvInt)) {

            @Override
            public VIntArray createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVIntArray(message, disconnected);
            }
        };

    //  -> VArrayShort
    final static PVATypeAdapter ToVArrayShort = new PVATypeAdapter(VShortArray.class, new String[] { "NTArray", "scalarArray_t", "structure" }, FieldFactory.getFieldCreate().createScalarArray(ScalarType.pvShort)) {

            @Override
            public VShortArray createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVShortArray(message, disconnected);
            }
        };
        
    //  -> VArrayByte
    final static PVATypeAdapter ToVArrayByte = new PVATypeAdapter(VByteArray.class, new String[] { "NTArray", "scalarArray_t", "structure" }, FieldFactory.getFieldCreate().createScalarArray(ScalarType.pvByte)) {

            @Override
            public VByteArray createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVByteArray(message, disconnected);
            }
        };
        
    //  -> VArrayString
    final static PVATypeAdapter ToVArrayString = new PVATypeAdapter(VStringArray.class, new String[] { "NTArray", "scalarArray_t", "structure" }, FieldFactory.getFieldCreate().createScalarArray(ScalarType.pvString)) {

            @Override
            public VStringArray createValue(final PVStructure message, Field valueType, boolean disconnected) {
            	return new PVFieldToVStringArray(message, disconnected);
            }
        };
        
    private static final Set<PVATypeAdapter> converters;
    
    static {
        Set<PVATypeAdapter> newFactories = new HashSet<PVATypeAdapter>();
        
        // Add all SCALARs
        newFactories.add(ToVDouble);
        newFactories.add(ToVInt);
        newFactories.add(ToVString);
        newFactories.add(ToVEnum);

        // Add all ARRAYs
        newFactories.add(ToVArrayDouble);
        newFactories.add(ToVArrayFloat);
        newFactories.add(ToVArrayByte);
        newFactories.add(ToVArrayInt);
        newFactories.add(ToVArrayShort);
        newFactories.add(ToVArrayString);
        //newFactories.add(ToVArrayEnum);
        
        converters = Collections.unmodifiableSet(newFactories);
    }
    
}
