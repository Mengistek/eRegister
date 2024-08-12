package com.eRegister.eRegister.controller;

import com.eRegister.eRegister.dto.StudentDto;
import com.eRegister.eRegister.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/eregistrar/students")

public class StudentController {
    @Autowired
    private final StudentService studentService;

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to eRegistrar !" ,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<StudentDto>> listStudents () {
       List<StudentDto> students= studentService.getAllStudents();
       return new ResponseEntity<>(students,HttpStatus.OK);
    }

//    @GetMapping("/register")
//    @ResponseStatus(HttpStatus.OK)
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("student",new Student());
//        return "register-student";
//    }

    @PostMapping("/register")
    public ResponseEntity<StudentDto> registerStudent(@RequestBody StudentDto studentDto){
        StudentDto createdStudent = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(createdStudent,HttpStatus.CREATED);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<StudentDto>  editStudent(@PathVariable Long id) {
      StudentDto studentDto = studentService.getStudentById(id);

      return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto,@PathVariable Long id){
       StudentDto updateStudent= studentService.updateStudent(studentDto,id);
        return new ResponseEntity<>(updateStudent,HttpStatus.OK);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id){
       studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudents(@RequestParam("studentNumber") String studentNumber){
        List<StudentDto> students=studentService.searchStudents(studentNumber);

        return new ResponseEntity<>(students,HttpStatus.OK);
    }
}
