package com.eRegister.eRegister.exception;

import com.eRegister.eRegister.exception.StudentNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public String handleStudentNotFoundException(StudentNotFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error"; // Ensure you have an error.html template in your Thymeleaf templates
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("message", "An unexpected error occurred: " + e.getMessage());
        return "error"; // Ensure you have an error.html template in your Thymeleaf templates
    }
}
