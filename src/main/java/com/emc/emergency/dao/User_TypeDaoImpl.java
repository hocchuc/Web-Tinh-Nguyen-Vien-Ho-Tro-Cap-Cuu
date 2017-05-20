package com.emc.emergency.dao;

import com.emc.emergency.model.User_Type;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by hocan on 21-May-17.
 */
public class User_TypeDaoImpl implements User_TypeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User_Type> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Category> categories = session.createCriteria(Category.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<User_Type> criteria = builder.createQuery(User_Type.class);

        // Specify criteria root
        criteria.from(User_Type.class);

        // Execute query
        List<User_Type> user_types = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return user_types;
    }

    @Override
    public User_Type findById(Long id) {
        Session session = sessionFactory.openSession();
        User_Type user_type = session.get(User_Type.class,id);
        Hibernate.initialize(user_type.getUsers());
        session.close();
        return user_type;
    }

    @Override
    public void save(User_Type user_type) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Save the category
        session.saveOrUpdate(user_type);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();

    }

    @Override
    public void delete(User_Type user_type) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user_type);
        session.getTransaction().commit();
        session.close();
    }
}
