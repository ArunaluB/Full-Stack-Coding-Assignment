package edu.treinetic.taskmanager.servise;

import edu.treinetic.taskmanager.dto.TaskResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskResponseDTO> getAllTasks();
    Optional<TaskResponseDTO> getTaskById(String id);
    TaskResponseDTO createTask(TaskResponseDTO taskCreateDTO);
    TaskResponseDTO updateTask(String id, TaskResponseDTO updateDTO);
    boolean deleteTask(String id);
}
