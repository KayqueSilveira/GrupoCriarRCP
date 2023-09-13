package com.grupo.criar.RCP.Service;

import com.grupo.criar.RCP.Exceptions.NotFoundException;
import com.grupo.criar.RCP.Models.Laps;
import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Proof;
import com.grupo.criar.RCP.Repository.ProofRepository;
import com.grupo.criar.RCP.Service.impl.PilotServiceImpl;
import com.grupo.criar.RCP.Service.impl.ProofServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class ProofService implements ProofServiceImpl {

    @Autowired
    private ProofRepository proofRepository;
    @Autowired
    private PilotServiceImpl pilotService;
    @Override
    public Proof getProof(Long id) {
        var proof = proofRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found by id: " + id));
        proof.setTopThree(PegaOsTresPrimeiros(proof.getLapsList()));
        return proof;
    }

    public Proof getPilotByProof(Long idProof) {

        var proof = pegarPilotos(idProof);
        for(Proof proof1 : )
    }

    private List<String> PegaOsTresPrimeiros(List<Laps> lapsList) {

        Map<Long, Duration> pilotoTempoTotalMap = new HashMap<>();

        for (Laps laps : lapsList) {
            Long pilotoId = laps.getPilot().getId();
            Duration tempoTotal = calcularTempoTotal(laps.getTime());

            pilotoTempoTotalMap.put(pilotoId, tempoTotal);
        }

        List<Long> pilotosOrdenados = pilotoTempoTotalMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        List<Long> tresPrimeirosIds = pilotosOrdenados.subList(0, Math.min(3, pilotosOrdenados.size()));


        List<String> tresPrimeirosPilotos = new ArrayList<>();

        for (Long pilotoId : tresPrimeirosIds) {
            Pilot piloto = pilotService.findById(pilotoId);
            tresPrimeirosPilotos.add(piloto.getName());
        }

        return tresPrimeirosPilotos;
    }

    private Duration calcularTempoTotal(List<LocalTime> tempos) {

        Duration tempoTotal = Duration.ZERO;
        for (LocalTime tempo : tempos) {
            tempoTotal = tempoTotal.plus(Duration.between(LocalTime.MIDNIGHT, tempo));
        }
        return tempoTotal;
    }

    private List<Pilot> pegarPilotos(Long id) {
        var proof = proofRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found by id: " + id));
        List<Pilot> pilots = new ArrayList<>();
        for(int i=0; i<proof.getLapsList().size(); i++) {
            pilots.add(proof.getLapsList().get(i).getPilot());
        }

        return pilots;
    }

    private LocalTime calcularMelhorTempo(List<LocalTime> tempos) {

        LocalTime melhorTempos = Collections.min(tempos, Comparator.comparing(LocalTime::toSecondOfDay));

        return melhorTempos;
    }



}
