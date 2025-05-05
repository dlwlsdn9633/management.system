package com.service.management.system.config.mybatis;

import com.service.management.system.domain.member.MajorType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MajorTypeHandler extends BaseTypeHandler<MajorType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MajorType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public MajorType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return MajorType.fromCode(code);
    }

    @Override
    public MajorType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return MajorType.fromCode(code);
    }

    @Override
    public MajorType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return MajorType.fromCode(code);
    }
}
