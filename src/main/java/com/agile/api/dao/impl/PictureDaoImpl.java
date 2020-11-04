package com.agile.api.dao.impl;

import com.agile.api.dao.PictureDao;
import com.agile.api.entity.Picture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            return session.createQuery("FROM Picture", Picture.class)
                    .getResultList();
        }
    }

    @Override
    public List<Picture> getByParameter(Map<String, String[]> parameters) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Picture> pictureCriteriaQuery = criteriaBuilder.createQuery(Picture.class);
            Root<Picture> root = pictureCriteriaQuery.from(Picture.class);
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                predicates.add(root.get(entry.getKey()).in(Arrays.asList(entry.getValue())));
            }
            pictureCriteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
            return session.createQuery(pictureCriteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get the list of phones", e);
        }
    }

    @Override
    public void clearData() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Picture").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not clear the table Picture");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
