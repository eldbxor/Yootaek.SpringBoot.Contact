package com.yootaek.contact.repository;

import com.yootaek.contact.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee findByName(String name);
    Employee save(Employee employee);
    void saveAll(List<Employee> employees);
}
