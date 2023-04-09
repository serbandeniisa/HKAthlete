package org.example;

import junit.framework.TestCase;
import java.util.List;



public class BiathlonStandingCalculatorTest extends TestCase {

    private static final String CSV ="11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n" +
            "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n" +
            "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

    @Test
    public void testReadAthletesFromCsv() {
        List<Athlete> athletes = BiathlonStandingCalculator.readAthletesFromCsv(CSV);
        assertEquals(3, athletes.size());
        assertEquals("Umar Jorgson", athletes.get(0).getName());
        assertEquals("SK", athletes.get(0).getCountryCode());
        assertEquals("xxxxx", athletes.get(2).getShootingRangeResults()[2]);
    }
    @Test
    public void testCalculateStandings() {
        List<Athlete> athletes = BiathlonStandingCalculator.readAthletesFromCsv(CSV);
        List<Athlete> standings = BiathlonStandingCalculator.calculateStandings(athletes);
        assertEquals("Piotr Smitzer", standings.get(0).getName());
        assertEquals("Jimmy Smiles", standings.get(1).getName());
        assertEquals("Umar Jorgson", standings.get(2).getName());
        assertEquals(30 * 60 + 10, standings.get(0).getSkiTimeResult());
        assertEquals(29 * 60 + 15 + 60, standings.get(1).getSkiTimeResult());
        assertEquals(30 * 60 + 27 + 30, standings.get(2).getSkiTimeResult());
    }

}