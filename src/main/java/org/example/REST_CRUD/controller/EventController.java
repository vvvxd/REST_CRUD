package org.example.REST_CRUD.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.REST_CRUD.model.Event;
import org.example.REST_CRUD.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "events", value = "/events")
public class EventController extends HttpServlet {
    EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLine = req.getParameter("id");
        if (idLine == null) {
            List<Event> events = eventService.getAll();
            if (events != null) {
                String eventsJson = new ObjectMapper().writeValueAsString(events);
                resp.getWriter().write(eventsJson);
            } else {
                resp.getWriter().write("The list of events is empty");
            }
        } else {
            long id = Long.parseLong(idLine);
            Event event = eventService.getById(id);
            if (event != null) {
                String eventJson = new ObjectMapper().writeValueAsString(event);
                resp.getWriter().write(eventJson);
            } else {
                resp.getWriter().write("Event with this <id> is not exist");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event newEvent = new ObjectMapper().readValue(req.getReader(), Event.class);
        eventService.save(newEvent);
        String eventsJson = new ObjectMapper().writeValueAsString(newEvent);
        resp.getWriter().write(eventsJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event newEvent = new ObjectMapper().readValue(req.getReader(), Event.class);
        Event event = eventService.update(newEvent);
        if (event != null) {
            String eventsJson = new ObjectMapper().writeValueAsString(event);
            resp.getWriter().write(eventsJson);
        } else {
            resp.getWriter().write("Event with this <id> is not exist");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLine = req.getParameter("id");
        eventService.deleteById(Long.parseLong(idLine));
        resp.getWriter().write("Ok");
    }
}
