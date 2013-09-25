/*
 * Copyright 2013 paynes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.muni.rentalservice.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.LocalDateTime;

/**
 *
 * @author paynes
 */
@MappedTypes(LocalDateTime.class)
public final class LocalDateTimeHandler implements TypeHandler<LocalDateTime> {    

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDateTime dateTime, JdbcType jdbcType) throws SQLException {
        if (dateTime == null) {
            return;
        }
        Timestamp timestamp = new Timestamp(dateTime.toDate().getTime());
        ps.setTimestamp(i, timestamp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        if (rs == null) {
            return null;
        }

        final Timestamp timestamp = rs.getTimestamp(columnName);
        return timestamp == null ? null : new LocalDateTime(timestamp.getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs == null) {
            return null;
        }

        final Timestamp timestamp = rs.getTimestamp(columnIndex);
        return timestamp == null ? null :new LocalDateTime(timestamp.getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs == null) {
            return null;
        }

        final Timestamp timestamp = cs.getTimestamp(columnIndex);
        return timestamp == null ? null : new LocalDateTime(timestamp.getTime());
    }    
}
