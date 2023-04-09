package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BiathlonStandingCalculator {
    private static final int PENALTY_SECONDS_PER_MISS = 10;


    public static List<Athlete> calculateStandings(List<Athlete> athletes) {
        for (Athlete athlete : athletes) {
            int penaltyTime = 0;
            for (String shootingRangeResult : athlete.getShootingRangeResults()) {
                for (int i = 0; i < shootingRangeResult.length(); i++) {
                    if (shootingRangeResult.charAt(i) == 'o') {
                        penaltyTime += PENALTY_SECONDS_PER_MISS;
                    }
                }
            }
            athlete.setSkiTimeResult(athlete.getSkiTimeResult() + penaltyTime);
        }

        athletes.sort(Comparator.comparingInt(Athlete::getSkiTimeResult));

        return athletes;
    }

    public static List<Athlete> calculateStandingsWithPenalties(List<Athlete> athletes) {
            athletes.sort(Comparator.comparingInt(Athlete::getSkiTimeResult)
                    .thenComparing((a1, a2) -> {
                        int p1 = countPenalties(a1.getShootingRangeResults());
                        int p2 = countPenalties(a2.getShootingRangeResults());
                        return Integer.compare(p1, p2);
                    })
            );

            List<Athlete> standings = new ArrayList<>();
            for (int i = 0; i < athletes.size(); i++) {
                Athlete athlete = athletes.get(i);
                int place = i + 1;
                int finalTime = athlete.getSkiTimeResult() + countPenalties(athlete.getShootingRangeResults()) * 10;
                switch (place) {
                    case 1:
                        athlete.setPlace(Place.FIRST);
                        break;
                    case 2:
                        athlete.setPlace(Place.SECOND);
                        break;
                    case 3:
                        athlete.setPlace(Place.THIRD);
                        break;
                    default:
                        athlete.setPlace(Place.OTHER);
                        break;
                }
                athlete.setFinalTime(finalTime);
                standings.add(athlete);
            }

            return standings;
        }

    public static int countPenalties(String[] shootingRangeResults) {
        int penaltyCount = 0;
        for (String result : shootingRangeResults) {
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == 'o') {
                    penaltyCount++;
                }
            }
        }
        return penaltyCount;
    }

    public static List<Athlete> readAthletesFromCsv(String csv) {
        List<Athlete> athletes = new ArrayList<>();
        String[] lines = csv.split("\\r?\\n");
        for (String line : lines) {
            String[] fields = line.split(",");
            int number = Integer.parseInt(fields[0]);
            String name = fields[1];
            String countryCode = fields[2];
            String[] shootingRangeResults = new String[] {fields[4], fields[5], fields[6]};
            String skiTimeResultString = fields[3];
            String[] skiTimeResultParts = skiTimeResultString.split(":");
            int skiTimeResult = Integer.parseInt(skiTimeResultParts[0]) * 60 + Integer.parseInt(skiTimeResultParts[1]);
            Athlete athlete = new Athlete(number, name, countryCode, skiTimeResult, shootingRangeResults);
            athletes.add(athlete);
        }
        return athletes;
    }





