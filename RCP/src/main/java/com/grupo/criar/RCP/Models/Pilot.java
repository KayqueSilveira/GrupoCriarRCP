package com.grupo.criar.RCP.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Pilot {

    @Id
    private long id;
    private String name;
    @ManyToOne
    private Race race;
}
