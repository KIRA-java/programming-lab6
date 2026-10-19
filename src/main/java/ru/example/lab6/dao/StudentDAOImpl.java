package ru.example.lab6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.example.lab6.entity.Student;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        return entityManager.createQuery(
                "select s from Student s order by s.id",
                Student.class
        ).getResultList();
    }

    @Override
    public Student getStudent(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student saveStudent(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
            return student;
        }

        return entityManager.merge(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student student = getStudent(id);

        if (student != null) {
            entityManager.remove(student);
        }
    }
}