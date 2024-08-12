package com.eRegister.eRegister.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView  handleStudentNotFoundException(StudentNotFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("error/student-not-found");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerGeneralException(Exception ex){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message","An unexpected error occurred: "+ ex.getMessage());
        mav.setViewName("error/general-error") ;
        return mav;
    }
}
