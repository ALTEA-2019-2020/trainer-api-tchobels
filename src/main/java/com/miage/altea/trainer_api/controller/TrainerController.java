package com.miage.altea.trainer_api.controller;

import com.miage.altea.trainer_api.bo.Trainer;
import com.miage.altea.trainer_api.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping(value = "/")
    public Iterable<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping(value = "/{name}")
    public Trainer getTrainer(@PathVariable String name) {
        return trainerService.getTrainer(name);
    }

    @PostMapping(value = "/")
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.createTrainer(trainer);
    }

    @PutMapping(value = "/")
    public Trainer updateTrainer(@RequestBody Trainer trainer) {
        return trainerService.update(trainer);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity deleteTrainer(@PathVariable String name) {
        trainerService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}