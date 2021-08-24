package org.example.REST_CRUD.service;

import org.example.REST_CRUD.model.Event;
import org.example.REST_CRUD.repository.EventRepository;
import org.example.REST_CRUD.repository.implementations.EventRepositoryImpl;

import java.util.List;

public class EventService {
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public Event getById(Long id) {
        return eventRepository.getById(id);
    }

    public List<Event> getAll(){
        return eventRepository.getAll();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event s) {
        return eventRepository.update(s);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
}
