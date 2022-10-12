package com.yootaek.contact.exception;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ApiExceptionEntity {
    private String errorCode;
    private String errorMessage;
}
