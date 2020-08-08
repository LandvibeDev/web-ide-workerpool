package com.landvibe.webideworkerpool.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landvibe.webideworkerpool.command.model.Command;
import com.landvibe.webideworkerpool.command.model.CommandResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void runCommand() throws Exception {
        String content = objectMapper.writeValueAsString(new Command("node index.js"));
        String expectedContent = objectMapper.writeValueAsString(new CommandResult("hello"));

        mockMvc.perform(post("/command/projects/0")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent))
                .andDo(print());
    }
}
