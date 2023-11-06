package com.raisetech.dogmanagement.mapper;

import com.raisetech.dogmanagement.entity.Dog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface DogMapper {

    @Select("SELECT * FROM dogs WHERE id = #{id}")
    Optional<Dog> findById(int id);

    @Insert("INSERT INTO dogs (name, dogSex, age, dogBreed, region) VALUES (#{name},#{dogSex},#{age},#{dogBreed},#{region})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createDog (Dog createDog);

    @Update("UPDATE dogs SET name = #{name}, dogSex =#{dogSex}, age = #{age}, dogBreed = #{dogBreed}, region = #{region} WHERE id = #{id}")
    void updateDog(Dog updateDog);

    @Delete("DELETE FROM dogs WHERE id = #{id}")
    void deleteById(int id);
}

