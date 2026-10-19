package ru.example.lab6.service;

import ru.example.lab6.entity.Discipline;

import java.util.List;

public interface DisciplineService {

    List<Discipline> getAllDisciplines();

    Discipline getDiscipline(Integer id);

    Discipline createDiscipline(Discipline discipline);

    Discipline updateDiscipline(Discipline discipline);

    void deleteDiscipline(Integer id);
}