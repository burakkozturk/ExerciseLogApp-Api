package exercise.logger.app.master.repository;

import exercise.logger.app.master.entity.Sets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SetsRepository extends JpaRepository<Sets, UUID> {}
