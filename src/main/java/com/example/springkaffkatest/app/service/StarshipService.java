package com.example.springkaffkatest.app.service;

import com.example.springkaffkatest.app.dto.StarshipDto;

public interface StarshipService {

    void send(StarshipDto dto);

    void consume(StarshipDto dto);

}
