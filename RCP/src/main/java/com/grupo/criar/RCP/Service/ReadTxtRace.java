package com.grupo.criar.RCP.Service;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Race;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadTxtRace {

    public List<Pilot> LoadFileRace() {
        List<Pilot> listPilot = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\kayqu\\OneDrive\\Área de Trabalho\\desafio tecnico\\GrupoCriarRCP\\RCP\\src\\main\\java\\com\\grupo\\criar\\RCP\\Utils\\race.txt"), StandardCharsets.UTF_8))) {
            String line;

            while ((line = br.readLine()) != null) {
                Pilot pilot = new Pilot();
                Race race = new Race();

                String[] parts = line.split(" ");
                if (parts.length <= 9) {
                    String hourStr = parts[0];
                    String idStr = parts[1];
                    String name = parts[3];
                    String lapNumberStr = parts[4];
                    String lapTimeStr = parts[5];
                    String averageSpeedStr = parts[6];

                    LocalTime hour = LocalTime.parse(hourStr);
                    long id = Long.parseLong(idStr);
                    int lapNumber = Integer.parseInt(lapNumberStr);
                    LocalTime lapTime = converter(lapTimeStr);
                    double averageSpeed = Double.parseDouble(averageSpeedStr);

                    pilot.setId(id);
                    pilot.setName(name);

                    race.setHour(hour);
                    race.setLapNumber(lapNumber);
                    race.setLapTime(lapTime);
                    race.setAverageSpeed(averageSpeed);

                    race.setPilot(pilot);
                    pilot.getRace().add(race);

                    listPilot.add(pilot);
                }
            }
            return listPilot;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalTime converter(String data) {

        String[] parts = data.split(":");
        int minutes = Integer.parseInt(parts[0]);
        String[] secondsParts = parts[1].split("\\.");
        int seconds = Integer.parseInt(secondsParts[0]);
        int milli = Integer.parseInt(secondsParts[1]);
        long nanoseconds = milli * 1_000_000;

        LocalTime localTime = LocalTime.MIDNIGHT
                .plus(minutes, ChronoUnit.MINUTES)
                .plus(seconds, ChronoUnit.SECONDS)
                .plusNanos(nanoseconds);

        return localTime;
    }

}