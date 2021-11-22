package com.example.jsonvalidator;

import com.example.jsonvalidator.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)


//config for MockMvc
@AutoConfigureMockMvc
@WebMvcTest(UserController.class)

//config for WebTestClient
//@WebFluxTest
class UserControllerTest {

    @Autowired
    UserController userController;

//    @Autowired
//    WebTestClient webTestClient;

    @Autowired
    MockMvc mockMvc;


    @Test
    void addUser() throws Exception {
        this.mockMvc
                .perform(
                        post("http://localhost:8081/api/v1/users")
                                .content("{\n\"age\" : 18\n}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    void addUserWithWebClient() {
//        webTestClient
//                .post().uri("http://localhost:8081/api/v1/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .bodyValue("{\n\"age\" : 18\n}")
//                .exchange().expectStatus().isBadRequest();
//}

}
