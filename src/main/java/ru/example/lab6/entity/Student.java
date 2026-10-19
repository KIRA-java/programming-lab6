package ru.example.lab6.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 15)
    private String name;

    @Column(name = "surname", length = 25)
    private String surname;

    @Column(name = "faculty", length = 20)
    private String faculty;

    @Column(name = "age")
    private Integer age;
}