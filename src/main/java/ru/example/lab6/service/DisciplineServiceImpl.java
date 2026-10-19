package ru.example.lab6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.lab6.dao.DisciplineDAO;
import ru.example.lab6.entity.Discipline;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineDAO disciplineDAO;

    @Override
    public List<Discipline> getAllDisciplines() {
        return disciplineDAO.getAllDisciplines();
    }

    @Override
    public Discipline getDiscipline(Integer id) {
        Discipline discipline = disciplineDAO.getDiscipline(id);

        if (discipline == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Дисциплина с id=" + id + " не найдена"
            );
        }

        return discipline;
    }

    @Override
    @Transactional
    public Discipline createDiscipline(Discipline discipline) {
        validate(discipline);
        discipline.setId(null);
        return disciplineDAO.saveDiscipline(discipline);
    }

    @Override
    @Transactional
    public Discipline updateDiscipline(Discipline discipline) {
        if (discipline.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Для изменения дисциплины укажите id"
            );
        }

        getDiscipline(discipline.getId());
        validate(discipline);

        return disciplineDAO.saveDiscipline(discipline);
    }

    @Override
    @Transactional
    public void deleteDiscipline(Integer id) {
        getDiscipline(id);
        disciplineDAO.deleteDiscipline(id);
    }

    private void validate(Discipline discipline) {
        if (discipline.getName() == null
                || discipline.getName().isBlank()
                || discipline.getName().length() > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Укажите название дисциплины длиной до 100 символов"
            );
        }

        if (discipline.getHours() == null || discipline.getHours() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Количество часов должно быть больше нуля"
            );
        }
    }
}