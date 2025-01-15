package exercise.logger.app.master.service;


import exercise.logger.app.master.entity.*;
import exercise.logger.app.master.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class WorkoutSessionService {

    private final WorkoutSessionRepository repository;

    public WorkoutSessionService(WorkoutSessionRepository repository) {
        this.repository = repository;
    }

    public List<WorkoutSession> findAll() {
        return repository.findAll();
    }

    public WorkoutSession findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("WorkoutSession not found"));
    }

    public List<WorkoutSession> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    // Opsiyonel: Tarih aralığına göre arama
    public List<WorkoutSession> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findByDateBetween(startDate, endDate);
    }

    public WorkoutSession save(WorkoutSession session) {
        return repository.save(session);
    }

    public WorkoutSession update(UUID id, WorkoutSession session) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("WorkoutSession not found");
        }
        session.setId(id);
        return repository.save(session);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
