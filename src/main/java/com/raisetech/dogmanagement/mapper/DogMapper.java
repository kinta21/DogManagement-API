package com.raisetech.dogmanagement.mapper;

import com.raisetech.dogmanagement.entity.Dog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface DogMapper {

    @Select("SELECT * FROM dogs WHERE id = #{id}")
    Optional<Dog> findById(int id);

    @Insert("INSERT INTO dogs (name, sex, age, dogBreed, region) VALUES (#{name},#{sex},#{age},#{dogBreed},#{region})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createDog (Dog createDog);
}

