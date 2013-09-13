/**
 * Copyright (C) 2010-12 Brookhaven National Laboratory
 * All rights reserved. Use is subject to license terms.
 */
package org.epics.pvmanager.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.sql.DataSource;
import org.epics.pvmanager.service.ServiceDescription;

/**
 * The description on how to construct an exec service.
 * <p>
 * This class encapsulate the description of a service, including:
 * <ul>
 *   <li>A number of commands</li>
 *   <li>The arguments for each command and how should they be mapped</li>
 *   <li>The results of each command</li>
 * </ul>
 *
 * @author carcassi
 */
public class ExecServiceDescription {
    
    final ServiceDescription serviceDescription;
    DataSource dataSource;
    ExecutorService executorService;
    private List<ExecServiceMethodDescription> jdbcServiceMethodDescriptions = new ArrayList<>();
    
    /**
     * A new service description with the given service name and description.
     * 
     * @param name the name of the service
     * @param description a brief description
     */
    public ExecServiceDescription(String name, String description) {
        serviceDescription = new ServiceDescription(name, description);
    }

    /**
     * Adds a service method (i.e. a query) to the service.
     * 
     * @param jdbcServiceMethodDescription a method description
     * @return this
     */
    public ExecServiceDescription addServiceMethod(ExecServiceMethodDescription jdbcServiceMethodDescription) {
        jdbcServiceMethodDescriptions.add(jdbcServiceMethodDescription);
        return this;
    }
    
    /**
     * The ExecutorService on which to execute the query.
     * 
     * @param executorService an executor service
     * @return this
     */
    public ExecServiceDescription executorService(ExecutorService executorService) {
        if (this.executorService != null) {
            throw new IllegalArgumentException("ExecutorService was already set");
        }
        this.executorService = executorService;
        return this;
    }
    
    ServiceDescription createService() {
        for (ExecServiceMethodDescription jdbcServiceMethodDescription : jdbcServiceMethodDescriptions) {
            jdbcServiceMethodDescription.dataSource(dataSource);
            jdbcServiceMethodDescription.executorService(executorService);
            serviceDescription.addServiceMethod(new ExecServiceMethod(jdbcServiceMethodDescription));
        }
        return serviceDescription;
    }
}
