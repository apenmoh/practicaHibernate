package com.mycompany.practicahibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @Column(name = "id_profesor", nullable = false, unique = true)
    private int id;

    @Column(name = "nombre", length = 45, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @OneToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id")
    private Persona persona;

    public Profesor() {
        // Constructor vacío
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Nombre profesor: " + nombre + " || ID: " + id;
    }
}