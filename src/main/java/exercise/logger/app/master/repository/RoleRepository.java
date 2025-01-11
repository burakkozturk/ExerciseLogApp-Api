package exercise.logger.app.master.repository;

import exercise.logger.app.master.entity.ERole;
import exercise.logger.app.master.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}