package com.miage.altea.trainer_api.repository;

import com.miage.altea.trainer_api.bo.Pokemon;
import com.miage.altea.trainer_api.bo.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureDataJpa
public class TrainerRepositoryTest {

    @Autowired
    private TrainerRepository repository;

    @Test
    public void trainerRepository_shouldExtendsCrudRepository() {
        assertTrue(CrudRepository.class.isAssignableFrom(TrainerRepository.class));
    }

    @Test
    public void trainerRepositoryShouldBeInstanciedBySpring() {
        assertNotNull(repository);
    }

    @Test
    public void testSave() {
        var ash = Trainer.builder().name("Ash").build();

        repository.save(ash);

        var saved = repository.findById(ash.getName()).orElse(null);

        assertEquals("Ash", saved.getName());
    }

    @Test
    public void testSaveWithPokemons() {
        var misty = Trainer.builder().name("Misty").build();
        var staryu = new Pokemon(120, 18);
        var starmie = new Pokemon(121, 21);
        misty.setTeam(List.of(staryu, starmie));

        repository.save(misty);

        var saved = repository.findById(misty.getName()).orElse(null);

        assertEquals("Misty", saved.getName());
        assertEquals(2, saved.getTeam().size());
    }
}