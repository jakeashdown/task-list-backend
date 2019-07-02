package com.jakeashdown.restbackenddemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakeashdown.restbackenddemo.boundary.TaskBoundary;
import com.jakeashdown.restbackenddemo.model.Task;
import com.jakeashdown.restbackenddemo.model.TaskWithId;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    private MockMvc mvc;

    @Mock
    private TaskBoundary taskBoundary;

    @InjectMocks
    private TaskController controller;

    // Initialized in setup()
    private JacksonTester<TaskWithId> jsonTaskWithId;
    private JacksonTester<List<TaskWithId>> jsonTasksWithId;

    private TaskWithId taskWithId1 = new TaskWithId(
            BigInteger.valueOf(1),
            "Get a job",
            "Hopefully something you actually like"
    );
    private TaskWithId taskWithId2 = new TaskWithId(
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
    public void queryAllTasksSuccess() throws Exception {
        // Given
        List<TaskWithId> expectedTasks = new ArrayList();
        expectedTasks.add(taskWithId1);
        expectedTasks.add(taskWithId2);
        Mockito.when(taskBoundary.selectAllTasks())
                .thenReturn(expectedTasks);

        // When
        MockHttpServletResponse response = mvc
                .perform(get("/task"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonTasksWithId.write(expectedTasks).getJson()
        );
    }

    @Test
    public void queryTaskForIdSuccessWithTaskForId() throws Exception {
        // Given
        Mockito.when(taskBoundary.selectTaskForId(BigInteger.valueOf(1)))
                .thenReturn(Optional.of(taskWithId1));

        // When
        MockHttpServletResponse response = mvc
                .perform(get("/task/1"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonTaskWithId.write(taskWithId1).getJson()
        );
    }


    @Test
    public void queryTaskForIdSuccessWithoutTaskForId() throws Exception {
        // Given
        Mockito.when(taskBoundary.selectTaskForId(BigInteger.valueOf(1))) // TODO: use matcher
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
    public void createTaskReturningIdSuccess() throws Exception {
        // Given
        final String title = "See your friends";
        final String description = "Book train to London";
        Mockito.when(taskBoundary.insertTaskReturningId(title, description))
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
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        String responseContent = response.getContentAsString();
        assertThat(responseContent).isEqualTo("123");
    }

    @Test
    public void updateTaskSuccess() throws Exception {
        // When
        final BigInteger id = BigInteger.valueOf(123);
        final String newTitle = "See Dave, Will and Emma";
        final String newDescription = "Book train to London with Yolanda";
        MockHttpServletResponse response = mvc
                .perform(
                        put("/task/" + id)
                                .content("{\"title\": \"" + newTitle + "\", \"description\": \"" + newDescription + "\"}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(taskBoundary).createOrUpdateTask(eq(id), eq(new Task(newTitle, newDescription)));
    }

    @Test
    public void deleteTaskSuccess() throws Exception {
        // When
        final BigInteger id = BigInteger.valueOf(123);
        MockHttpServletResponse response = mvc
                .perform(delete("/task/" + id))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Mockito.verify(taskBoundary).deleteTask(eq(id));
    }
}