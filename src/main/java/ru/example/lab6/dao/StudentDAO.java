package ru.example.lab6.dao;

import ru.example.lab6.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student getStudent(Integer id);

    Student saveStudent(Student student);

    void deleteStudent(Integer id);
}