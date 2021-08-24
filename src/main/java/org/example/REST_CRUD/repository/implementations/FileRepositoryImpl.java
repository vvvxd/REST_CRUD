package org.example.REST_CRUD.repository.implementations;

import org.example.REST_CRUD.model.File;
import org.example.REST_CRUD.model.File;
import org.example.REST_CRUD.repository.FileRepository;
import org.example.REST_CRUD.util.ConnectUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    @Override
    public File getById(Long id) {
        File file;
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from File e inner join fetch e.event where e.id = :id");
            query.setParameter("id", id);
            file = (File) query.getSingleResult();
        } catch (NoResultException e) {
            file = null;
        }
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> files;
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from File e inner join fetch e.event");
            files = query.getResultList();
        } catch (NoResultException e) {
            files = null;
        }
        return files;
    }

    @Override
    public File save(File s) {
        try (Session session = ConnectUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(s);
            session.getTransaction().commit();
        }
        return s;
    }

    @Override
    public File update(File s) {
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
            File file = session.load(File.class, id);
            session.delete(file);
            session.getTransaction().commit();
        }
    }
}
