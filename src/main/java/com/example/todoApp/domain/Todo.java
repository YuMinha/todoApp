package com.example.todoApp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity(name = "Todo")
@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String todo;

    private Boolean completed = false;
}
