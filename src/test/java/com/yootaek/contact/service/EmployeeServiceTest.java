package com.yootaek.contact.service;

import com.yootaek.contact.domain.Employee;
import com.yootaek.contact.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@ActiveProfiles("mongo")
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    @Order(1)
    void 사원정보_입력() {
        List<Employee> list = new ArrayList<>();
        Employee 이철수 = new Employee("이철수", "cheolsu@mail.com", "010-2222-2222", new Date());
        Employee 김영희 = new Employee("김영희", "yeonghee@mail.com", "010-3333-3333", new Date());
        list.add(이철수);
        list.add(김영희);

        List<Employee> employees = employeeService.saveEmployees(list);
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    @Order(2)
    void CSV_STRING_사원정보_입력() {
        String csvStr = """
                김철수, charles@clovf.com, 010-7531-2468,2018-03-07
                박영희, matilda@clovf.com, 010-8765-4321,2021-04-28
                홍길동, kildong.hong@clovf.com, 010-1234-5678,2015-08-15""";
        List<Employee> employees = null;
        try {
            employees = employeeService.saveEmployeesWithCsv(csvStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(3);

        employees = employeeService.selectAllEmployee();
        assertThat(employees.size()).isEqualTo(5);

        Employee 홍길동 = employeeService.selectEmployee("홍길동");
        assertThat(홍길동.getEmail()).isEqualTo("kildong.hong@clovf.com");
    }

}
