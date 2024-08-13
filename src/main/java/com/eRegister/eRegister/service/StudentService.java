package com.eRegister.eRegister.service;

import com.eRegister.eRegister.crudMethods.StudentIpm;
import com.eRegister.eRegister.dto.StudentDto;
import com.eRegister.eRegister.exception.StudentNotFoundException;
import com.eRegister.eRegister.model.Student;
import com.eRegister.eRegister.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentIpm {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;


    // Conversion methods using ModelMapper
    private StudentDto convertToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    private Student convertToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student savestudent = studentRepository.save(student);
        return modelMapper.map(savestudent, StudentDto.class);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
        return modelMapper.map(student, StudentDto.class);
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
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        modelMapper.map(studentDto, existingStudent);
        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> searchStudentsByNumber(String studentNumber) {
        List<Student> students = studentRepository.findByStudentNumberContaining(studentNumber);
        return students.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());


    }
}


    /*
        // manual converted //
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

     */



