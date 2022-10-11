package com.yootaek.contact.repository;

import com.yootaek.contact.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByName(String name);
}
