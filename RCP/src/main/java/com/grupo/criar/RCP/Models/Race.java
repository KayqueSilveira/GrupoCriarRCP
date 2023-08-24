package com.grupo.criar.RCP.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Data
@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;
    @Column(name = "hour")
    private LocalTime hour;
    private int lapNumber;
    private LocalTime lapTime;
    private double averageSpeed;

}
