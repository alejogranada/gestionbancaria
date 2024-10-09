package com.example.gestionbancaria.controller;

import com.example.gestionbancaria.dto.ReporteDTO;
import com.example.gestionbancaria.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        List<ReporteDTO> reportes = reporteService.generarReporte(clienteId, fechaInicio, fechaFin);
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }
}