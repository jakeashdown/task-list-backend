package com.jakeashdown.restbackenddemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakeashdown.restbackenddemo.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    private MockMvc mvc;

    @InjectMocks
    private TaskController controller;

    private JacksonTester<List<Task>> jsonTasks;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void getAllTasksSuccess() throws Exception {
        // When
        MockHttpServletResponse response = mvc
                .perform(get("/"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        List<Task> expectedContent = new ArrayList();
        expectedContent.add(new Task(
                1,
                "Get a job",
                new ArrayList<>()
                )
        );
        assertThat(response.getContentAsString()).isEqualTo(
                jsonTasks.write(expectedContent).getJson()
        );
    }
}
