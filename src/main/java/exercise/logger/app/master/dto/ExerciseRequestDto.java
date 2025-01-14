package exercise.logger.app.master.dto;

import java.util.UUID;
public class ExerciseRequestDto {

    private String name;
    private String notes;
    private UUID workoutSessionId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UUID getWorkoutSessionId() {
        return workoutSessionId;
    }

    public void setWorkoutSessionId(UUID workoutSessionId) {
        this.workoutSessionId = workoutSessionId;
    }
}
