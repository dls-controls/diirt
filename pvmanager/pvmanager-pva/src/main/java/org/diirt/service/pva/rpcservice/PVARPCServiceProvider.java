/**
 * Copyright (C) 2010-14 diirt developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */
package org.diirt.service.pva.rpcservice;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.diirt.service.AbstractFileServiceProvider;
import org.diirt.service.Service;
import org.diirt.service.ServiceProvider;

/**
 * A pva rpcservice factory that crawls a directory for xml files, and creates
 * a pvAccessRPC rpcs ervice from each of them.
 * 
 * @author dkumar
 */
public class PVARPCServiceProvider extends AbstractFileServiceProvider {

    /**
     * Creates a new factory that reads from the given directory.
     * <p>
     * If the directory does not exist, it simply returns an empty set.
     * 
     * @param directory a directory
     */
    public PVARPCServiceProvider(File directory) {
        super(directory);
    }

    /**
     * Creates a new provider that reads from the default configuration directory.
     */
    public PVARPCServiceProvider() {
    }

    @Override
    public String getName() {
        return "pvarpc";
    }

    @Override
    public Service createService(File file) throws Exception {
        if (file.getName().endsWith(".xml")) {
            return RPCServices.createFromXml(new FileInputStream(file));
        } else {
            return null;
        }
    }
    
    
}
