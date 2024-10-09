package com.example.gestionbancaria.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Persona {

    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contraseña;

    private boolean estado;

}