package exercise.logger.app.master.repository;

import exercise.logger.app.master.entity.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, UUID> {
    List<WorkoutSession> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<WorkoutSession> findByDate(LocalDate date);
}
