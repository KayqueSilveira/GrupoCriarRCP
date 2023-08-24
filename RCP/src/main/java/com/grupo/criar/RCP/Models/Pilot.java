package com.grupo.criar.RCP.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pilot {

    @Id
    private long id;
    private String name;
    @OneToMany(mappedBy = "pilot")
    @JsonIgnore
    private List<Race> race = new ArrayList<>();
}
