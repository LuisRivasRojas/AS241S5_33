package com.luisrivas.student.service;

import com.luisrivas.student.model.Student;
import com.luisrivas.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository repository;

    public List<Student> findAll() {
        log.info("[INVOCAR] Listando todos los estudiantes - {}", LocalDateTime.now());
        return repository.findAll();
    }

    public Optional<Student> findById(Long id) {
        log.info("[INVOCAR] Buscando estudiante ID: {} - {}", id, LocalDateTime.now());
        return repository.findById(id);
    }

    public Student save(Student student) {
        student.setDate(LocalDateTime.now());
        log.info("[REGISTRAR] Nuevo estudiante: {} {} | DNI: {} | Promoción: {} | Fecha: {}",
                student.getFirstName(),
                student.getLastName(),
                student.getDni(),
                student.getPromotion(),
                student.getDate());
        return repository.save(student);
    }

    public Student update(Long id, Student details) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        student.setDni(details.getDni());
        student.setFirstName(details.getFirstName());
        student.setLastName(details.getLastName());
        student.setPromotion(details.getPromotion());
        student.setDate(LocalDateTime.now());

        log.info("[ACTUALIZAR] Estudiante ID: {} | {} {} | DNI: {} | Fecha: {}",
                id,
                student.getFirstName(),
                student.getLastName(),
                student.getDni(),
                student.getDate());

        return repository.save(student);
    }

    public void delete(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));

        log.info("[ELIMINAR] Estudiante ID: {} | {} {} | DNI: {} | Fecha: {}",
                id,
                student.getFirstName(),
                student.getLastName(),
                student.getDni(),
                LocalDateTime.now());

        repository.deleteById(id);
    }
}
