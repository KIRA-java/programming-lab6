package ru.example.lab6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.lab6.entity.Discipline;
import ru.example.lab6.service.DisciplineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    @GetMapping
    public ApiResponse<List<Discipline>> getAllDisciplines() {
        return new ApiResponse<>(
                true,
                "Список дисциплин получен",
                disciplineService.getAllDisciplines()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Discipline> getDiscipline(@PathVariable("id") Integer id) {
        return new ApiResponse<>(
                true,
                "Дисциплина найдена",
                disciplineService.getDiscipline(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Discipline> createDiscipline(
            @RequestBody Discipline discipline) {
        return new ApiResponse<>(
                true,
                "Дисциплина добавлена",
                disciplineService.createDiscipline(discipline)
        );
    }

    @PutMapping
    public ApiResponse<Discipline> updateDiscipline(
            @RequestBody Discipline discipline) {
        return new ApiResponse<>(
                true,
                "Данные дисциплины изменены",
                disciplineService.updateDiscipline(discipline)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDiscipline(@PathVariable("id") Integer id) {
        disciplineService.deleteDiscipline(id);

        return new ApiResponse<>(
                true,
                "Дисциплина удалена",
                null
        );
    }
}