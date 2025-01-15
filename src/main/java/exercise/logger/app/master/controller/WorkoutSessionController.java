
package exercise.logger.app.master.controller;

import exercise.logger.app.master.entity.*;
import exercise.logger.app.master.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionController {

    private final WorkoutSessionService service;

    public WorkoutSessionController(WorkoutSessionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutSession>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutSession> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<WorkoutSession>> getByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(service.findByDate(date));
    }


    @GetMapping("/by-date-range")
    public ResponseEntity<List<WorkoutSession>> getByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(service.findByDateRange(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<WorkoutSession> create(@RequestBody WorkoutSession session) {
        return ResponseEntity.ok(service.save(session));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutSession> update(@PathVariable UUID id, @RequestBody WorkoutSession session) {
        return ResponseEntity.ok(service.update(id, session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
