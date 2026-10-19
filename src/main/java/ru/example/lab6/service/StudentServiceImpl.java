package ru.example.lab6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.lab6.dao.StudentDAO;
import ru.example.lab6.entity.Student;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public Student getStudent(Integer id) {
        Student student = studentDAO.getStudent(id);

        if (student == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Студент с id=" + id + " не найден"
            );
        }

        return student;
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        validate(student);
        student.setId(null);
        return studentDAO.saveStudent(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        if (student.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Для изменения студента укажите id"
            );
        }

        getStudent(student.getId());
        validate(student);

        return studentDAO.saveStudent(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Integer id) {
        getStudent(id);
        studentDAO.deleteStudent(id);
    }

    private void validate(Student student) {
        if (student.getName() == null || student.getName().isBlank()
                || student.getSurname() == null || student.getSurname().isBlank()
                || student.getFaculty() == null || student.getFaculty().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Заполните имя, фамилию и факультет"
            );
        }

        if (student.getName().length() > 15
                || student.getSurname().length() > 25
                || student.getFaculty().length() > 20) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Максимальная длина: имя — 15, фамилия — 25, факультет — 20 символов"
            );
        }

        if (student.getAge() == null || student.getAge() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Возраст должен быть неотрицательным числом"
            );
        }
    }
}