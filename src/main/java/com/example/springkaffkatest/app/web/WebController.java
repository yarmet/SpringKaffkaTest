package com.example.springkaffkatest.app.web;

import com.example.springkaffkatest.app.dto.StarshipDto;
import com.example.springkaffkatest.app.service.StarshipService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebController {

    private final StarshipService starshipService;


    @PostConstruct
    public void init() {
        StarshipDto starshipDto = new StarshipDto(1L, "sdfsdfsdf");
        starshipService.send(starshipDto);
    }
}
