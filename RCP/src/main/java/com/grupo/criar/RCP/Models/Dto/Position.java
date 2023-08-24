package com.grupo.criar.RCP.Models.Dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Position {
    long id;
    private String name;
    private int position;
    int amountOfTurns;
    private int lapNumber;
    private LocalTime lapTime;
    private boolean isLapTimeAllRace = false;
    private double averageSpeedRace;
    private LocalTime arrivedAfterWinner;
    private LocalTime totalTestTime;
}
