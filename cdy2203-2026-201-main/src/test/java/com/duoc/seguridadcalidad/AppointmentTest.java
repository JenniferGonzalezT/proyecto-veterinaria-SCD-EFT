package com.duoc.seguridadcalidad;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        // Arrange
        Appointment appointment = new Appointment();
        LocalDate testDate = LocalDate.of(2026, 5, 10);
        LocalTime testTime = LocalTime.of(14, 30);

        // Act
        appointment.setId(1L);
        appointment.setPatientId(101L);
        appointment.setDate(testDate);
        appointment.setTime(testTime);
        appointment.setReason("Control de rutina");
        appointment.setVeterinarian("Dr. Pérez");

        // Assert
        assertEquals(1L, appointment.getId());
        assertEquals(101L, appointment.getPatientId());
        assertEquals(testDate, appointment.getDate());
        assertEquals(testTime, appointment.getTime());
        assertEquals("Control de rutina", appointment.getReason());
        assertEquals("Dr. Pérez", appointment.getVeterinarian());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        // Arrange
        LocalDate testDate = LocalDate.of(2026, 6, 15);
        LocalTime testTime = LocalTime.of(10, 0);

        // Act
        Appointment appointment = new Appointment(2L, 202L, testDate, testTime, "Vacunación", "Dra. Gómez");

        // Assert
        assertEquals(2L, appointment.getId());
        assertEquals(202L, appointment.getPatientId());
        assertEquals(testDate, appointment.getDate());
        assertEquals(testTime, appointment.getTime());
        assertEquals("Vacunación", appointment.getReason());
        assertEquals("Dra. Gómez", appointment.getVeterinarian());
    }
}