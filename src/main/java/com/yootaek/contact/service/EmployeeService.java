package com.yootaek.contact.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yootaek.contact.domain.Employee;
import com.yootaek.contact.exception.ApiException;
import com.yootaek.contact.exception.ExceptionEnum;
import com.yootaek.contact.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    public List<Employee> saveEmployeesWithCsv(String csv) {
        DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Employee> employees = new ArrayList<>();

        String[] lines = csv.split("\n");
        for (String line :
                lines) {
            if (!StringUtils.hasText(line))
                break;

            String[] items = line.split(",");
            if (items.length != 4)
                break;

            try {
                employees.add(new Employee(items[0].trim(), items[1].trim(), items[2].trim(), sdFormat.parse(items[3].trim())));
            } catch (ParseException e) {
                throw new ApiException(ExceptionEnum.INVALID_PARAMETER);
            }
        }

        if (employees.size() == 0)
            throw new ApiException(ExceptionEnum.INVALID_PARAMETER);

        return saveEmployees(employees);
    }

    public List<Employee> saveEmployeesWithFile(MultipartFile file) {
        List<Employee> employees = null;
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
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
        } catch (IOException e) {
            throw new ApiException(ExceptionEnum.INVALID_PARAMETER);
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
