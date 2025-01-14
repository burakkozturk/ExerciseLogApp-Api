package exercise.logger.app.master.service;

import exercise.logger.app.master.entity.Sets;
import exercise.logger.app.master.repository.SetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SetsService {

    private final SetsRepository setsRepository;

    @Autowired
    public SetsService(SetsRepository setsRepository) {
        this.setsRepository = setsRepository;
    }

    public List<Sets> getAllSets() {
        return setsRepository.findAll();
    }

    public Sets getSetById(UUID id) {
        return setsRepository.findById(id).orElseThrow(() -> new RuntimeException("Set not found with id: " + id));
    }

    public Sets createSet(Sets set) {
        return setsRepository.save(set);
    }

    public Sets updateSet(UUID id, Sets updatedSet) {
        Sets existingSet = getSetById(id);
        existingSet.setWeight(updatedSet.getWeight());
        existingSet.setReps(updatedSet.getReps());
        existingSet.setDuration(updatedSet.getDuration());
        existingSet.setNotes(updatedSet.getNotes());
        return setsRepository.save(existingSet);
    }

    public void deleteSet(UUID id) {
        setsRepository.deleteById(id);
    }
}
