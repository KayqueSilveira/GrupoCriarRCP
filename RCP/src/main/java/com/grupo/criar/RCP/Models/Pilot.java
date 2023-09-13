package com.grupo.criar.RCP.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "pilot")
    @JsonIgnore
    private List<Race> race = new ArrayList<>();
    @ManyToMany(mappedBy = "pilot")
    private List<Proof> proofs = new ArrayList<>();
}
