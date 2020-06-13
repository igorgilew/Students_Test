package ru.students.service;

import ru.students.entity.Student;

import java.util.List;

public interface StudentsService {

    List<Student> findAll();

    Student findById(Object id);

    Student createStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(Object id);

}
