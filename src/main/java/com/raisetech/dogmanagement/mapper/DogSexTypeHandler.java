package com.raisetech.dogmanagement.mapper;

import com.raisetech.dogmanagement.entity.DogSex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@MappedTypes(DogSex.class)
public class DogSexTypeHandler extends BaseTypeHandler<DogSex> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DogSex parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getDogSex());
    }

    @Override
    public DogSex getNullableResult(java.sql.ResultSet rs, String columnName) throws SQLException {
        return DogSex.from(rs.getString(columnName));
    }

    @Override
    public DogSex getNullableResult(java.sql.ResultSet rs, int columnIndex) throws SQLException {
        return DogSex.from(rs.getString(columnIndex));
    }

    @Override
    public DogSex getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        return DogSex.valueOf(cs.getString(columnIndex));
    }

}

