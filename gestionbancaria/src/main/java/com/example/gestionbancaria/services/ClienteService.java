package com.example.gestionbancaria.services;

import com.example.gestionbancaria.entities.Cliente;
import com.example.gestionbancaria.exceptions.ResourceNotFoundException;
import com.example.gestionbancaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));

        cliente.setNombre(clienteDetalles.getNombre());
        cliente.setGenero(clienteDetalles.getGenero());
        cliente.setEdad(clienteDetalles.getEdad());
        cliente.setIdentificacion(clienteDetalles.getIdentificacion());
        cliente.setDireccion(clienteDetalles.getDireccion());
        cliente.setTelefono(clienteDetalles.getTelefono());
        cliente.setClienteId(clienteDetalles.getClienteId());
        cliente.setContraseña(clienteDetalles.getContraseña());
        cliente.setEstado(clienteDetalles.isEstado());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
        clienteRepository.delete(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id " + id));
    }

    public Cliente obtenerClientePorClienteId(String clienteId) {
        return clienteRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con clienteId " + clienteId));
    }
}