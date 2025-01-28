package com.mycompany.practicahibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "creditos")
    private float creditos;

    @Column(name = "curso")
    private int curso;

    @Column(name = "cuatrimestre")
    private int cuatrimestre;

    @Column(name = "tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_grado")
    private Grado grado;

    public Asignatura() {}

    public Asignatura(String nombre, float creditos, String tipo, int curso, int cuatrimestre, Grado grado) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.tipo = tipo;
        this.curso = curso;
        this.cuatrimestre = cuatrimestre;
        this.grado = grado;
    }
    
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Asignatura{" 
            + "id=" + id + 
            ", nombre=" + nombre + '}';
    }
}
