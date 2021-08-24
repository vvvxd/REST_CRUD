package org.example.REST_CRUD.repository;

import org.example.REST_CRUD.model.Event;

import java.util.List;

public interface EventRepository extends GenericRepository<Event,Long>{
    @Override
    Event getById(Long aLong);

    @Override
    List<Event> getAll();

    @Override
    Event save(Event s);

    @Override
    Event update(Event s);

    @Override
    void deleteById(Long aLong);
}
