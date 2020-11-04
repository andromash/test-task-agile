package com.agile.api.dao.impl;

import com.agile.api.dao.PictureDetailsDao;
import com.agile.api.entity.PictureDetails;
import java.util.List;
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

    @Override
    public List<PictureDetails> getByDynamicParameters(String parameter) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM PictureDetails WHERE author LIKE :p OR camera LIKE :p"
                    + " OR tags LIKE :p", PictureDetails.class)
                    .setParameter("%p%", parameter)
                    .getResultList();
        }
    }
}
