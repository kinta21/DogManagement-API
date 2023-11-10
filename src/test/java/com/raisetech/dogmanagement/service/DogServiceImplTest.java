package com.raisetech.dogmanagement.service;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.entity.DogSex;
import com.raisetech.dogmanagement.exception.NotDogFoundException;
import com.raisetech.dogmanagement.form.DogCreateForm;
import com.raisetech.dogmanagement.mapper.DogMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @InjectMocks
    DogServiceImpl dogServiceImpl;

    @Mock
    DogMapper dogMapper;

    @Nested
    class FindByDogTest{
        @Test
        public void 存在する犬のIDを指定した際に正常に犬の情報が返されること(){
        doReturn(Optional.of(new Dog(1, "シロ", DogSex.MALE, "1歳", "フレンチ・ブルドック", "東北"))).when(dogMapper).findById(1);

        Dog actual = dogServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Dog(1, "シロ", DogSex.MALE, "1歳", "フレンチ・ブルドック", "東北"));
        verify(dogMapper, times(1)).findById(1);
    }
        @Test
        public void 存在しない犬のIDを指定した際に例外が返されること(){
        doReturn(Optional.empty()).when(dogMapper).findById(6);

        assertThatThrownBy(
                () ->dogServiceImpl.findById(6)
        ).isInstanceOfSatisfying(
                NotDogFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("resource not found"));
        }
    }

    @Nested
    class CreateDogTest {
        @Test
        public void formから取得した内容で犬の情報が登録できること() {
            DogCreateForm form = new DogCreateForm("おはぎ", DogSex.MALE, "1歳", "パグ", "東北");
            Dog dog = new Dog("おはぎ", DogSex.MALE, "1歳", "パグ", "東北");
            doNothing().when(dogMapper).createDog(dog);

            assertThat(dogServiceImpl.createDog("おはぎ", DogSex.MALE, "1歳", "パグ", "東北")).isEqualTo(dog);
            verify(dogMapper, times(1)).createDog(dog);
        }
    }

    @Nested
    class UpdateDogTest{
        @Test
        public void 指定したIDのデータ情報を入力データで更新ができること() {
            doReturn(Optional.of(new Dog(1, "シロ", DogSex.MALE, "1歳", "フレンチ・ブルドック", "東北"))).when(dogMapper).findById(1);
            dogServiceImpl.updateDog(1, "おはぎ", DogSex.MALE, "1歳", "パグ", "東北");

            verify(dogMapper, times(1)).findById(1);
            verify(dogMapper, times(1)).updateDog(new Dog(1, "おはぎ", DogSex.MALE, "1歳", "パグ", "東北"));
        }

        @Test
        public void 更新指定した犬のIDが存在しないとき例外を返すこと() {
            doReturn(Optional.empty()).when(dogMapper).findById(99);

            assertThatThrownBy(() -> dogServiceImpl.updateDog(99, "おはぎ", DogSex.MALE, "1歳", "パグ", "東北"))
                    .isInstanceOfSatisfying(NotDogFoundException.class, e ->{
                        assertThat(e.getMessage()).isEqualTo("resource not found");
                            });
            verify(dogMapper, times(1)).findById(99);
        }
    }
    @Nested
    class DeleteDogTest{
        @Test
        public void 指定したIDのデータが削除できること(){
                doReturn(Optional.of(new Dog(1, "シロ", DogSex.MALE, "1歳", "フレンチ・ブルドック", "東北"))).when(dogMapper).findById(1);
                dogServiceImpl.deleteById(1);
                verify(dogMapper,times(1)).findById(1);
                verify(dogMapper,times(1)).deleteById(1);

            }

        @Test
        public void 存在しないIDのデータを削除したときに例外を返すこと() {
            doReturn(Optional.empty()).when(dogMapper).findById(99);

            assertThatThrownBy(
                    () -> dogServiceImpl.deleteById(99)
            ).isInstanceOfSatisfying(
                    NotDogFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("resource not found"));
            verify(dogMapper, times(1)).findById(99);
        }
    }
}

