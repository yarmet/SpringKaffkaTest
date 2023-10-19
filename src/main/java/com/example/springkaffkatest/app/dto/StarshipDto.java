package com.example.springkaffkatest.app.dto;

import lombok.ToString;

@ToString
public class StarshipDto extends kafkaEntity {

    public StarshipDto(long id, String name) {
        super(id, name);
    }

    public StarshipDto() {
    }
}
