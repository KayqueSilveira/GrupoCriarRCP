package com.grupo.criar.RCP.Controller;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Race;
import com.grupo.criar.RCP.Service.PilotService;
import com.grupo.criar.RCP.Service.ReadTxtRace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("race")
public class RaceController {

    @Autowired
    private PilotService pilotService;

    @GetMapping("converter/race")
    public List<Pilot> getPilotToArchive(){
        return pilotService.Add_File_To_Pilot();
    }

    @GetMapping("all/pilots")
    public List<Pilot> getPilot() {
        return pilotService.getPilot();
    }

    @GetMapping("pilot/{name}")
    public Pilot getPilotByName(final String name) throws Exception {
        return pilotService.getPilotByName(name);
    }
}
