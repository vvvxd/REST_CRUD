package org.example.REST_CRUD.repository;

import org.example.REST_CRUD.model.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User,Long>{
    @Override
    User getById(Long aLong);

    @Override
    List<User> getAll();

    @Override
    User save(User s);

    @Override
    User update(User s);

    @Override
    void deleteById(Long aLong);
}
