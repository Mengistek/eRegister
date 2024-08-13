package com.eRegister.eRegister.dto;

import com.eRegister.eRegister.Enam.IsInternational;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
