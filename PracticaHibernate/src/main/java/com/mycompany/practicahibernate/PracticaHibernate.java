/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.practicahibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

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
            String hql = "FROM Profesor p WHERE p.departamento.nombre = :nombre";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", "Matemáticas");
            List<Profesor> profesores = query.list();

            for (Profesor p : profesores) {
                System.out.println(p);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }finally{
            session.close();
            HibernateUtil.shutdown();
        }
        
    }
}
