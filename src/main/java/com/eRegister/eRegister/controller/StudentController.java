package com.eRegister.eRegister.controller;

import org.springframework.ui.Model;

import com.eRegister.eRegister.dto.StudentDto;
import com.eRegister.eRegister.exception.StudentNotFoundException;
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
    @GetMapping("/test")
    public String testPage() {
        return "index"; // Ensure src/main/resources/templates/index.html exists
    }

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to eRegistrar!", HttpStatus.OK);
    }


    @GetMapping
    public String listStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new StudentDto());
        return "student/register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") StudentDto studentDto) {
        studentService.saveStudent(studentDto);
        return  "redirect:/api/v1/eregistrar/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            StudentDto studentDto = studentService.getStudentById(id);
            model.addAttribute("student", studentDto);
            return "student/edit";
        } catch (StudentNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") StudentDto studentDto) {
        try {
            studentService.updateStudent(studentDto, id);
            return "redirect:/api/v1/eregistrar/students";
        } catch (StudentNotFoundException e) {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return "redirect:/api/v1/eregistrar/students";
        } catch (StudentNotFoundException e) {
            return "error";
        }
    }

    @PostMapping("/search")
    public String searchStudents(@RequestParam("studentNumber") String studentNumber, Model model) {
        List<StudentDto> students = studentService.searchStudentsByNumber(studentNumber);
        model.addAttribute("students", students);
        return "student/list";
    }
}