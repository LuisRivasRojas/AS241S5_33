package com.luisrivas.student.rest;   

import com.luisrivas.student.model.Student;
import com.luisrivas.student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/student")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class StudentRest {   
    private final StudentService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("dni", "87654321");
        response.put("firstName", "Luis");
        response.put("lastName", "Rivas");
        response.put("promotion", 2026);
        response.put("date", LocalDateTime.now());
        log.info("[INVOCAR] GET /v1/api/student - {}", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        log.info("[INVOCAR] GET /v1/api/student/{} - {}", id, LocalDateTime.now());
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student student) {
        log.info("[REGISTRAR] POST /v1/api/student - {}", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        log.info("[ACTUALIZAR] PUT /v1/api/student/{} - {}", id, LocalDateTime.now());
        return ResponseEntity.ok(service.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("[ELIMINAR] DELETE /v1/api/student/{} - {}", id, LocalDateTime.now());
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
