package ru.example.lab6.dao;

import ru.example.lab6.entity.Discipline;

import java.util.List;

public interface DisciplineDAO {

    List<Discipline> getAllDisciplines();

    Discipline getDiscipline(Integer id);

    Discipline saveDiscipline(Discipline discipline);

    void deleteDiscipline(Integer id);
}