package com.raisetech.dogmanagement.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class DogRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;


    @Nested
    class FindByIdTest {
        @Test
        @DataSet(value = "databases/dogs.yml")
        @Transactional
        void 存在するIDを指定した際にステータスコード200を返すこと()throws Exception{
         String response = mockMvc.perform(MockMvcRequestBuilders.get("/dogs/{id}",1 ))
                 .andExpect(status().isOk())
                 .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);


            JSONAssert.assertEquals("""
                    {
                      "id":1,
                      "name":"シロ",
                      "dogSex":"オス",
                      "age":"1歳",
                      "dogBreed":"フレンチ・ブルドック",
                      "region":"東北"
                    }
                       """, response, JSONCompareMode.STRICT);
        }

        @Test
        @DataSet(value = "databases/dogs.yml")
        @Transactional
        void 指定したIDが存在しない時ステータスコード404を返すこと() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.get("/dogs/{id}", 99))
                            .andExpect(status().isNotFound())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        }
    }
}

