package com.example.gestionbancaria.services;

import com.example.gestionbancaria.entities.Cuenta;
import com.example.gestionbancaria.entities.Cliente;
import com.example.gestionbancaria.exceptions.ResourceNotFoundException;
import com.example.gestionbancaria.repositories.CuentaRepository;
import com.example.gestionbancaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Cuenta crearCuenta(Cuenta cuenta, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + clienteId));
        cuenta.setCliente(cliente);
        return cuentaRepository.save(cuenta);
    }

    public Cuenta actualizarCuenta(Long id, Cuenta cuentaDetalles) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));

        cuenta.setNumeroCuenta(cuentaDetalles.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetalles.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetalles.getSaldoInicial());
        cuenta.setEstado(cuentaDetalles.isEstado());

        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));
        cuentaRepository.delete(cuenta);
    }

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id " + id));
    }

    public Cuenta obtenerCuentaPorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con numeroCuenta " + numeroCuenta));
    }

    public List<Cuenta> listarCuentasPorClienteId(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }
}
