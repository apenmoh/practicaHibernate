package com.mycompany.practicahibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "curso_escolar")
public class curso_escolar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private year anyo_inicio;
    private year anyo_fin;

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public year getAnyo_inicio() {
        return anyo_inicio;
    }

    public void setAnyo_inicio() {
        this.anyo_inicio = anyo_inicio;
    }

    public year getAnyo_fin() {
        return anyo_fin;
    }

    public void setAnyo_fin() {
        this.anyo_fin = anyo_fin;
    }

    
}
