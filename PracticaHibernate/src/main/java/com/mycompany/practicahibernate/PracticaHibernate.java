/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practicahibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author DAM
 */
public class PracticaHibernate {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            // Crear y guardar una nueva instancia de Departamento
            Departamento depto = new Departamento();
            depto.setNombre("Ciencias");
            session.save(depto);

            // Crear y guardar una nueva instancia de Persona
            Persona persona = new Persona();
            persona.setNif("12345678A");
            persona.setNombre("Juan");
            persona.setApellido1("Pérez");
            persona.setCiudad("Madrid");
            persona.setDireccion("Calle Falsa 123");
            persona.setFechaNacimiento(new Date());
            persona.setSexo("H");
            persona.setTipo("profesor");
            session.save(persona);

            // Crear y guardar un Profesor asociado a la Persona y al Departamento
            Profesor profesor = new Profesor();
            profesor.setId(persona.getId()); // Usar el ID de la persona creada
            profesor.setNombre("Juan Pérez");
            profesor.setDepartamento(depto);
            session.save(profesor);

            // Crear y guardar un Grado
            Grado grado = new Grado("Ingeniería Informática");
            session.save(grado);

            // Crear y guardar una Asignatura asociada al Grado
            Asignatura asignatura = new Asignatura("Programación", 6, "Obligatoria", 1, 1, grado);
            session.save(asignatura);

            // Crear y guardar un Curso Escolar con fechas válidas
            curso_escolar curso = new curso_escolar();
            curso.setFechaInicio(new Date());
            curso.setFechaFin(new Date());
            session.save(curso);

            // Consultas en HQL
            List<Profesor> profesores = session.createQuery("FROM Profesor p WHERE p.departamento.nombre = 'Ciencias'", Profesor.class).list();
            System.out.println("Profesores en Ciencias: " + profesores);

            List<Object[]> groupByDept = session.createQuery("SELECT p.departamento.nombre, COUNT(p) FROM Profesor p GROUP BY p.departamento.nombre").list();
            System.out.println("Número de profesores por departamento: " + groupByDept);

            // Consultas en SQL
            List<Object[]> sqlQuery1 = session.createNativeQuery("SELECT p.nombre, d.nombre FROM profesor p JOIN departamento d ON p.id_departamento = d.id").list();
            System.out.println("Profesores y sus departamentos: " + sqlQuery1);

            List<Object[]> sqlQuery2 = session.createNativeQuery("SELECT a.nombre, g.nombre FROM asignatura a JOIN grado g ON a.id_grado = g.id").list();
            System.out.println("Asignaturas y sus grados: " + sqlQuery2);

            // Actualizar una instancia
            profesor.setNombre("Juan García");
            session.update(profesor);

            // Eliminar una instancia
            session.delete(profesor);

            tx.commit();
            session.close();
            HibernateUtil.shutdown();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
