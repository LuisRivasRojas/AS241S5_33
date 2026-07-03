package com.luisrivas.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El DNI es obligatorio")
    @Column(nullable = false, length = 20)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull(message = "La promoción es obligatoria")
    @Column(nullable = false)
    private Integer promotion;

    @Column(nullable = false)
    private LocalDateTime date;
}
