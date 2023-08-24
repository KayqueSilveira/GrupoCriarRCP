package com.grupo.criar.RCP.Service;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Dto.Position;
import com.grupo.criar.RCP.Models.Race;
import com.grupo.criar.RCP.Service.impl.PositionServiceImpl;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class PositionService implements PositionServiceImpl {
    public List<Position> PilotPosition(final List<Pilot> listPilot) {
        List<Position> listPosition = BetweenTimesWinner(listPilot);
        LocalTime minLapTime = listPosition.stream().map(Position::getLapTime).min(LocalTime::compareTo).orElse(null);

        for (Position position : listPosition) {
            if (position.getLapTime().equals(minLapTime)) {
                position.setLapTimeAllRace(true);
            }
        }

        Collections.sort(listPosition, Comparator.comparingInt(Position::getPosition));

        return listPosition;
    }

    private List<Position> PilotPositionWinner(final List<Pilot> listPilot) {
        var listPosition = AverageSpeed(listPilot);
        List<LocalTime> listTime = new ArrayList<>();

        for(Position position : listPosition) {
            listTime.add(position.getLapTime());
        }
        Collections.sort(listTime);

        for(int j=0; j<listTime.size(); j++) {
            for(int i=0; i<listPosition.size(); i++){
                if(listTime.get(j) == listPosition.get(i).getLapTime()){
                    listPosition.get(i).setPosition(j + 1);
                }
            }
        }
        return listPosition;
    }

    private List<Position> TotalHourTest(final List<Pilot> listPilot) {
        List<LocalTime> localTimes = new ArrayList<>();
        var listPosition = PilotPositionWinner(listPilot);
        int count=0;
        int position=0;

        for (Pilot pilot : listPilot) {
            count=0;
            while (pilot.getRace().size() > count) {
                LocalTime localTime = pilot.getRace().get(count).getLapTime();
                localTimes.add(localTime);
                Duration totalDuration = Duration.ZERO;
                count++;
                if(localTimes.size() == pilot.getRace().size()){
                    for (LocalTime local : localTimes) {
                        totalDuration = totalDuration.plus(Duration.between(LocalTime.MIN, local));
                    }
                    int minutes = totalDuration.toMinutesPart();
                    int seconds = totalDuration.toSecondsPart();
                    int millis = totalDuration.toMillisPart();
                    LocalTime someLocalTime = LocalTime.of(totalDuration.toHoursPart(), minutes, seconds, millis * 1_000_000);
                    listPosition.get(position).setTotalTestTime(someLocalTime);
                    listPosition.get(position).setId(pilot.getId());
                    listPosition.get(position).setAmountOfTurns(pilot.getRace().size());
                    position++;
                    localTimes.clear();
                }
            }
        }
        return listPosition;
    }

    private List<Position> BetweenTimesWinner(final List<Pilot> listPilot) {
        var listPosition = TotalHourTest(listPilot);
        LocalTime win = null;
        for(Position position : listPosition) {
            if(position.getPosition() == 1) {
                win = position.getLapTime();
            }
        }

        for(Position position : listPosition) {
            var between = BetweenTimesWin( win, position.getLapTime());
            position.setArrivedAfterWinner(between);

        }
        return listPosition;
    }

    private LocalTime BetweenTimesWin(LocalTime win, LocalTime loser) {
        Duration duration = Duration.between(win, loser);
        int hours = duration.toHoursPart();
        int minutes = duration.toMinutesPart();
        int seconds = duration.toSecondsPart();
        int millis = duration.toMillisPart();

        LocalTime result = LocalTime.of(hours, minutes, seconds, millis);

        return result;
    }

    private List<Position> AverageSpeed(final List<Pilot> listPilot) {
        var listPosition = PilotPositionLapNumber(listPilot);

        int i=0;
        for(Pilot pilot: listPilot) {
            var speed = AverageSpeedForPilot(pilot, listPilot.get(i).getRace().size());
            listPosition.get(i).setAverageSpeedRace(speed);
            i++;
        }
        return listPosition;
    }

    private double AverageSpeedForPilot(final Pilot pilot, int divide) {
        double someSpeed=0.0;

        for (Race race : pilot.getRace()){
            someSpeed += race.getAverageSpeed();
        }
        return someSpeed/divide;
    }

    private List<Position> PilotPositionLapNumber(final List<Pilot> listPilot ) {
        List<LocalTime> localTimes = new ArrayList<>();
        List<Position> positionList = new ArrayList<>();

        int count=0;
        for(Pilot pilot: listPilot) {
            while(pilot.getRace().size() > count) {
                LocalTime localTime = pilot.getRace().get(count).getLapTime();
                localTimes.add(localTime);

                if(localTimes.size() == pilot.getRace().size()) {
                    for(int i =0; i< localTimes.size(); i++){
                        if(pilot.getRace().get(i).getLapTime().equals(Collections.min(localTimes))){
                            Position positions = new Position();
                            positions.setName(pilot.getName());
                            positions.setLapNumber(pilot.getRace().get(i).getLapNumber());
                            positions.setLapTime(pilot.getRace().get(i).getLapTime());
                            positionList.add(positions);
                        }
                    }
                }
                count++;
            }
            localTimes.clear();
            count=0;
        }
        return positionList;
    }
}
