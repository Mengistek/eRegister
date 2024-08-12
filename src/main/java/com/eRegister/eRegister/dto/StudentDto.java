package com.eRegister.eRegister.dto;

import com.eRegister.eRegister.Enam.IsInternational;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto {
    private Long student_id;
    private String studentNumber;
    private String firstName;
    private String middleName ;
    private String lastName;
    private Double gpa;
    private LocalDate enrollmentDate;
    private IsInternational isInternational;
}
