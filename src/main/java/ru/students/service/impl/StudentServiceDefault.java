package ru.students.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.students.entity.Student;
import ru.students.exceptions.EntityConflictException;
import ru.students.exceptions.EntityIllegalArgumentException;
import ru.students.exceptions.EntityNotFoundException;
import ru.students.jpa.StudentsRepository;
import ru.students.service.StudentsService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceDefault implements StudentsService {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentServiceDefault(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Student findById(Object id) {

        Optional<Student> student;

        if(id == null){
            throw new EntityIllegalArgumentException("id не может быть null");
        }
        Long parsedId;
        try{
            parsedId = Long.valueOf(id.toString());
        } catch (NumberFormatException ex){
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор " +
                    "к нужному типу, текст ошибки: %s", ex.getMessage()));
        }

        student = studentsRepository.findById(parsedId);

        if(!student.isPresent()){
            throw new EntityNotFoundException(Student.NAME, id);
        }

        return student.get();
    }

    @Override
    public Student createStudent(Student student) {

        if (student == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if(student.getId() == null){
            throw new EntityIllegalArgumentException("Id создаваемого объекта не может быть null");
        }

        if(student.getName() == null || student.getName().isEmpty()){
            throw new EntityIllegalArgumentException("Name создаваемого объекта не может быть null или пустым");
        }

        if(student.getPassport() == null || student.getPassport().isEmpty()){
            throw new EntityIllegalArgumentException("Passport создаваемого объекта не может быть null или пустым");
        }

        Student existedPassportStudent = studentsRepository.findByPassport(student.getPassport());

        if (existedPassportStudent != null) {
            throw new EntityConflictException("В системе уже есть студент с таким номером паспорта");
        }

        return studentsRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        if (student == null) {
            throw new EntityIllegalArgumentException("Изменяемый объект не может быть null");
        }
        if(student.getId() == null){
            throw new EntityIllegalArgumentException("Id изменяемого объекта не может быть null");
        }

        if(student.getName() == null || student.getName().isEmpty()){
            throw new EntityIllegalArgumentException("Name изменяемого объекта не может быть null или пустым");
        }

        if(student.getPassport() == null || student.getPassport().isEmpty()){
            throw new EntityIllegalArgumentException("Passport изменяемого объекта не может быть null или пустым");
        }

        Student existedStudent = studentsRepository.findByPassport(student.getPassport());

        if (existedStudent != null && existedStudent.getId().intValue() != student.getId().intValue()) {

            throw new EntityConflictException("В системе уже есть студент с таким номером паспорта");
        }

        Optional<Student> findStudent = studentsRepository.findById(student.getId());

        if(!findStudent.isPresent()){
            throw new EntityNotFoundException(Student.NAME, student.getId());
        }

        return studentsRepository.save(student);
    }

    @Override
    public void deleteStudent(Object id) {
        Student student = findById(id);
        studentsRepository.delete(student);
    }
}
