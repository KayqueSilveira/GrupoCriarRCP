package com.grupo.criar.RCP.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class Race {

    private long id;
    private LocalTime hour;
    private int lapNumber;
    private LocalTime lapTime;
    private double averageSpeed;

}
