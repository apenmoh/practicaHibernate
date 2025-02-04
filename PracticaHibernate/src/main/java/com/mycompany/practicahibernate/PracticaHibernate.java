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
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            // Crear y guardar una nueva instancia de Departamento
            Departamento depto = new Departamento();
            depto.setNombre("Ciencias");
            session.save(depto);

            // Crear y guardar una nueva instancia de Persona
            Persona persona = new Persona();
            persona.setNif("12345678S");
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
            profesor.setPersona(persona); // Establecer la relación con Persona
            session.save(profesor);

            // Crear y guardar un Grado
            Grado grado = new Grado("Ingeniería Informática");
            session.save(grado);

            // Crear y guardar una Asignatura asociada al Grado
            Asignatura asignatura = new Asignatura("Programación", 6, "Obligatoria", 1, 1, grado);
            session.save(asignatura);

            // Confirmar la transacción
            tx.commit();

            // Consultas en HQL
            List<Grado> grados = session.createQuery("FROM Grado", Grado.class).list();
            System.out.println("Grados: " + grados);

            List<Asignatura> asignaturas = session.createQuery("FROM Asignatura", Asignatura.class).list();
            System.out.println("Asignaturas: " + asignaturas);

            // Consultas en SQL
            List<Object[]> sqlQueryGrados = session.createNativeQuery("SELECT nombre FROM grado").list();
            System.out.println("Grados (SQL): " + sqlQueryGrados);

            List<Object[]> sqlQueryAsignaturas = session.createNativeQuery("SELECT nombre FROM asignatura").list();
            System.out.println("Asignaturas (SQL): " + sqlQueryAsignaturas);

            // Actualizar una instancia que tenga relaciones
            tx = session.beginTransaction();
            Grado gradoToUpdate = session.find(Grado.class, grado.getId());
            if (gradoToUpdate != null) {
                // Actualizar el nombre del grado
                gradoToUpdate.setNombre("Ingeniería de Software");

                // Actualizar las asignaturas asociadas al grado
                List<Asignatura> asignaturasToUpdate = session.createQuery("FROM Asignatura a WHERE a.grado.id = :gradoId", Asignatura.class)
                        .setParameter("gradoId", gradoToUpdate.getId())
                        .getResultList();
                for (Asignatura asig : asignaturasToUpdate) {
                    asig.setNombre(asig.getNombre() + " Avanzada");
                    session.merge(asig);
                }

                // Guardar los cambios en el grado
                session.merge(gradoToUpdate);
            }
            tx.commit();

            // Eliminar una instancia que tenga relaciones
            tx = session.beginTransaction();
            Departamento deptoToDelete = session.find(Departamento.class, depto.getId());
            if (deptoToDelete != null) {
                // Eliminar los profesores asociados al departamento
                List<Profesor> profesoresToDelete = session.createQuery("FROM Profesor p WHERE p.departamento.id = :deptoId", Profesor.class)
                        .setParameter("deptoId", deptoToDelete.getId())
                        .getResultList();
                for (Profesor prof : profesoresToDelete) {
                    session.remove(prof);
                }
                // Eliminar el departamento
                session.remove(deptoToDelete);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}