package ru.example.lab6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.example.lab6.entity.Discipline;

import java.util.List;

@Repository
public class DisciplineDAOImpl implements DisciplineDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Discipline> getAllDisciplines() {
        return entityManager.createQuery(
                "select d from Discipline d order by d.id",
                Discipline.class
        ).getResultList();
    }

    @Override
    public Discipline getDiscipline(Integer id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        if (discipline.getId() == null) {
            entityManager.persist(discipline);
            return discipline;
        }

        return entityManager.merge(discipline);
    }

    @Override
    public void deleteDiscipline(Integer id) {
        Discipline discipline = getDiscipline(id);

        if (discipline != null) {
            entityManager.remove(discipline);
        }
    }
}