package org.example;

public class Athlete {
    private int number;
    private String name;
    private String countryCode;
    private int skiTimeResult;
    private String[] shootingRangeResults;
    public Athlete(int number, String name, String countryCode, int skiTimeResult, String[] shootingRangeResults) {
        this.number = number;
        this.name = name;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.shootingRangeResults = shootingRangeResults;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getSkiTimeResult() {
        return skiTimeResult;
    }

    public void setSkiTimeResult(int skiTimeResult) {
        this.skiTimeResult = skiTimeResult;
    }

    public String[] getShootingRangeResults() {
        return shootingRangeResults;
    }

    public void setShootingRangeResults(String[] shootingRangeResults) {
        this.shootingRangeResults = shootingRangeResults;
    }
}


