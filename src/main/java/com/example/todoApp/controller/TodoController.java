package com.example.todoApp.controller;

import com.example.todoApp.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("todos", todoService.findAll());
        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo") String todo){
        todoService.addTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }

    @PostMapping("/toggleTodo/{id}")
    public String toggleTodo(@PathVariable("id") Long id) {
        todoService.toggleTodo(id);
        return "redirect:/";
    }

    @PostMapping("/updateTodo/{id}")
    public String updateTodo(@PathVariable("id") Long id, @RequestParam("todo") String newTodo) {
        todoService.updateTodo(id, newTodo);
        return "redirect:/";
    }
}
