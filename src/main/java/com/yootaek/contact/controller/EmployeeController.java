package com.yootaek.contact.controller;

import com.yootaek.contact.domain.Employee;
import com.yootaek.contact.exception.ApiException;
import com.yootaek.contact.exception.ExceptionEnum;
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
        return new ResponseEntity<>(employeeService.selectAllEmployee(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Employee> getEmployee(@PathVariable String name) {
        return new ResponseEntity<>(employeeService.selectEmployee(name), HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> addEmployee(@RequestPart @Nullable MultipartFile file,
                                            @RequestPart @Nullable List<Employee> json,
                                            @RequestPart @Nullable String csv) {
        if (file != null && !file.isEmpty())
            employeeService.saveEmployeesWithFile(file);
        else if (json != null)
            employeeService.saveEmployees(json);
        else if (StringUtils.hasText(csv))
            employeeService.saveEmployeesWithCsv(csv);
        else
            throw new ApiException(ExceptionEnum.INVALID_PARAMETER);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
