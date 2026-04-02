package com.example.todoApp.Service;

import com.example.todoApp.domain.Todo;
import com.example.todoApp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public void addTodo(String text) {
        Todo todo = new Todo();
        todo.setTodo(text);
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public void toggleTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 항목이 없습니다."));
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    public void updateTodo(Long id, String newText) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 항목이 없습니다."));
        todo.setTodo(newText);
        todoRepository.save(todo);
    }
}
