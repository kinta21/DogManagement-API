package com.raisetech.dogmanagement.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.entity.DogSex;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class FindByIdTest {
        @Test
        @DataSet(value = "databases/dogs.yml")
        @Transactional
        void 存在する犬のIDを指定した時に正常に犬の情報が取得できること() {
            assertThat(dogMapper.findById(1)).
                    contains(new Dog(1, "シロ", DogSex.MALE, "1歳", "フレンチ・ブルドック", "東北"));
        }

        @Test
        @DataSet(value = "databases/dogs.yml")
        @Transactional
        void 存在しない犬のIDを指定した際に空のOptionalを取得すること() {
            assertThat(dogMapper.findById(99)).isEmpty();
        }
    }

    @Nested
    class CreateDogTest{

        @Test
        @DataSet(value = "databases/dogs.yml")
        @ExpectedDataSet(value = "databases/createdog.yml", ignoreCols = "id")
        @Transactional
        void 新規の犬の情報が登録できること(){
            Dog dog = new Dog("おはぎ", DogSex.MALE, "1歳", "パグ", "東北");
            dogMapper.createDog(dog);
        }
    }
}

