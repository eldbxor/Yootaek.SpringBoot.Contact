package com.yootaek.contact.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    private String name;
    private String email;
    private String tel;
    private Date joined;
}
