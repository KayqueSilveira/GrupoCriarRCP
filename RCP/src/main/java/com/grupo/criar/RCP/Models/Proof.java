package com.grupo.criar.RCP.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Entity
@Data
public class Proof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime date;
    private String local;
    private int amountLaps;
    private LocalTime totalDuration;
    private List<String> topThree;
    @OneToMany(mappedBy = "Proof")
    private List<Laps> lapsList;
}
