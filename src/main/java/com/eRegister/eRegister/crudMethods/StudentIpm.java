package com.eRegister.eRegister.crudMethods;

import com.eRegister.eRegister.dto.StudentDto;
import com.eRegister.eRegister.model.Student;

import java.util.List;

public interface StudentIpm {
    List<StudentDto> getAllStudents( );

    StudentDto saveStudent(StudentDto studentDto);

    void  deleteStudent(Long id);
    StudentDto getStudentById(Long id);

    StudentDto updateStudent(StudentDto studentDto, Long id);
    List<StudentDto> searchStudentsByNumber(String StudentNumber);
}
