package com.agile.api.dao;

import com.agile.api.entity.PictureDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDetailsDaoImpl implements PictureDetailsDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PictureDetailsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PictureDetails add(PictureDetails details) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(details);
            transaction.commit();
            return details;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add details " + details + "to database :", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
