package ru.students.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.students.entity.Student;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
    Student findByPassport(String passport);
}
