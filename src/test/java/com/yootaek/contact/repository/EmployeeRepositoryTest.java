package com.yootaek.contact.repository;

import com.yootaek.contact.domain.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@ActiveProfiles("mongo")
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    void 데이터_입력() {
        Employee 홍길동 = new Employee("홍길동", "gildong@mail.com", "010-1111-1111", new Date());
        Employee save = employeeRepository.save(홍길동);
        assertThat(save.getName()).isEqualTo("홍길동");
    }

    @Test
    @Order(2)
    void 데이터_리스트_입력() {
        List<Employee> list = new ArrayList<>();
        Employee 이철수 = new Employee("이철수", "cheolsu@mail.com", "010-2222-2222", new Date());
        Employee 김영희 = new Employee("김영희", "yeonghee@mail.com", "010-3333-3333", new Date());
        list.add(이철수);
        list.add(김영희);
        List<Employee> employees = employeeRepository.saveAll(list);
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    @Order(3)
    void 데이터_가져오기() {
        Employee 홍길동 = employeeRepository.findByName("홍길동");
        assertThat(홍길동).isNotNull();
        assertThat(홍길동.getName()).isEqualTo("홍길동");
    }
}
