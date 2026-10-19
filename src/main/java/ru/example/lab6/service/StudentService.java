package ru.example.lab6.service;

import ru.example.lab6.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudent(Integer id);

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Integer id);
}