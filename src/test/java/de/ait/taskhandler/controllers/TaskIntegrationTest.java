package de.ait.taskhandler.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /tasks works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("tests")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "/application-test.properties")
class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /tasks")
    public class GetTasks {

        @Test
        public void return_empty_list_of_tasks() throws Exception {
            mockMvc.perform(get("/api/tasks"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(0)));
        }

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_list_of_tasks() throws Exception {
            mockMvc.perform(get("/api/tasks"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(4)))
                    // Проверки для первой строки
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[0].title", is("Implement user authentication feature")))
                    .andExpect(jsonPath("$.[0].description", is("Implement user authentication using JWT for secure access to the application")))
                    .andExpect(jsonPath("$.[0].assignTo", is("John")))
                    .andExpect(jsonPath("$.[0].priority", is("HIGH")))
                    .andExpect(jsonPath("$.[0].status", is("DRAFT")))
                    // Проверки для второй строки
                    .andExpect(jsonPath("$.[1].id", is(2)))
                    .andExpect(jsonPath("$.[1].title", is("Fix order calculation bug")))
                    .andExpect(jsonPath("$.[1].description", is("Resolve issue causing incorrect order total calculation in the checkout process")))
                    .andExpect(jsonPath("$.[1].assignTo", is("Sarah")))
                    .andExpect(jsonPath("$.[1].priority", is("MEDIUM")))
                    .andExpect(jsonPath("$.[1].status", is("TODO")))
                    // Проверки для третьей строки
                    .andExpect(jsonPath("$.[2].id", is(3)))
                    .andExpect(jsonPath("$.[2].title", is("Optimize database queries")))
                    .andExpect(jsonPath("$.[2].description", is("Identify and optimize slow-performing queries for improved application performance")))
                    .andExpect(jsonPath("$.[2].assignTo", is("Alex")))
                    .andExpect(jsonPath("$.[2].priority", is("HIGH")))
                    .andExpect(jsonPath("$.[2].status", is("TESTING")))
                    // Проверки для четвертой строки
                    .andExpect(jsonPath("$.[3].id", is(4)))
                    .andExpect(jsonPath("$.[3].title", is("Enhance UI for mobile devices")))
                    .andExpect(jsonPath("$.[3].description", is("Improve user experience by optimizing UI elements for better display on mobile screens")))
                    .andExpect(jsonPath("$.[3].assignTo", is("Emily")))
                    .andExpect(jsonPath("$.[3].priority", is("MEDIUM")))
                    .andExpect(jsonPath("$.[3].status", is("DRAFT")));
        }
    }

    @Nested
    @DisplayName("Post /tasks")
    public class PostTask {

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_task() throws Exception {
            mockMvc.perform(post("/api/tasks")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"Test task\",\n" +
                                    "  \"description\": \"Some description\",\n" +
                                    "  \"assignTo\": \"Max\",\n" +
                                    "  \"priority\": \"MEDIUM\",\n" +
                                    "  \"status\": \"DRAFT\"\n" +
                                    "}"))
                    // Проверка, что задача создана успешно и возвращается код ответа 201 (Created)
                    .andExpect(status().isCreated())
                    // Проверка, что возвращается объект задачи с указанным id (в данном случае 5)
                    .andExpect(jsonPath("$.id", is(5)))
                    // Проверка, что возвращенная задача имеет указанные значения полей
                    .andExpect(jsonPath("$.title", is("Test task")))
                    .andExpect(jsonPath("$.description", is("Some description")))
                    .andExpect(jsonPath("$.assignTo", is("Max")))
                    .andExpect(jsonPath("$.priority", is("MEDIUM")))
                    .andExpect(jsonPath("$.status", is("DRAFT")));
        }
    }

    @Nested
    @DisplayName("Get /tasks/{task-id}")
    public class GetTask {

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_existed_task() throws Exception {
            mockMvc.perform(get("/api/tasks/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.title", is("Implement user authentication feature")))
                    .andExpect(jsonPath("$.description", is("Implement user authentication using JWT for secure access to the application")))
                    .andExpect(jsonPath("$.assignTo", is("John")))
                    .andExpect(jsonPath("$.priority", is("HIGH")))
                    .andExpect(jsonPath("$.status", is("DRAFT")));
        }

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_404_for_not_existed_course() throws Exception {
            mockMvc.perform(get("/api/tasks/7"))
                    .andExpect(status().isNotFound());
        }

        @Test
        public void return_400_for_not_valid_task() throws Exception {
            mockMvc.perform(post("/api/tasks")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"Test task\",\n" +
                                    "  \"description\": \"Some description\",\n" +
                                    "  \"assignTo\": \"Max\",\n" +
                                    "  \"priority\": \"invalid\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors.size()", is(1)))
                    .andExpect(jsonPath("$.errors[0]", is("Priority must match \"^(HIGHEST|HIGH|MEDIUM|LOW|LOWEST)$\"")));

        }
    }
}
