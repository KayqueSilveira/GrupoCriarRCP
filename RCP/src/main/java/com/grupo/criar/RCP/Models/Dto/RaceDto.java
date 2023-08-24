package com.grupo.criar.RCP.Models.Dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class RaceDto {
    private long id;
    private LocalTime hour;
    private int lapNumber;
    private LocalTime lapTime;
    private double averageSpeed;
}
