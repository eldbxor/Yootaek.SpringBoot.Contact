package com.yootaek.contact.repository;

import com.yootaek.contact.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee findByName(String name) {
        return null;
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public void saveAll(List<Employee> employees) {

    }
}
