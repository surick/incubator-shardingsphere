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

package org.apache.shardingsphere.transaction.xa.jta.connection;

import org.apache.shardingsphere.spi.database.DatabaseTypes;
import org.h2.jdbcx.JdbcXAConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.postgresql.xa.PGXAConnection;

import javax.sql.XADataSource;
import java.sql.Connection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public final class XAConnectionFactoryTest {
    
    @Mock
    private XADataSource xaDataSource;
    
    @Mock
    private Connection connection;
    
    @Test(expected = Exception.class)
    // TODO assert fail
    public void assertCreateMySQLXAConnection() {
        XAConnectionFactory.createXAConnection(DatabaseTypes.getActualDatabaseType("MySQL"), xaDataSource, connection);
    }
    
    @Test
    public void assertCreatePostgreSQLXAConnection() {
        assertThat(XAConnectionFactory.createXAConnection(DatabaseTypes.getActualDatabaseType("PostgreSQL"), xaDataSource, connection), instanceOf(PGXAConnection.class));
    }
    
    @Test
    public void assertCreateH2XAConnection() {
        assertThat(XAConnectionFactory.createXAConnection(DatabaseTypes.getActualDatabaseType("H2"), xaDataSource, connection), instanceOf(JdbcXAConnection.class));
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void assertCreateUnknownXAConnectionThrowsUnsupportedOperationException() {
        XAConnectionFactory.createXAConnection(DatabaseTypes.getActualDatabaseType("Oracle"), xaDataSource, connection);
    }
}
