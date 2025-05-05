package com.service.management.system.config.mybatis;

import com.service.management.system.domain.member.MemberType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberTypeHandler extends BaseTypeHandler<MemberType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MemberType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public MemberType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return MemberType.fromCode(code);
    }

    @Override
    public MemberType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return MemberType.fromCode(code);
    }

    @Override
    public MemberType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return MemberType.fromCode(code);
    }
}
