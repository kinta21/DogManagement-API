package com.raisetech.dogmanagement;

import com.raisetech.dogmanagement.entity.DogSex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(DogSex.class)

public class EnumTypeHandler extends BaseTypeHandler<DogSex> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DogSex parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    @Override
    public DogSex getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String DogSex = (rs.getString(columnName));
        return null;
    }

    @Override
    public DogSex getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String DogSex = (rs.getString(columnIndex));
        return null;
    }

    @Override
    public DogSex getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String DogSex = (cs.getString(columnIndex));
        return null;
    }
}
