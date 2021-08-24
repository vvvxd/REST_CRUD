package org.example.REST_CRUD.repository.implementations;

import org.example.REST_CRUD.model.User;
import org.example.REST_CRUD.repository.UserRepository;
import org.example.REST_CRUD.util.ConnectUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getById(Long id) {
        User user;
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User u inner join fetch u.events where u.id = :id");
            query.setParameter("id", id);
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User u inner join fetch u.events");
            users = query.getResultList();
        } catch (NoResultException e) {
            users = null;
        }
        return users;
    }

    @Override
    public User save(User s) {
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(s);
            session.getTransaction().commit();
        }
        return s;
    }

    @Override
    public User update(User s) {
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(s);
            session.getTransaction().commit();
        }
        return s;
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}
