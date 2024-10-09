package com.example.gestionbancaria.services;

import com.example.gestionbancaria.dto.ReporteDTO;
import com.example.gestionbancaria.entities.Cliente;
import com.example.gestionbancaria.entities.Cuenta;
import com.example.gestionbancaria.entities.Movimiento;
import com.example.gestionbancaria.exceptions.ResourceNotFoundException;
import com.example.gestionbancaria.repositories.ClienteRepository;
import com.example.gestionbancaria.repositories.CuentaRepository;
import com.example.gestionbancaria.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<ReporteDTO> generarReporte(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + clienteId));

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<ReporteDTO> reportes = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
            for (Movimiento movimiento : movimientos) {
                ReporteDTO reporte = new ReporteDTO();
                reporte.setFecha(movimiento.getFecha());
                reporte.setCliente(cliente.getNombre());
                reporte.setNumeroCuenta(cuenta.getNumeroCuenta());
                reporte.setTipo(cuenta.getTipoCuenta());
                reporte.setSaldoInicial(cuenta.getSaldoInicial());
                reporte.setEstado(cuenta.isEstado());
                reporte.setMovimiento(movimiento.getValor());
                reporte.setSaldoDisponible(movimiento.getSaldo());
                reportes.add(reporte);
            }
        }

        return reportes;
    }
}

