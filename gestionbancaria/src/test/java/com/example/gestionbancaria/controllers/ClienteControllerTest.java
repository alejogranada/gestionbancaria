package com.example.gestionbancaria.controllers;

import com.example.gestionbancaria.controller.ClienteController;
import com.example.gestionbancaria.entities.Cliente;
import com.example.gestionbancaria.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Cliente");
        cliente.setClienteId("testcliente");
        cliente.setContraseña("password");
        cliente.setEstado(true);

        Mockito.when(clienteService.crearCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Test Cliente"))
                .andExpect(jsonPath("$.clienteId").value("testcliente"));
    }
}
