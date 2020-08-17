package com.landvibe.webideworkerpool.container;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landvibe.webideworkerpool.container.controller.ContainerController;
import com.landvibe.webideworkerpool.container.model.Container;
import com.landvibe.webideworkerpool.container.service.ContainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContainerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ContainerService containerService;

    @InjectMocks
    private ContainerController containerController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(containerController).build();
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getContainers() throws Exception {
        // given
        String time = Instant.now().toString();
        List<Container> expected = new ArrayList<>();
        String userId = "user1";
        expected.add(new Container(UUID.randomUUID().toString(), "container0", "description", "running", "nodejs:12", time, time, userId));
        expected.add(new Container(UUID.randomUUID().toString(), "container1", "description", "running", "nodejs:12", time, time, userId));
        expected.add(new Container(UUID.randomUUID().toString(), "container2", "description", "running", "nodejs:12", time, time, userId));

        given(containerService.getContainers(userId, 30, 0)).willReturn(expected);


        // when, then
        mockMvc.perform(get("/containers?userId=" + userId)
                .queryParam("limit", "30")
                .queryParam("skip", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expected)))
                .andDo(print());

        verify(containerService, times(1)).getContainers(userId, 30, 0);
    }

    @Test
    void getContainer() throws Exception {
        String time = Instant.now().toString();
        String userId = "user1";
        String cid = UUID.randomUUID().toString();

        Container container = new Container(cid, "container0", "description", "running", "nodejs:12", time, time, userId);
        Optional<Container> expected = Optional.of(container);

        String expectedContent = objectMapper.writeValueAsString(expected);

        given(containerService.getContainer(cid)).willReturn(expected);


        mockMvc.perform(get("/containers/" + cid))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent))
                .andDo(print());

        verify(containerService, times(1)).getContainer(cid);
    }
}