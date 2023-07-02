package xb.note.acceptance.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import xb.note.controller.user.request.UserSaveRequest;
import xb.note.controller.user.response.UserResponse;

public final class UserInitializer {

    public static UserResponse saveAndGetUser(MockMvc mvc, ObjectMapper objectMapper, String name) throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/user")
                .content(objectMapper.writeValueAsString(new UserSaveRequest(name)))
                .contentType(MediaType.APPLICATION_JSON));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/user/{name}", name));
        return objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class);
    }

    public static void deprecateUser(MockMvc mvc, int id) throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/user/{id}", String.valueOf(id)));
    }

}
