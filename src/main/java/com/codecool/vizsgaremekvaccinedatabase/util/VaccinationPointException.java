package com.codecool.vizsgaremekvaccinedatabase.util;

public class VaccinationPointException extends RuntimeException {
    public VaccinationPointException(String name) {
        super("No vaccines stored in currently selected vaccination point with name: " + name);
    }
}
