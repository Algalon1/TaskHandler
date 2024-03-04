package de.ait.taskhandler.repositories.impl;

import de.ait.taskhandler.models.Task;
import de.ait.taskhandler.repositories.TasksRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class TasksRepositoriesJdbcImpl implements TasksRepositories {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM Tasks";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Task.class));
    }

    @Override
    public void save(Task model) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");

        jdbcInsert.withTableName("Tasks");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("title",model.getTitle());
        parameters.put("description",model.getDescription());
        parameters.put("assignTo",model.getAssignTo());

        long generatedID = jdbcInsert.executeAndReturnKey(parameters).longValue();
        model.setId(generatedID);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Task model) {

    }

    @Override
    public Task findTaskByKeywordInTitle(String keyword) {
        return null;
    }
}
