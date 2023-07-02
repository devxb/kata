package xb.note.acceptance.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;
import xb.note.controller.user.request.UserSaveRequest;
import xb.note.controller.user.response.UserResponse;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("UAT")
public class UserAcceptanceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Queue<UserResponse> deleteUserQue = new LinkedList<>();

    @Test
    @DisplayName("새로운 유저 저장")
    public void CREATE_NEW_USER_TEST() throws Exception{
        // given
        String name = "devxb";

        // when
        mvc.perform(MockMvcRequestBuilders.post("/user").content(objectMapper.writeValueAsString(new UserSaveRequest(name))).contentType(MediaType.APPLICATION_JSON));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/user/{name}", "devxb"));

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.name").value(name)
        );

        deleteUserQue.add(objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class));
    }

    @AfterEach
    @BeforeEach
    public void DELETE_ALL_USER() throws Exception{
        // when
        for(UserResponse user : deleteUserQue)
            mvc.perform(MockMvcRequestBuilders.delete("/user/{id}", String.valueOf(user.getId())));

        // then
        while(!deleteUserQue.isEmpty()){
            UserResponse user = deleteUserQue.poll();
            Assertions.assertThrows(NestedServletException.class, ()-> mvc.perform(MockMvcRequestBuilders.get("/user/{name}", user.getName())));
        }
    }

}
