package com.agile.api.dao;

import com.agile.api.entity.Picture;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PictureDaoImpl implements PictureDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PictureDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Picture add(Picture picture) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(picture);
            transaction.commit();
            return picture;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add picture " + picture + "to database :", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Picture> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Picture", Picture.class).getResultList();
        }
    }

    @Override
    public List<Picture> getByParameter(Map<String, String> parameters) {
        return null;
    }
}
