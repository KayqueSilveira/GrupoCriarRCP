package com.grupo.criar.RCP.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Pilot {

    private long id;
    private String name;
    private Race race;
}
