package exercise.logger.app.master.controller;


import exercise.logger.app.master.dto.ExerciseRequestDto;
import exercise.logger.app.master.entity.Exercise;
import exercise.logger.app.master.entity.WorkoutSession;
import exercise.logger.app.master.repository.ExerciseRepository;
import exercise.logger.app.master.repository.WorkoutSessionRepository;
import exercise.logger.app.master.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final WorkoutSessionRepository workoutSessionRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, WorkoutSessionRepository workoutSessionRepository, ExerciseRepository exerciseRepository) {
        this.exerciseService = exerciseService;
        this.workoutSessionRepository = workoutSessionRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable UUID id) {
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    @PostMapping("/exercises")
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseRequestDto exerciseDto) {
        WorkoutSession workoutSession = workoutSessionRepository
                .findById(exerciseDto.getWorkoutSessionId())
                .orElseThrow(() -> new IllegalArgumentException("WorkoutSession not found"));

        Exercise exercise = new Exercise();
        exercise.setName(exerciseDto.getName());
        exercise.setNotes(exerciseDto.getNotes());
        exercise.setWorkoutSession(workoutSession);

        Exercise savedExercise = exerciseRepository.save(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExercise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable UUID id, @RequestBody ExerciseRequestDto exerciseDto) {
        Exercise existingExercise = exerciseService.getExerciseById(id);

        if (exerciseDto.getWorkoutSessionId() != null) {
            WorkoutSession workoutSession = workoutSessionRepository
                    .findById(exerciseDto.getWorkoutSessionId())
                    .orElseThrow(() -> new IllegalArgumentException("WorkoutSession not found"));
            existingExercise.setWorkoutSession(workoutSession);
        }

        existingExercise.setName(exerciseDto.getName());
        existingExercise.setNotes(exerciseDto.getNotes());

        Exercise updatedExercise = exerciseRepository.save(existingExercise);
        return ResponseEntity.ok(updatedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable UUID id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}