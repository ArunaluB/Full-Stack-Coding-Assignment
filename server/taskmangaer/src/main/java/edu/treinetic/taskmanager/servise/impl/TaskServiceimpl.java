package edu.treinetic.taskmanager.servise.impl;

import edu.treinetic.taskmanager.document.Task;
import edu.treinetic.taskmanager.dto.TaskResponseDTO;
import edu.treinetic.taskmanager.repository.TaskRepository;
import edu.treinetic.taskmanager.servise.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceimpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, TaskResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskResponseDTO> getTaskById(String id) {
        return taskRepository.findById(id)
                .map(task -> modelMapper.map(task, TaskResponseDTO.class));
    }

    @Override
    public TaskResponseDTO createTask(TaskResponseDTO taskCreateDTO) {
        Task task = modelMapper.map(taskCreateDTO, Task.class);
        task.setCreatedAt(LocalDateTime.now());
        Task saved = taskRepository.save(task);
        return modelMapper.map(saved, TaskResponseDTO.class);
    }

    @Override
    public TaskResponseDTO updateTask(String id, TaskResponseDTO updateDTO) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updateDTO.getTitle());
            task.setDescription(updateDTO.getDescription());
            task.setStatus(updateDTO.getStatus());
            Task updated = taskRepository.save(task);
            return modelMapper.map(updated, TaskResponseDTO.class);
        }).orElse(null);
    }

    @Override
    public boolean deleteTask(String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
