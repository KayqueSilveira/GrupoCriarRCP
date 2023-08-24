package com.grupo.criar.RCP.Controller;

import com.grupo.criar.RCP.Models.Dto.PilotDto;
import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Service.impl.PilotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pilot")
public class PilotController {

    @Autowired
    private PilotServiceImpl pilotServiceImpl;

    @GetMapping("load/archive/race")
    public List<Pilot> getPilotToArchive(){
        return pilotServiceImpl.SavePilotRace();
    }

    @GetMapping("{name}")
    public PilotDto getPilotByName(final @PathVariable String name) throws Exception {
        return pilotServiceImpl.getPilot(name);
    }
    @GetMapping("all")
    public List<PilotDto> getAllPilots() {
        var listPilot = pilotServiceImpl.findAll();
        return pilotServiceImpl.getAllPilot(listPilot);
    }

}
