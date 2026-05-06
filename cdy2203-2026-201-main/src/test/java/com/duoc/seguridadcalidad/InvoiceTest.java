package com.duoc.seguridadcalidad;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvoiceTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        // Arrange
        Invoice invoice = new Invoice();
        LocalDate testDate = LocalDate.of(2026, 4, 30);
        List<InvoiceLineItem> items = new ArrayList<>();
        items.add(new InvoiceLineItem()); // Agregamos un item vacío para probar la lista

        // Act
        invoice.setId(17L);
        invoice.setAppointmentId(100L);
        invoice.setIssueDate(testDate);
        invoice.setVatRate(new BigDecimal("0.19")); // IVA
        invoice.setSubtotal(new BigDecimal("50000"));
        invoice.setVatAmount(new BigDecimal("9500"));
        invoice.setTotal(new BigDecimal("59500"));
        invoice.setNotes("Consulta del perrito de Mingyu");
        invoice.setItems(items);

        // Assert
        assertEquals(17L, invoice.getId());
        assertEquals(100L, invoice.getAppointmentId());
        assertEquals(testDate, invoice.getIssueDate());
        assertEquals(new BigDecimal("0.19"), invoice.getVatRate());
        assertEquals(new BigDecimal("50000"), invoice.getSubtotal());
        assertEquals(new BigDecimal("9500"), invoice.getVatAmount());
        assertEquals(new BigDecimal("59500"), invoice.getTotal());
        assertEquals("Consulta del perrito de Mingyu", invoice.getNotes());
        assertEquals(items, invoice.getItems());
        assertEquals(1, invoice.getItems().size());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        // Arrange
        LocalDate testDate = LocalDate.of(2026, 5, 26);
        List<InvoiceLineItem> items = new ArrayList<>();
        
        // Act
        Invoice invoice = new Invoice(
                2L, 
                202L, 
                testDate, 
                new BigDecimal("0.19"), 
                new BigDecimal("20000"), 
                new BigDecimal("3800"), 
                new BigDecimal("23800"), 
                "Control general de Bongbongie", 
                items
        );

        // Assert
        assertEquals(2L, invoice.getId());
        assertEquals(202L, invoice.getAppointmentId());
        assertEquals(testDate, invoice.getIssueDate());
        assertEquals(new BigDecimal("0.19"), invoice.getVatRate());
        assertEquals(new BigDecimal("20000"), invoice.getSubtotal());
        assertEquals(new BigDecimal("3800"), invoice.getVatAmount());
        assertEquals(new BigDecimal("23800"), invoice.getTotal());
        assertEquals("Control general de Bongbongie", invoice.getNotes());
        assertNotNull(invoice.getItems());
        assertEquals(0, invoice.getItems().size());
    }
}