package com.grupo.criar.RCP.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Laps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;
    @ManyToOne
    @JoinColumn(name = "proof_id")
    private Proof proof;
    @ElementCollection
    private List<LocalTime> time;
}
