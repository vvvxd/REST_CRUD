package org.example.REST_CRUD.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.REST_CRUD.model.File;
import org.example.REST_CRUD.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/files")
public class FileController extends HttpServlet {
    FileService fileService = new FileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLine = req.getParameter("id");
        if (idLine == null) {
            List<File> files = fileService.getAll();
            if (files != null) {
                String filesJson = new ObjectMapper().writeValueAsString(files);
                resp.getWriter().write(filesJson);
            } else {
                resp.getWriter().write("The list of files is empty");
            }
        } else {
            long id = Long.parseLong(idLine);
            File file = fileService.getById(id);
            if (file != null) {
                String fileJson = new ObjectMapper().writeValueAsString(file);
                resp.getWriter().write(fileJson);
            } else {
                resp.getWriter().write("File with this <id> is not exist");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File newFile = new ObjectMapper().readValue(req.getReader(), File.class);
        fileService.save(newFile);
        String filesJson = new ObjectMapper().writeValueAsString(newFile);
        resp.getWriter().write(filesJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File newFile = new ObjectMapper().readValue(req.getReader(), File.class);
        File file = fileService.update(newFile);
        if (file != null) {
            String filesJson = new ObjectMapper().writeValueAsString(file);
            resp.getWriter().write(filesJson);
        } else {
            resp.getWriter().write("File with this <id> is not exist");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLine = req.getParameter("id");
        fileService.deleteById(Long.parseLong(idLine));
        resp.getWriter().write("Ok");
    }
}
