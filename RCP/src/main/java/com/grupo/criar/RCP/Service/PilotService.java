package com.grupo.criar.RCP.Service;

import com.grupo.criar.RCP.Exceptions.DataProcessingException;
import com.grupo.criar.RCP.Exceptions.NotFoundException;
import com.grupo.criar.RCP.Models.Dto.PilotDto;
import com.grupo.criar.RCP.Models.Dto.RaceDto;
import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Race;
import com.grupo.criar.RCP.Repository.PilotRepository;
import com.grupo.criar.RCP.Service.impl.PilotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PilotService implements PilotServiceImpl {

    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    private ReadTxtRace readTxtRace;

    @Autowired
    private RaceService raceService;

    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }

    public List<PilotDto> getAllPilot(List<Pilot> pilots) {
        List<PilotDto> listPilotDto = new ArrayList<>();

        for (Pilot pilot : pilots) {
            PilotDto pilotDto = new PilotDto();
            pilotDto.setId(pilot.getId());
            pilotDto.setName(pilot.getName());

            List<Race> races = pilot.getRace();
            List<RaceDto> listRaceDto = new ArrayList<>();

            for (Race race : races) {
                RaceDto raceDto = new RaceDto();
                raceDto.setId(race.getId());
                raceDto.setHour(race.getHour());
                raceDto.setLapNumber(race.getLapNumber());
                raceDto.setLapTime(race.getLapTime());
                raceDto.setAverageSpeed(race.getAverageSpeed());

                listRaceDto.add(raceDto);
            }

            pilotDto.setRaces(listRaceDto);
            listPilotDto.add(pilotDto);
        }

        return listPilotDto;
    }

    public PilotDto getPilot(final String name) {

        var pilot = pilotRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Pilot not found with name: " + name));

        PilotDto pilotDto = new PilotDto();
        pilotDto.setId(pilot.getId());
        pilotDto.setName(pilot.getName());

        List<Race> races = pilot.getRace();
        List<RaceDto> listRaceDto = new ArrayList<>();

        for (Race race : races) {
            RaceDto raceDto = new RaceDto();
            raceDto.setId(race.getId());
            raceDto.setHour(race.getHour());
            raceDto.setLapNumber(race.getLapNumber());
            raceDto.setLapTime(race.getLapTime());
            raceDto.setAverageSpeed(race.getAverageSpeed());

            listRaceDto.add(raceDto);
        }

        pilotDto.setRaces(listRaceDto);

        return pilotDto;
    }

    @Override
    public Pilot findById(long id) {
        return pilotRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found for id: " +id));
    }

    @Override
    public List<Pilot> SavePilotRace() {
        try {
            var pilots = readTxtRace.LoadFileRace();

            for (Pilot pilot : pilots) {
                List<Race> races = pilot.getRace();

                pilotRepository.save(pilot);

                for (Race race : races) {
                    race.setPilot(pilot);
                    raceService.save(race);
                }
            }

            return pilots;
        } catch (Exception e) {
            throw new DataProcessingException("Error processing pilot data", e);
        }
    }
}
