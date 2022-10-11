package com.yootaek.contact.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yootaek.contact.domain.Employee;
import com.yootaek.contact.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> saveEmployees(List<Employee> employees) {
        if (employees.size() == 0)
            return null;

        return employeeRepository.saveAll(employees);
    }

    public List<Employee> saveEmployeesWithCsv(String csv) throws ParseException {
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Employee> employees = new ArrayList<>();

        String[] lines = csv.split("\n");
        for (String line :
                lines) {
            String[] items = line.split(",");
            if (items.length != 4)
                break;

            employees.add(new Employee(items[0].trim(), items[1].trim(), items[2].trim(), sdFormat.parse(items[3].trim())));
        }

        return saveEmployees(employees);
    }

    public List<Employee> saveEmployeesWithFile(MultipartFile file) throws IOException, ParseException {
        List<Employee> employees = null;
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        switch (ext) {
            case "json":
                String jsonStr = new String(file.getBytes(), StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                employees = saveEmployees(Arrays.asList(mapper.readValue(jsonStr, Employee[].class)));
                break;
            case "csv":
                employees = saveEmployeesWithCsv(new String(file.getBytes(), StandardCharsets.UTF_8));
                break;
            default:
                break;
        }

        return employees;
    }

    public List<Employee> selectAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee selectEmployee(String name) {
        return employeeRepository.findByName(name);
    }
}
