package com.eRegister.eRegister.dto;

import com.eRegister.eRegister.Enam.IsInternational;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long studentId;
    private String studentNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Double gpa;
    private LocalDate enrollmentDate;
    @NotNull(message = "International status is required")
    @Enumerated(EnumType.STRING)
    private IsInternational isInternational;
}
