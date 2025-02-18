/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.transaction.xa.jta.datasource.properties;

import org.apache.shardingsphere.core.config.DatabaseAccessConfiguration;
import org.apache.shardingsphere.spi.database.DatabaseType;

import java.util.Collection;
import java.util.Properties;

/**
 * XA data source definition.
 *
 * @author zhangliang
 */
public interface XADataSourceDefinition {
    
    /**
     * Get database type.
     * 
     * @return database type
     */
    DatabaseType getDatabaseType();
    
    /**
     * Get XA driver class names.
     * 
     * @return XA driver class names
     */
    Collection<String> getXADriverClassName();
    
    /**
     * Get XA properties.
     *
     * @param databaseAccessConfiguration database access configuration
     * @return properties for XA
     */
    Properties getXAProperties(DatabaseAccessConfiguration databaseAccessConfiguration);
}
