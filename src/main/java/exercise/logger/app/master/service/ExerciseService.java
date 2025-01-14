package exercise.logger.app.master.service;


import exercise.logger.app.master.entity.Exercise;
import exercise.logger.app.master.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(UUID id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
    }

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(UUID id, Exercise updatedExercise) {
        Exercise existingExercise = getExerciseById(id);
        existingExercise.setName(updatedExercise.getName());
        existingExercise.setNotes(updatedExercise.getNotes());
        return exerciseRepository.save(existingExercise);
    }

    public void deleteExercise(UUID id) {
        exerciseRepository.deleteById(id);
    }
}