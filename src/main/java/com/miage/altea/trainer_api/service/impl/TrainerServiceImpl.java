package com.miage.altea.trainer_api.service.impl;

import com.miage.altea.trainer_api.bo.Trainer;
import com.miage.altea.trainer_api.repository.TrainerRepository;
import com.miage.altea.trainer_api.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
        return trainerRepository.findById(name).orElseThrow(() -> new RuntimeException("Cannot find Trainer " + name));
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        if (trainerRepository.findById(trainer.getName()).isPresent())
            throw new RuntimeException("Trainer " + trainer + " exist");
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        if (trainerRepository.findById(trainer.getName()).isEmpty())
            throw new RuntimeException("Trainer " + trainer + "doesn't exist");
        return trainerRepository.save(trainer);
    }

    @Override
    public void delete(String name) {
        Trainer trainer = trainerRepository.findById(name).orElseThrow(() -> new RuntimeException("Cannot delete Trainer " + name));
        trainerRepository.delete(trainer);
    }
}
