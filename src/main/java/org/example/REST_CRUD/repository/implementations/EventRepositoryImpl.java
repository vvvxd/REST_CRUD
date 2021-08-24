package org.example.REST_CRUD.repository.implementations;

import org.example.REST_CRUD.model.Event;
import org.example.REST_CRUD.model.Event;
import org.example.REST_CRUD.repository.EventRepository;
import org.example.REST_CRUD.util.ConnectUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    @Override
    public Event getById(Long id) {
        Event event;
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Event e inner join fetch e.user inner join fetch e.file where e.id = :id");
            query.setParameter("id", id);
            event = (Event) query.getSingleResult();
        } catch (NoResultException e) {
            event = null;
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events;
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Event  e inner join fetch e.user inner join fetch e.file");
            events = query.getResultList();
        } catch (NoResultException e) {
            events = null;
        }
        return events;
    }

    @Override
    public Event save(Event s) {
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(s);
            session.getTransaction().commit();
        }
        return s;
    }

    @Override
    public Event update(Event s) {
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
            Event event = session.load(Event.class, id);
            session.delete(event);
            session.getTransaction().commit();
        }
    }
}
