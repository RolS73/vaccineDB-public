package com.codecool.vizsgaremekvaccinedatabase.util;

public class PatientUnsafeVaccinationException extends RuntimeException {
    public PatientUnsafeVaccinationException(String name) {
        super("This vaccine cannot be safely administered to patient: " + name);
    }
}
