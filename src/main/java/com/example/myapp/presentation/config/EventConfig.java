package com.example.myapp.presentation.config;


import com.example.myapp.persistence.model.Evenement;

import com.example.myapp.persistence.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EventConfig {
    @Bean
    CommandLineRunner eventconfig(
            EventRepository eventRepository){
        return args -> {

            Evenement event1 = new Evenement("Presentielle","2022-05-15","2022-05-18");
            Evenement event2 = new Evenement("A distance","2022-05-20","2022-05-22");
            Evenement event3 = new Evenement("Congé","2022-05-20","2022-05-22");


            eventRepository.saveAll(
                    List.of(event1,event2,event3)
            );
        };
    }
}
