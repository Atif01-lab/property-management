package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//PropertyRepository and CrudRepository both are interfaces, that's why extend keyword is used.
//CrudRepository takes two parameters
// 1. name of the entity on which the whole CRUD operations will happen.
// 2. Datatype of the primary key column of that entity.

//CrudRepository has already multiple methods, so we can use it
public interface PropertyRepository extends CrudRepository<PropertyEntity,Long> {
    Optional<PropertyEntity> findAllById(Long propertyId);
}
