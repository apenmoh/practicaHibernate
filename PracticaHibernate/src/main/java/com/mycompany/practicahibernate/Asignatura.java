package com.mycompany.practicahibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private float creditos;
    private int curso;
    private int cuatrimestre;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;

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

    public float getCreditos() {
        return creditos;
    }

    public void setCreditos(float creditos) {
        this.creditos = creditos;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    
}
