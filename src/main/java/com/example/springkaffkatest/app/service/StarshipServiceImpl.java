package com.example.springkaffkatest.app.service;

import com.example.springkaffkatest.app.dto.StarshipDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StarshipServiceImpl implements StarshipService {

    private final KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StarshipServiceImpl(KafkaTemplate<Long, StarshipDto> kafkaStarshipTemplate,
                               ObjectMapper objectMapper) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(StarshipDto dto) {
        kafkaStarshipTemplate.send("server.starship", dto);
    }

    @Override
    @KafkaListener(id = "Starship", topics = {"server.starship"}, containerFactory = "singleFactory")
    public void consume(StarshipDto dto) {
        System.out.println("=> consumed {}" + writeValueAsString(dto));
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }

}
