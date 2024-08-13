package com.eRegister.eRegister.repository;

import com.eRegister.eRegister.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student ,Long> {
    @Query("SELECT s From Student s WHERE s.studentNumber LIKE %:studentNumber%")
    List<Student> findByStudentNumberContaining(@Param("studentNumber") String StudentNumber);
}
