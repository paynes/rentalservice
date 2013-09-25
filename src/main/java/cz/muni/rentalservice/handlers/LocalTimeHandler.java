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
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author paynes
 */
@MappedTypes(LocalTime.class)
public final class LocalTimeHandler implements TypeHandler<LocalTime> {

    /** Formatter for local time conversion to string and back. */
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("HH:mm");

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, LocalTime time, JdbcType jdbcType) throws SQLException {
        if (time == null) {
            return;
        }

        ps.setString(i, time.toString(FORMATTER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime getResult(ResultSet rs, String columnName) throws SQLException {
        if (rs == null) {
            return null;
        }

        final String timeValue = rs.getString(columnName);

        return timeValue == null ? null : FORMATTER.parseLocalTime(timeValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs == null) {
            return null;
        }

        final String timeValue = rs.getString(columnIndex);

        return timeValue == null ? null : FORMATTER.parseLocalTime(timeValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs == null) {
            return null;
        }

        final String timeValue = cs.getString(columnIndex);

        return timeValue == null ? null : FORMATTER.parseLocalTime(timeValue);
    } 
}
