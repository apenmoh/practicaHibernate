/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practicahibernate;

import java.util.List;

import org.hibernate.Session;

/**
 *
 * @author DAM
 */
public class PracticaHibernate {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Crear un departamento
            Departamento departamento = new Departamento();
            departamento.setNombre("TIC");
            session.save(departamento);

            // Confirmar la transacción
            session.getTransaction().commit();

            //Consulta HQL
            String hql = "SELECT p.departamento.nombre, COUNT(p.id) FROM Profesor p GROUP BY p.departamento.nombre";
            List<Object[]> resultados = session.createQuery(hql).list();

            for (Object[] fila : resultados) {
                System.out.println("Departamento: " + fila[0] + ", Profesores: " + fila[1]);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            if (session != null) {
                session.close();
            }
            HibernateUtil.shutdown();
        }

    }
}
