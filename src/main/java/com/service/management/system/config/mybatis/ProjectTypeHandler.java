package com.service.management.system.config.mybatis;

import com.service.management.system.domain.enums.ProjectType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  TypeHandler는 MyBatis가 직접 관리하는 개체이기 때문에, application.yml에서만 설정하면 된다.
 * */

public class ProjectTypeHandler extends BaseTypeHandler<ProjectType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ProjectType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    @Override
    public ProjectType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return ProjectType.fromCode(code);
    }

    @Override
    public ProjectType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return ProjectType.fromCode(code);
    }

    @Override
    public ProjectType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return ProjectType.fromCode(code);
    }
}
