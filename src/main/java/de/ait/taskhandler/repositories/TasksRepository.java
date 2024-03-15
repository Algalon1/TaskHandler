package de.ait.taskhandler.repositories;

import de.ait.taskhandler.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Long> {
}
