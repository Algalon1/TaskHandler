package de.ait.taskhandler.repositories;

import de.ait.taskhandler.models.Task;

public interface TasksRepositories extends CrudRepository<Task>{

    Task findTaskByKeywordInTitle(String keyword);

}
