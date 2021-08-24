package org.example.REST_CRUD.repository;

import org.example.REST_CRUD.model.File;

import java.util.List;

public interface FileRepository  extends GenericRepository<File,Long>{
    @Override
    File getById(Long aLong);

    @Override
    List<File> getAll();

    @Override
    File save(File s);

    @Override
    File update(File s);

    @Override
    void deleteById(Long aLong);
}
