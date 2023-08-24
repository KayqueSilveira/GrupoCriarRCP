package com.grupo.criar.RCP.Service;

import com.grupo.criar.RCP.Models.Race;
import com.grupo.criar.RCP.Repository.RaceRepository;
import com.grupo.criar.RCP.Service.impl.RaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RaceService implements RaceServiceImpl {

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Race save(Race race) {
        return raceRepository.save(race);
    }

}
