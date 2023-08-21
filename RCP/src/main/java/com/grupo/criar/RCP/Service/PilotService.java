package com.grupo.criar.RCP.Service;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Race;
import com.grupo.criar.RCP.Repository.PilotRepository;
import com.grupo.criar.RCP.Service.impl.PilotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService implements PilotServiceImpl {

    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    private ReadTxtRace readTxtRace;

    @Autowired
    private RaceService raceService;

    @Override
    public List<Pilot> getPilot() {
        return pilotRepository.findAll();
    }

    @Override
    public Pilot getPilotByName(String name) throws Exception {
        return pilotRepository.findByName(name).orElseThrow(() -> new Exception("Piloto não encontrado"));
    }

    @Override
    public List<Pilot> Add_File_To_Pilot() {
        var pilots = readTxtRace.Add_File_To_Pilot();

        for (Pilot pilot : pilots) {
            Race race = new Race();
            race = pilot.getRace();
            raceService.save(race);
            pilotRepository.saveAndFlush(pilot);
        }

        return pilots;
    }

}
