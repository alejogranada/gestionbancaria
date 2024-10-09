package com.example.gestionbancaria.controller;

import com.example.gestionbancaria.entities.Cliente;
import com.example.gestionbancaria.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@Validated @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Validated @RequestBody Cliente clienteDetalles) {
        Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDetalles);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}