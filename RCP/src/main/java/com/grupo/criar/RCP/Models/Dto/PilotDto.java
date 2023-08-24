package com.grupo.criar.RCP.Models.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PilotDto {
    private long id;
    private String name;
    private List<RaceDto> races;
}
