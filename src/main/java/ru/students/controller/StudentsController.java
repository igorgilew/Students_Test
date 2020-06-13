package ru.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.students.entity.Student;
import ru.students.service.StudentsService;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentsController {

    private final StudentsService studentsService;


    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping()
    public List<Student> findAll() {
        return studentsService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable String id){
        return studentsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentsService.createStudent(student);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student) {
        return studentsService.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable String id) {
        studentsService.deleteStudent(id);
    }
}
