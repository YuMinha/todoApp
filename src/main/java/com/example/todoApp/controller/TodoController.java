package com.example.todoApp.controller;

import com.example.todoApp.domain.Todo;
import com.example.todoApp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo") String todo){
        Todo toDo = new Todo();
        toDo.setTodo(todo);
        todoRepository.save(toDo);
        return "redirect:/";
    }

    @PostMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/toggleTodo/{id}")
    public String toggleTodo(@PathVariable("id") Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( ()-> new IllegalArgumentException("해당 포스트가 없습니다."));
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);

        return "redirect:/";
    }

    @PostMapping("/updateTodo/{id}")
    public String updateTodo(@PathVariable("id") Long id, @RequestParam("todo") String newTodo) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( ()-> new IllegalArgumentException("해당 항목이 없습니다."));
        todo.setTodo(newTodo);
        todoRepository.save(todo);

        return "redirect:/";
    }
}
