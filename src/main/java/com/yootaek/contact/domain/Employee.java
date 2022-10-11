package com.yootaek.contact.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "employee")
public class Employee {
    @Id
    private String name;
    private String email;
    private String tel;
    private Date joined;
}
