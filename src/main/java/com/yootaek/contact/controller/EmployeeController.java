package com.yootaek.contact.controller;

import com.yootaek.contact.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployee(@PathVariable String name) {
        Employee employee = new Employee();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> addEmployee(@RequestPart @Nullable MultipartFile file, @RequestPart @Nullable List<Employee> request) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
