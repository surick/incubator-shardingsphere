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

package org.apache.shardingsphere.transaction.xa.jta.datasource.properties.dialect;

import com.google.common.base.Optional;
import org.apache.shardingsphere.core.config.DatabaseAccessConfiguration;
import org.apache.shardingsphere.core.metadata.datasource.dialect.SQLServerDataSourceMetaData;
import org.apache.shardingsphere.spi.database.DatabaseType;
import org.apache.shardingsphere.spi.database.DatabaseTypes;
import org.apache.shardingsphere.transaction.xa.jta.datasource.properties.XADataSourceDefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * XA data source definition for SQLServer.
 *
 * @author zhaojun
 */
public final class SQLServerXADataSourceDefinition implements XADataSourceDefinition {
    
    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseTypes.getActualDatabaseType("SQLServer");
    }
    
    @Override
    public Collection<String> getXADriverClassName() {
        return Collections.singletonList("com.microsoft.sqlserver.jdbc.SQLServerXADataSource");
    }
    
    @Override
    public Properties getXAProperties(final DatabaseAccessConfiguration databaseAccessConfiguration) {
        Properties result = new Properties();
        SQLServerDataSourceMetaData dataSourceMetaData = new SQLServerDataSourceMetaData(databaseAccessConfiguration.getUrl());
        result.setProperty("user", databaseAccessConfiguration.getUsername());
        result.setProperty("password", Optional.fromNullable(databaseAccessConfiguration.getPassword()).or(""));
        result.setProperty("serverName", dataSourceMetaData.getHostName());
        result.setProperty("portNumber", String.valueOf(dataSourceMetaData.getPort()));
        result.setProperty("databaseName", dataSourceMetaData.getSchemaName());
        return result;
    }
}
