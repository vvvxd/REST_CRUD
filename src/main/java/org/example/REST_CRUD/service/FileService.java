package org.example.REST_CRUD.service;

import org.example.REST_CRUD.model.File;
import org.example.REST_CRUD.repository.FileRepository;
import org.example.REST_CRUD.repository.implementations.FileRepositoryImpl;

import java.util.List;

public class FileService {
    private final FileRepository fileRepository = new FileRepositoryImpl();

    public File getById(Long id) {
        return fileRepository.getById(id);
    }

    public List<File> getAll(){
        return fileRepository.getAll();
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public File update(File s) {
        return fileRepository.update(s);
    }

    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }
}
