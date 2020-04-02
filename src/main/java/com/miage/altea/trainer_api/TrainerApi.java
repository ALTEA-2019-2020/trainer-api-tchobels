package com.miage.altea.trainer_api;

import com.miage.altea.trainer_api.bo.Pokemon;
import com.miage.altea.trainer_api.bo.Trainer;
import com.miage.altea.trainer_api.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class TrainerApi {

    public static void main(String... args) {
        SpringApplication.run(TrainerApi.class, args);
    }

    @Bean
    @Autowired
    public CommandLineRunner demo(TrainerRepository repository) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return (args) -> {
            var pikachu = new Pokemon(25, 18);
            var staryu = new Pokemon(120, 18);
            var starmie = new Pokemon(121, 21);

            var ash = Trainer.builder().name("Ash").team(List.of(pikachu)).password(bCryptPasswordEncoder.encode("ash_password")).build();
            var misty = Trainer.builder().name("Misty").team(List.of(staryu, starmie)).password(bCryptPasswordEncoder.encode("misty_password")).build();

            // save a couple of trainers
            repository.save(ash);
            repository.save(misty);
        };
    }
}