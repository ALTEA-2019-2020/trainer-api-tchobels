package com.miage.altea.trainer_api.controller;

import com.miage.altea.trainer_api.bo.Pokemon;
import com.miage.altea.trainer_api.bo.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Test
    void trainerController_shouldBeInstanciated() {
        assertNotNull(controller);
    }

    @Test
    void getTrainers_shouldThrowAnUnauthorized() {
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonTypeId());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);
        assertNotNull(trainers);
        assertEquals(2, trainers.length);

        assertEquals("Ash", trainers[1].getName());
        assertEquals("Misty", trainers[0].getName());
    }

    @Test
    void addAsh_shouldReturnAsh() {
        Trainer trainer = Trainer.builder().name("Ash").password("pwd").team(List.of(new Pokemon(15, 6))).build();
        var ash = restTemplate
                .withBasicAuth(username, password)
                .postForObject("http://localhost:" + port + "/trainers/", trainer, Trainer.class);

        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(15, ash.getTeam().get(0).getPokemonTypeId());
    }

    @Test
    void updateAsh_shouldReturnUpdatedAsh() {
        var trainer = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(trainer);
        trainer.setTeam(List.of(new Pokemon(15, 6)));
        restTemplate
                .withBasicAuth(username, password)
                .put("http://localhost:" + port + "/trainers/Ash", trainer);

        var ash = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(15, ash.getTeam().get(0).getPokemonTypeId());
    }

    @Test
    void removeAsh_shouldReturnNothing() {
        this.restTemplate
                .withBasicAuth(username, password)
                .delete("http://localhost:" + port + "/trainers/Ash", Trainer[].class);

        var NoAsh = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNull(NoAsh.getName());
    }
}