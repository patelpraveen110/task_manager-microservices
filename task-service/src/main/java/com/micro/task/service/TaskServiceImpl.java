package com.micro.task.service;

import com.micro.task.model.Task;
import com.micro.task.model.TaskStatus;
import com.micro.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if(!requesterRole.equals("ROLE_ADMIN")){
            throw new Exception("only admin can create task");
        }
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(
                ()-> new Exception("Task not found with id"+ id));
    }

    @Override
    public List<Task> getAllTasks(TaskStatus status) {
        List<Task> allTask = taskRepository.findAll();

        return allTask.stream().filter(
                task -> status == null || task.getTaskStatus()
                             .name()
                             .equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);
        if(updatedTask.getTitle()!=null){
            existingTask.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getDescription()!=null){
            existingTask.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getImage()!=null){
            existingTask.setImage(updatedTask.getImage());
        }
//        if(updatedTask.getAssignedUserId()!=null){
//            existingTask.setAssignedUserId(updatedTask.getAssignedUserId());
//        }
//        if(updatedTask.getTags()!=null){
//            existingTask.setTags(updatedTask.getTags());
//        }
        if(updatedTask.getDeadLine()!=null){
            existingTask.setDeadLine(updatedTask.getDeadLine());
        }
//        if(updatedTask.getCreatedAt()!=null){
//            existingTask.setCreatedAt(updatedTask.getCreatedAt());
//        }
        if(updatedTask.getTaskStatus()!=null){
            existingTask.setTaskStatus(updatedTask.getTaskStatus());
        }
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        Task existingTask = getTaskById(id);
        if(existingTask!=null)
          taskRepository.deleteById(id);
    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setTaskStatus(TaskStatus.ASSIGNED);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUsersTask(Long userId, TaskStatus status) {
        List<Task> tasks = taskRepository.findByAssignedUserId(userId);
        return tasks.stream().filter(
                task -> status == null || task.getTaskStatus()
                        .name()
                        .equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setTaskStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }
}
