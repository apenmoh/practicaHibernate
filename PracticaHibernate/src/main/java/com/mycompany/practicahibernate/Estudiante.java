package com.mycompany;

import jakarta.persistence.*;
import java.util.Set;
import com.mycompany.practicahibernate.curso_escolar;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private int edad;

    @ManyToMany(mappedBy = "estudiantes")
    private Set<curso_escolar> cursos;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Set<curso_escolar> getCursos() {
        return cursos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCursos(Set<curso_escolar> cursos) {
        this.cursos = cursos;
    }

}
