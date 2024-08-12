package com.eRegister.eRegister.service;

import com.eRegister.eRegister.crudMethods.StudentIpm;
import com.eRegister.eRegister.dto.StudentDto;
import com.eRegister.eRegister.exception.StudentNotFoundException;
import com.eRegister.eRegister.model.Student;
import com.eRegister.eRegister.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentIpm {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
       Student student= mapToEntity(studentDto);
       student = studentRepository.save(student);
        return mapToDto(student);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student= studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " +id));
        return mapToDto(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Cannot delete. Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Cannot update. Student not found with ID: " + id);
        }
        Student student= mapToEntity(studentDto);
        student.setStudent_id(id);
        student = studentRepository.save(student);
        return mapToDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    public List<StudentDto> searchStudents(String studentNumber){
        return studentRepository.findByStudentNumberContaining(studentNumber)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }
    public StudentDto mapToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setStudent_id(student.getStudent_id());
        studentDto.setStudentNumber(student.getStudentNumber());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setMiddleName(student.getMiddleName());
        studentDto.setLastName(student.getLastName());
        studentDto.setGpa(student.getGpa());
        studentDto.setEnrollmentDate(student.getEnrollmentDate());
        studentDto.setIsInternational(student.getIsInternational());
        return studentDto;
    }

    public Student mapToEntity(StudentDto studentDto){
        Student student = new Student();
        student.setStudentNumber(studentDto.getStudentNumber());
        student.setFirstName(studentDto.getFirstName());
        student.setMiddleName(studentDto.getMiddleName());
        student.setLastName(studentDto.getLastName());
        student.setGpa(studentDto.getGpa());
        student.setEnrollmentDate(studentDto.getEnrollmentDate());
        student.setIsInternational(studentDto.getIsInternational());
        return student;

    }
}

