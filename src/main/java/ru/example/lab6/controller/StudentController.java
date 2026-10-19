package ru.example.lab6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.lab6.entity.Student;
import ru.example.lab6.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ApiResponse<List<Student>> getAllStudents() {
        return new ApiResponse<>(
                true,
                "Список студентов получен",
                studentService.getAllStudents()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Student> getStudent(@PathVariable("id") Integer id) {
        return new ApiResponse<>(
                true,
                "Студент найден",
                studentService.getStudent(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Student> createStudent(@RequestBody Student student) {
        return new ApiResponse<>(
                true,
                "Студент добавлен",
                studentService.createStudent(student)
        );
    }

    @PutMapping
    public ApiResponse<Student> updateStudent(@RequestBody Student student) {
        return new ApiResponse<>(
                true,
                "Данные студента изменены",
                studentService.updateStudent(student)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);

        return new ApiResponse<>(
                true,
                "Студент удалён",
                null
        );
    }
}