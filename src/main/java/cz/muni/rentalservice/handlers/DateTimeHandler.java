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
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author paynes
 */
@MappedTypes(DateTime.class)
public final class DateTimeHandler implements TypeHandler<DateTime> {

    private static final DateTimeZone DEFAULT_TIMEZONE = DateTimeZone.forID("GMT");

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, DateTime dateTime, JdbcType jdbcType) throws SQLException {
        if (dateTime == null) {
            return;
        }

        ps.setString(i, dateTime.toString(FORMATTER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
        if (rs == null) {
            return null;
        }

        final String dateTimeValue = rs.getString(columnName);
        return dateTimeValue == null //
                ? null //
                : FORMATTER.parseDateTime(dateTimeValue).withZoneRetainFields(DEFAULT_TIMEZONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        if (rs == null) {
            return null;
        }

        final String dateTimeValue = rs.getString(columnIndex);
        return dateTimeValue == null //
                ? null //
                : FORMATTER.parseDateTime(dateTimeValue).withZoneRetainFields(DEFAULT_TIMEZONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (cs == null) {
            return null;
        }

        final String dateTimeValue = cs.getString(columnIndex);
        return dateTimeValue == null //
                ? null //
                : FORMATTER.parseDateTime(dateTimeValue).withZoneRetainFields(DEFAULT_TIMEZONE);
    }    
}
