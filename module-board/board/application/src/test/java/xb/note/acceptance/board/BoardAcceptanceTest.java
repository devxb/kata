package xb.note.acceptance.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import xb.note.acceptance.user.UserInitializer;
import xb.note.controller.board.request.AuthorRequest;
import xb.note.controller.board.request.BoardRequest;
import xb.note.controller.board.response.BoardResponse;
import xb.note.controller.user.response.UserResponse;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardAcceptanceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Queue<UserResponse> deleteUserQueue = new LinkedList<>();
    private Queue<BoardResponse> deleteBoardQueue = new LinkedList<>();

    @AfterEach
    @BeforeEach
    public void DELETE_ALL() throws Exception {
        deleteAllBoard();
        deleteAllUser();
    }

    private void deleteAllBoard() throws Exception{
        while(!deleteBoardQueue.isEmpty())
            mvc.perform(MockMvcRequestBuilders.delete("/article/{article-id}", deleteBoardQueue.poll().getId()));
        mvc.perform(MockMvcRequestBuilders.get("/article")).andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string("[]")
        );
    }

    private void deleteAllUser() throws Exception{
        while(!deleteUserQueue.isEmpty())
            UserInitializer.deprecateUser(mvc, deleteUserQueue.poll().getId());
    }

    @Test
    @DisplayName("글 조회하기 0개 글이 저장되었을때")
    public void READ_ARTICLE_TEST() throws Exception{
        // given
        String url = "/article";

        // when
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get(url));

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),
                MockMvcResultMatchers.content().string("[]")
        );
    }

    @Test
    @DisplayName("글 조회하기 2개글이 저장 되었을때")
    public void READ_TWO_ARTICLE_TEST() throws Exception{
        // given
        String url = "/article";
        UserResponse user1 = UserInitializer.saveAndGetUser(mvc, objectMapper, "devxb");
        String article1 = objectMapper.writeValueAsString(new BoardRequest("hello world", "hello world too", new AuthorRequest(user1.getId(), user1.getName())));
        UserResponse user2 = UserInitializer.saveAndGetUser(mvc, objectMapper, "octocat");
        String article2 = objectMapper.writeValueAsString(new BoardRequest("hello world 2", "hello world too 2", new AuthorRequest(user2.getId(), user2.getName())));

        // when
        mvc.perform(MockMvcRequestBuilders.post("/article").content(article1).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        mvc.perform(MockMvcRequestBuilders.post("/article").content(article2).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn().getRequest().getContentAsString();
        deleteUserQueue.add(user1);
        deleteUserQueue.add(user2);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/article"));
        BoardResponse[] boardResponses = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), BoardResponse[].class);
        deleteBoardQueue.add(boardResponses[0]);
        deleteBoardQueue.add(boardResponses[1]);

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON),

                MockMvcResultMatchers.jsonPath("$.[0].title").value("hello world"),
                MockMvcResultMatchers.jsonPath("$.[0].author.id").value(user1.getId()),
                MockMvcResultMatchers.jsonPath("$.[0].author.name").value(user1.getName()),

                MockMvcResultMatchers.jsonPath("$.[1].title").value("hello world 2"),
                MockMvcResultMatchers.jsonPath("$.[1].author.id").value(user2.getId()),
                MockMvcResultMatchers.jsonPath("$.[1].author.name").value(user2.getName())
        ).andDo(MockMvcResultHandlers.print());
    }

}
