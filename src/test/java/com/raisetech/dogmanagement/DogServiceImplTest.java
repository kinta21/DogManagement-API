package com.raisetech.dogmanagement;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.exception.NotDogFoundException;
import com.raisetech.dogmanagement.mapper.DogMapper;
import com.raisetech.dogmanagement.service.DogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {
    @InjectMocks
    DogServiceImpl dogServiceImpl;

    @Mock
    DogMapper dogMapper;

    @Test
    public void 存在する犬のIDを指定した際に正常に犬の情報が返されること() throws Exception {
        doReturn(Optional.of(new Dog(1, "シロ", "オス", "1歳", "フレンチ・ブルドック", "東北"))).when(dogMapper).findById(1);

        Dog actual = dogServiceImpl.findById(1);
        assertThat(actual).isEqualTo(new Dog(1, "シロ", "オス", "1歳", "フレンチ・ブルドック", "東北"));
        verify(dogMapper, times(1)).findById(1);
    }

    @Test
    public void 存在しない犬のIDを指定した際に例外が返されること() throws Exception{
        doReturn(Optional.empty()).when(dogMapper).findById(6);

        assertThatThrownBy(
                () ->dogServiceImpl.findById(6)
        ).isInstanceOfSatisfying(
                NotDogFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("resource not found"));

    }
}
