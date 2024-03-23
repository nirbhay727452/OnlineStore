package com.onlinestore.onlinestore.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Setter
@Getter
public class ExceptionDTO {
    private String msg;
    private HttpStatus httpStatus;
}
