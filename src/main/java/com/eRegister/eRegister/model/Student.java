package com.eRegister.eRegister.model;

import com.eRegister.eRegister.Enam.IsInternational;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")


public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;

    @Column(nullable = false ,unique = true)
    private String studentNumber;

    @Column(nullable = false)
    private String firstName;

    private String middleName ;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Double gpa;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate enrollmentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IsInternational isInternational;


}
