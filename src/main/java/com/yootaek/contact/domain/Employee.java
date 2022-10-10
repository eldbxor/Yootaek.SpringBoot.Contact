package com.yootaek.contact.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private String name;
    private String email;
    private String tel;
    private Date joined;
}
