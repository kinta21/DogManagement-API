package com.raisetech.dogmanagement.mapper;

import com.raisetech.dogmanagement.entity.Dog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface DogMapper {

    @Select("SELECT * FROM dogs WHERE id = #{id}")
    Optional<Dog> findById(int id);
}
