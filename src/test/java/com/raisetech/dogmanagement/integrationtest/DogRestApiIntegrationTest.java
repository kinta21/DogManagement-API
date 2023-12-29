package com.raisetech.dogmanagement.integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

            JSONAssert.assertEquals("""
                                        {
                                            "path": "/dogs/99",
                                            "status": "404",
                                            "message": "resource not found",
                                            "timestamp": "2023-12-26T20:20:46.478599+09:00[Asia/Tokyo]",
                                            "error": "Not Found"
                                        } 
    
                                         """, response,
                    new CustomComparator(JSONCompareMode.STRICT,
                            new Customization("timestamp", ((o1, o2) -> true))));
        }
    }

    @Nested
    class CreateDogTest{

        @Test
        @DataSet(value = "databases/dogs.yml")
        @ExpectedDataSet(value = "databases/createdog.yml", ignoreCols = "id")
        @Transactional
        void 犬の情報を新規登録できること() throws Exception{
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.post("/dogs")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                        {
                                        "name":"おはぎ",
                                        "dogSex":"オス",
                                        "age":"1歳",
                                        "dogBreed":"パグ",
                                        "region":"東北"
                                        }
                                        """))
                            .andExpect(MockMvcResultMatchers.status().isCreated())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
            JSONAssert.assertEquals("""
                                      {
                                        "id":6,
                                        "name":"おはぎ",
                                        "dogSex":"オス",
                                        "age":"1歳",
                                        "dogBreed":"パグ",
                                        "region":"東北"
                                      }
                                      """, response,
                    new CustomComparator(JSONCompareMode.STRICT,
                            new Customization("id", ((o1, o2) -> true))));
        }
    }

    @Nested
    class UpdateDogTest {
        @Test
        @DataSet(value = "databases/dogs.yml")
        @ExpectedDataSet(value = "databases/updatedog.yml")
        @Transactional
        void 指定したidの犬の情報が更新できること()throws Exception{
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/dogs/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                             {
                                                    "id": 1,
                                                    "name": "おはぎ",
                                                    "dogSex": "オス",
                                                    "age": "1歳",
                                                    "dogBreed": "パグ",
                                                    "region": "東北"
                                             }      
                                            """))
                            .andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                      "message": "dog successfully updated"
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 指定したidが存在しないとき更新した時ステータスコード404を返すこと() throws Exception {
            String response = mockMvc.perform(MockMvcRequestBuilders.patch("/dogs/{id}", 99)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                      "id": 99,
                                      "name": "おはぎ",
                                      "dogSex": "オス",
                                      "age": "1歳",
                                      "dogBreed": "パグ",
                                      "region": "東北"
                                    }
                                    """))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                                        {
                                            "path": "/dogs/99",
                                            "status": "404",
                                            "message": "resource not found",
                                            "timestamp": "2023-12-26T20:20:46.478599+09:00[Asia/Tokyo]",
                                            "error": "Not Found"
                                        } 
                                        """, response,
                    new CustomComparator(JSONCompareMode.STRICT,
                            new Customization("timestamp", ((o1, o2) -> true))));
        }

        @Test
        @Transactional
        void 更新時に空文字でされた場合ステータスコード400が返されること() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/dogs/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                    {
                                      "id":"",
                                      "name": "",
                                      "dogSex": "",
                                      "age": "",
                                      "dogBreed": "",
                                      "region": ""
                                    }
                                    """))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                            {
                              "type": "about:blank",
                              "title": "Bad Request",
                              "status": 400,
                              "detail": "Invalid request content.",
                              "instance": "/dogs/1"
                            }
                              """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にnameが20文字以上で入力された場合ステータスコード400が返されること() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/dogs/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                            {
                                              "id": 1,
                                              "name": "1234567890123456789012345678901",
                                              "dogSex": "オス",
                                              "age": "1歳",
                                              "dogBreed": "パグ",
                                              "region": "東北"
                                                     }
                                            """))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                    {
                           "type": "about:blank",
                           "title": "Bad Request",
                           "status": 400,
                           "detail": "Invalid request content.",
                           "instance": "/dogs/1"
                    }
                    """, response, JSONCompareMode.STRICT);
        }

        @Test
        @Transactional
        void 更新時にがdogBreedが３0文字以上で入力された場合ステータスコード400が返されること() throws Exception {
            String response =
                    mockMvc.perform(MockMvcRequestBuilders.patch("/dogs/1")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("""
                                            {
                                              "id": 1,
                                              "name": "おはぎ",
                                              "dogSex": "オス",
                                              "age": "1歳",
                                              "dogBreed": "1234567890123456789012345678901",
                                              "region": "東北"
                                                     }
                                            """))
                            .andExpect(status().isBadRequest())
                            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            JSONAssert.assertEquals("""
                             {
                               "type": "about:blank",
                               "title": "Bad Request",
                               "status": 400,
                               "detail": "Invalid request content.",
                               "instance": "/dogs/1"
                             }
                             """, response, JSONCompareMode.STRICT);
        }
    }
}

