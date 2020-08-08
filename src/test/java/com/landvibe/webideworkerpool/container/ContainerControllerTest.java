package com.landvibe.webideworkerpool.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landvibe.webideworkerpool.container.model.Container;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getContainers() throws Exception {
        LocalDateTime time = LocalDateTime.parse("2020-04-10T20:09:31", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        List<Container> containers = new ArrayList<>();
        containers.add(new Container(0, "container0", "description", "running", "nodejs:12", time, time));
        containers.add(new Container(1, "container1", "description", "running", "nodejs:12", time, time));
        containers.add(new Container(2, "container2", "description", "running", "nodejs:12", time, time));

        String expectedContent = objectMapper.writeValueAsString(containers);

        mockMvc.perform(get("/containers")
                .queryParam("limit", "30")
                .queryParam("skip", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent))
                .andDo(print());
    }

    @Test
    void getContainer() throws Exception {
        LocalDateTime time = LocalDateTime.parse("2020-04-10T20:09:31", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Container container = new Container(0, "container0", "description", "running", "nodejs:12", time, time);

        String expectedContent = objectMapper.writeValueAsString(container);

        mockMvc.perform(get("/containers/0"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent))
                .andDo(print());
    }
}