package org.example.REST_CRUD.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.REST_CRUD.model.User;
import org.example.REST_CRUD.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "users", value = "/users")
public class UserController extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLine = req.getParameter("id");
        if (idLine == null) {
            List<User> users = userService.getAll();
            if (users != null) {
                String usersJson = new ObjectMapper().writeValueAsString(users);
                resp.getWriter().write(usersJson);
            } else {
                resp.getWriter().write("The list of users is empty");
            }
        } else {
            long id = Long.parseLong(idLine);
            User user = userService.getById(id);
            if (user != null) {
                String userJson = new ObjectMapper().writeValueAsString(user);
                resp.getWriter().write(userJson);
            } else {
                resp.getWriter().write("User with this <id> is not exist");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new ObjectMapper().readValue(req.getReader(), User.class);
        userService.save(newUser);
        String usersJson = new ObjectMapper().writeValueAsString(newUser);
        resp.getWriter().write(usersJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new ObjectMapper().readValue(req.getReader(), User.class);
        User user = userService.update(newUser);
        if (user != null) {
            String usersJson = new ObjectMapper().writeValueAsString(user);
            resp.getWriter().write(usersJson);
        } else {
            resp.getWriter().write("User with this <id> is not exist");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLine = req.getParameter("id");
        userService.deleteById(Long.parseLong(idLine));
        resp.getWriter().write("Ok");
    }
}
