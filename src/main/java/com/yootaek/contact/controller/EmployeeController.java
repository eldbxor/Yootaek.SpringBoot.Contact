package com.yootaek.contact.controller;

import com.yootaek.contact.domain.Employee;
import com.yootaek.contact.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployee(@PathVariable String name) {
        Employee employee = new Employee("홍길동", "", "", new Date());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> addEmployee(@RequestPart @Nullable MultipartFile file,
                                            @RequestPart @Nullable List<Employee> json,
                                            @RequestPart @Nullable String csv) {
        try {
            if (file != null && !file.isEmpty())
                employeeService.saveEmployeesWithFile(file);
            else if (json != null)
                employeeService.saveEmployees(json);
            else if (StringUtils.hasText(csv))
                employeeService.saveEmployeesWithCsv(csv);
        }
        catch (ParseException | IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
