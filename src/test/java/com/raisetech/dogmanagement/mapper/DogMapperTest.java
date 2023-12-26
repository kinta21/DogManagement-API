package com.raisetech.dogmanagement.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetech.dogmanagement.entity.Dog;
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
                    contains(new Dog(1, "シロ", "オス", "1歳", "フレンチ・ブルドック", "東北"));
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
            Dog dog = new Dog("おはぎ", "オス", "1歳", "パグ", "東北");
            dogMapper.createDog(dog);
        }
    }

    @Nested
    class UpdateDogTest{

        @Test
        @DataSet(value = "databases/dogs.yml")
        @ExpectedDataSet(value = "databases/updatedog.yml")
        @Transactional
        void 指定したidの犬の情報が更新できること(){
            Dog dog = new Dog(1,"おはぎ", "オス", "1歳", "パグ", "東北");
            dogMapper.updateDog(dog);
        }

        @Test
        @DataSet(value = "databases/dogs.yml")
        @ExpectedDataSet(value = "databases/dogs.yml")
        @Transactional
        void 指定したidが存在しないとき更新されないこと(){
            Dog dog = new Dog(99,"おはぎ", "オス", "1歳", "パグ", "東北");
            dogMapper.updateDog(dog);
        }
    }

    @Nested
    class DeleteDogTest{

        @Test
        @DataSet(value = "databases/dogs.yml")
        @ExpectedDataSet(value = "databases/deletedog.yml")
        @Transactional
        void 指定したIDの犬の情報を削除すること(){
            dogMapper.deleteById(1);
        }

        @Test
        @DataSet(value = "databases/dogs.yml")
        @Transactional
        void 指定したidが存在しないとき情報が削除されないこと(){
            dogMapper.deleteById(99);
        }
    }
}

