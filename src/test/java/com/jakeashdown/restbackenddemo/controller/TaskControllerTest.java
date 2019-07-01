package com.jakeashdown.restbackenddemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakeashdown.restbackenddemo.boundary.TaskBoundary;
import com.jakeashdown.restbackenddemo.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    private MockMvc mvc;

    @Mock
    private TaskBoundary taskBoundary;

    @InjectMocks
    private TaskController controller;

    private JacksonTester<Task> jsonTask;
    private JacksonTester<List<Task>> jsonTasks;

    private Task task1 = new Task(
            BigInteger.valueOf(1),
            "Get a job",
            "Hopefully something you actually like"
    );
    private Task task2 = new Task(
            BigInteger.valueOf(2),
            "Climb 7a",
            "Maybe 'Shakira'"
    );

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
        // Given
        List<Task> expectedTasks = new ArrayList();
        expectedTasks.add(task1);
        expectedTasks.add(task2);
        Mockito.when(taskBoundary.getAllTasks())
                .thenReturn(expectedTasks);

        // When
        MockHttpServletResponse response = mvc
                .perform(get("/task"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonTasks.write(expectedTasks).getJson()
        );
    }

    @Test
    public void getTaskForIdSuccessWithTaskForId() throws Exception {
        // Given
        Mockito.when(taskBoundary.getTaskForId(BigInteger.valueOf(1)))
                .thenReturn(Optional.of(task1));

        // When
        MockHttpServletResponse response = mvc
                .perform(get("/task/1"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonTask.write(task1).getJson()
        );
    }


    @Test
    public void getTaskForIdSuccessWithoutTaskForId() throws Exception {
        // Given
        Mockito.when(taskBoundary.getTaskForId(BigInteger.valueOf(1))) // TODO: use matcher
                .thenReturn(Optional.empty());

        // When
        MockHttpServletResponse response = mvc
                .perform(get("/task/1"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("null");
    }

    @Test
    public void createTaskSuccess() throws Exception {
        // Given
        final String title = "See your friends";
        final String description = "Book train to London";
        Mockito.when(taskBoundary.createTaskReturningId(title, description))
                .thenReturn(BigInteger.valueOf(123));

        // When
        MockHttpServletResponse response = mvc
                .perform(
                        post("/task")
                        .content("{\"title\": \"" + title + "\", \"description\": \"" + description + "\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String responseContent = response.getContentAsString();
        assertThat(responseContent).isEqualTo("123");
    }
}