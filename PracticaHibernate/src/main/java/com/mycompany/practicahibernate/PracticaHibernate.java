/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicahibernate;

import org.hibernate.Session;

/**
 *
 * @author DAM
 */
public class PracticaHibernate {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Crear un departamento
        Departamento departamento = new Departamento();
        departamento.setNombre("Matemáticas");
        session.save(departamento);

        // Confirmar la transacción
        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
