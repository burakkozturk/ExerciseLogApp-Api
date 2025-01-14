package exercise.logger.app.master.controller;

import exercise.logger.app.master.dto.SetRequestDto;
import exercise.logger.app.master.dto.SetResponseDto;
import exercise.logger.app.master.entity.Exercise;
import exercise.logger.app.master.entity.Sets;
import exercise.logger.app.master.repository.ExerciseRepository;
import exercise.logger.app.master.service.SetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sets")
public class SetsController {
    private final SetsService setsService;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public SetsController(SetsService setsService, ExerciseRepository exerciseRepository) {
        this.setsService = setsService;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping
    public List<SetResponseDto> getAllSets() {
        return setsService.getAllSets().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetResponseDto> getSetById(@PathVariable UUID id) {
        Sets set = setsService.getSetById(id);
        return ResponseEntity.ok(convertToDto(set));
    }

    @PostMapping
    public ResponseEntity<SetResponseDto> createSet(@RequestBody SetRequestDto requestDto) {
        Exercise exercise = exerciseRepository.findById(requestDto.getExerciseId())
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        Sets set = new Sets();
        set.setExercise(exercise);
        set.setWeight(requestDto.getWeight());
        set.setReps(requestDto.getReps());
        set.setDuration(requestDto.getDuration());
        set.setNotes(requestDto.getNotes());

        Sets savedSet = setsService.createSet(set);
        return ResponseEntity.ok(convertToDto(savedSet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetResponseDto> updateSet(@PathVariable UUID id, @RequestBody SetRequestDto requestDto) {
        Sets existingSet = setsService.getSetById(id);

        if (requestDto.getExerciseId() != null) {
            Exercise exercise = exerciseRepository.findById(requestDto.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));
            existingSet.setExercise(exercise);
        }

        existingSet.setWeight(requestDto.getWeight());
        existingSet.setReps(requestDto.getReps());
        existingSet.setDuration(requestDto.getDuration());
        existingSet.setNotes(requestDto.getNotes());

        Sets updatedSet = setsService.updateSet(id, existingSet);
        return ResponseEntity.ok(convertToDto(updatedSet));
    }

    private SetResponseDto convertToDto(Sets set) {
        SetResponseDto dto = new SetResponseDto();
        dto.setId(set.getId());
        dto.setExerciseId(set.getExercise().getId());
        dto.setWeight(set.getWeight());
        dto.setReps(set.getReps());
        dto.setDuration(set.getDuration());
        dto.setNotes(set.getNotes());
        dto.setCreatedAt(set.getCreatedAt());
        return dto;
    }
}