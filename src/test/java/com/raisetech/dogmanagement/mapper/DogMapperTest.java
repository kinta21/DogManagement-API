package com.raisetech.dogmanagement.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.entity.DogSex;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)

class DogMapperTest {
    @Autowired
    DogMapper dogMapper;

    @Test
    @DataSet(value = "databases/dogs.yml")
    @Transactional
    void 存在する犬のIDを指定した時に正常に犬の情報が返されること() {
        assertThat(dogMapper.findById(1)).
                contains(new Dog(1, "シロ", DogSex.MALE, "1歳", "フレンチ・ブルドック", "東北"));
    }

    @Test
    @DataSet(value = "databases/dogs.yml")
    @Transactional
    void 存在しない犬のIDを指定した際に例外を返すこと(){
        assertThat(dogMapper.findById(99)).isEmpty();
    }
}

